package com.tenpay.wxwork.salary.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tenpay.wxwork.wxworklib.service.SuiteTicketStoreService;
import com.tenpay.wxwork.wxworklib.client.WxworkHttpClient;
import com.tenpay.wxwork.wxworklib.model.AccessToken;
import com.tenpay.wxwork.wxworklib.WxworkConfig;
import com.tenpay.wxwork.wxworklib.service.AbstractWxAccessTokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.service.CkvPlusClientFactory;
import static com.tenpay.wxwork.salary.common.utils.Constant.PRE_WX_ACCESS_TOKEN;
import static com.tenpay.wxwork.salary.common.utils.DateUtils.getTimestamp;

/**
 * 企业微信accessToken服务
 * Created by yaogangli on 2018/2/23.
 */
@Service
public class WxAccessTokenCkvService extends AbstractWxAccessTokenService {

    private final static Logger LOGGER = LoggerFactory.getLogger(WxAccessTokenCkvService.class);

    private final static String KEY_ACCESS_TOKEN_LOCK = PRE_WX_ACCESS_TOKEN + "lock";

    @Resource
    private CkvPlusClientFactory ckvPlusClientFactory;

    @Resource
    private Gson gson;

    @Override
    protected boolean tryLock(int expireInSeconds) {
        CkvPlusClient client = null;
        try {
            client = ckvPlusClientFactory.getClient();
            String reply = client.set(KEY_ACCESS_TOKEN_LOCK, KEY_ACCESS_TOKEN_LOCK, "nx", "EX", expireInSeconds);
            LOGGER.info("tryLock reply:{}", reply);
            return Constant.OK.equals(reply);
        } catch (Exception e) {
            LOGGER.error("failed to try acquire lock with ckv: {}", e);
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    protected void unlock() {
        CkvPlusClient client = null;
        try {
            client = ckvPlusClientFactory.getClient();
            Long reply = client.del(KEY_ACCESS_TOKEN_LOCK);
            LOGGER.info("unlock reply:{}", reply);
        } catch (Exception e) {
            LOGGER.error("failed to unlock with ckv: {}", e);
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected AccessToken getAccessToken(String id) {
        CkvPlusClient client = null;
        try {
            client = ckvPlusClientFactory.getClient();
            return gson.fromJson(client.get(PRE_WX_ACCESS_TOKEN + id), AccessToken.class);
        } catch (Exception e) {
            LOGGER.error("failed to get access token from ckv for id {}: {}", id, e);
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void setAccessToken(String id, AccessToken newAccessToken) {
        CkvPlusClient client = null;
        try {
            client = ckvPlusClientFactory.getClient();
            String reply = client.set(PRE_WX_ACCESS_TOKEN + id, gson.toJson(newAccessToken));
            // Validator.checkOk(reply);
            LOGGER.debug("setAccessToken for id {}, reply: {}", id, reply);
        } catch (Exception e) {
            LOGGER.error("failed to get access token from ckv for id {}: {}", id, e);
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
