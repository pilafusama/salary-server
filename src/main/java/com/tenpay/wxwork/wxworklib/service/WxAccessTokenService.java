package com.tenpay.wxwork.wxworklib.service;

/**
 * Created by yaogangli on 2018/2/23.
 */
public interface WxAccessTokenService {

    void refreshAccessTokens();

    String getProviderAccessToken();

    String getSuiteAccessToken(String suiteId);

    /**
     * 获取某第三方应用下某企业的 access token
     *
     */
    String getCorpAccessToken(String suiteId, String corpid, String permanentCode);
}
