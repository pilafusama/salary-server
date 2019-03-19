package com.tenpay.wxwork.wxworklib.service;

import com.tenpay.wxwork.wxworklib.client.WxworkHttpClient;
import com.tenpay.wxwork.wxworklib.utils.UrlGenerator;
import com.tenpay.wxwork.wxworklib.WxworkConfig;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 授权服务基类
 * Created by yaogangli on 2018/3/7.
 */
public abstract class AuthService {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    @Resource
    protected WxworkHttpClient wxworkHttpClient;

    @Resource
    private WxworkConfig wxworkConfig;

    /**
     * 生成企业授权 url
     *
     */
    public String genCorpAuthUrl(String suiteId, String redirectUrl, String state) {
        WxworkConfig.Suite suite = wxworkConfig.getSuite(suiteId);
        boolean isSuiteReleased = false;
        if (null != suite) {
            isSuiteReleased = suite.getReleased();
        }
        String preAuthCode = wxworkHttpClient.getPreAuthCode(suiteId, isSuiteReleased);
        return UrlGenerator.genCorpAuthUrl(suiteId, preAuthCode, redirectUrl, state);
    }

    abstract public void createAuth(String suiteId, String authCode);

    /**
     * 注册定制化时调用，需要传入商户 id
     *
     */
    abstract public void createAuth(String suiteId, String authCode, String mchId);

    abstract public void changeAuth(String suiteId, String corpId);

    abstract public void cancelAuth(String suiteId, String corpId);
}
