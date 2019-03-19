package com.tenpay.wxwork.wxworklib.service;

import com.google.gson.JsonObject;
import com.tenpay.wxwork.wxworklib.service.SuiteTicketStoreService;
import com.tenpay.wxwork.wxworklib.client.WxworkHttpClient;
import com.tenpay.wxwork.wxworklib.model.AccessToken;
import com.tenpay.wxwork.wxworklib.WxworkConfig;
import com.tenpay.wxwork.wxworklib.service.WxAccessTokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.tenpay.wxwork.wxworklib.utils.DateUtils.getTimestamp;

/**
 * 企业微信accessToken服务基类
 * Created by yaogangli on 2018/2/23.
 */
public abstract class AbstractWxAccessTokenService implements WxAccessTokenService {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractWxAccessTokenService.class);

    protected final static int LOCK_EXPIRE = 10;
    protected final static int ACCESS_TOKEN_EXPIRE_IN = 7200;

    protected ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    @Resource
    protected WxworkHttpClient wxworkHttpClient;

    @Resource
    protected WxworkConfig wxworkConfig;

    @Resource
    protected SuiteTicketStoreService suiteTicketStoreService;

    protected List<AccessTokenSpi> accessTokenSpiList = new ArrayList<>();;

    abstract protected boolean tryLock(int expireInSeconds);

    abstract protected void unlock();

    /**
     * 从存储介质取 access token
     * id 参数可以是服务商 id 、 suite id 等
     *
     */
    abstract protected AccessToken getAccessToken(String id);

    /**
     * 保存 access token 到存储介质取
     *
     */
    abstract protected void setAccessToken(String id, AccessToken newAccessToken);

    /**
     * 启动一个线程不断刷新 token
     *
     */
    @PostConstruct
    void startTask() {
        accessTokenSpiList.add(new ProivderAccessTokenSpi());
        for (WxworkConfig.Suite suite : wxworkConfig.suites) {
            accessTokenSpiList.add(new SuiteAccessTokenSpi(suite));
        }

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("time to refresh all access tokens");
                refreshAccessTokens();
            }
        }, 1, 300, TimeUnit.SECONDS); // TODO 间隔可配置
    }

    @Override
    public void refreshAccessTokens() {
        LOGGER.info("start to refreshAccessToken.....");

        long start = System.currentTimeMillis();

        try {
            // 先获取锁
            boolean locked = tryLock(LOCK_EXPIRE);
            if (!locked) {
                LOGGER.error("failed to acquire lock!");
                return;
            }
            try {
                // 每个access-token自己玩
                for (AccessTokenSpi accessTokenSpi : accessTokenSpiList) {
                    LOGGER.info("access token id: {}", accessTokenSpi.getId());

                    AccessToken accessToken = getAccessToken(accessTokenSpi.getId());
                    LOGGER.info("access token in ckv:{}", accessToken);

                    // 再判断时间是否快到期，如果剩5分钟过期则更新
                    if (accessToken == null || (accessToken.getExpire() - getTimestamp()) < 300) {
                        AccessToken newAccessToken = accessTokenSpi.getAccessToken();
                        LOGGER.info("new access token:{}", newAccessToken);

                        setAccessToken(accessTokenSpi.getId(), newAccessToken);
                    }
                }

            } finally {
                if ((System.currentTimeMillis() - start) / 1000 < LOCK_EXPIRE) {
                    unlock();
                }
            }
        } catch (Throwable t) {
            LOGGER.error("refreshAccessToken err!", t);
        } finally {
            LOGGER.info("end of refreshAccessToken!cost={}", (System.currentTimeMillis() - start));
        }
    }

    @Override
    public String getSuiteAccessToken(String id) {
        AccessToken accessToken = getAccessToken(id);
        if ( null != accessToken){
            return accessToken.getToken();
        }else {
            return null;
        }
    }

    @Override
    public String getProviderAccessToken() {
        AccessToken accessToken = getAccessToken(wxworkConfig.corpId);
        if (null != accessToken){
            return accessToken.getToken();
        }else {
            return null;
        }
    }

    @Override
    public String getCorpAccessToken(String suiteId, String corpid, String permanentCode) {
        // TODO cache it in ckv
        JsonObject result = wxworkHttpClient.getCorpAccessToken(suiteId, corpid, permanentCode);
        return result.get("access_token").getAsString();
    }

    protected interface AccessTokenSpi {
        String getId();

        AccessToken getAccessToken();
    }

    protected class ProivderAccessTokenSpi implements AccessTokenSpi {

        @Override
        public String getId() {
            return wxworkConfig.corpId;
        }

        @Override
        public AccessToken getAccessToken() {
            // https://work.weixin.qq.com/api/doc#11791/服务商的token
            JsonObject data = new JsonObject();
            data.addProperty("corpid", wxworkConfig.corpId); // 服务商的，非企业
            data.addProperty("provider_secret", wxworkConfig.providerSecret);
            JsonObject jsonRet = wxworkHttpClient.invoke("/service/get_provider_token", data);
            String token = jsonRet.get("provider_access_token").getAsString();
            //int expire = jsonRet.get("expires_in").getAsInt();

            // 提前10分钟更新access_token，所以我们的过期时间要比企业微信少10分钟
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, ACCESS_TOKEN_EXPIRE_IN - 600);
            AccessToken accessToken = new AccessToken(token, getTimestamp(calendar));
            return accessToken;
        }
    }

    protected class SuiteAccessTokenSpi implements AccessTokenSpi {

        protected WxworkConfig.Suite suite;

        SuiteAccessTokenSpi(WxworkConfig.Suite suite) {
            this.suite = suite;
        }

        @Override
        public String getId() {
            return suite.id;
        }

        @Override
        public AccessToken getAccessToken() {
            // https://work.weixin.qq.com/api/doc#10975/获取第三方应用凭证
            JsonObject data = new JsonObject();
            data.addProperty("suite_id", suite.id);
            data.addProperty("suite_secret", suite.secret);
            data.addProperty("suite_ticket", suiteTicketStoreService.getSuiteTicket(suite.id));
            JsonObject jsonRet = wxworkHttpClient.invoke("/service/get_suite_token", data);
            String token = jsonRet.get("suite_access_token").getAsString();
            //int expire = jsonRet.get("expires_in").getAsInt();

            // 提前10分钟更新access_token
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, ACCESS_TOKEN_EXPIRE_IN - 600);
            AccessToken accessToken = new AccessToken(token, getTimestamp(calendar));
            return accessToken;
        }
    }

}
