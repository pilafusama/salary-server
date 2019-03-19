package com.tenpay.wxwork.wxworklib.utils;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlGenerator {

    private final static Logger LOGGER = LoggerFactory.getLogger(UrlGenerator.class);

    public static String encodeUrl(String url) {
        String encodedUrl = url;
        try {
            encodedUrl = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error("failed to encode url: {}", ex);
        }
        LOGGER.debug("encode '{}' to '{}'", url, encodedUrl);

        return encodedUrl;
    }

    /**
     * 第三方服务商在自己的网站中放置“企业微信应用授权”的入口，引导企业微信管理员进入应用授权页。
     *
     */
    public static String genCorpAuthUrl(String suiteId, String preAuthCode,
                                        String redirectUrl, String state) {
        String encodedUrl = encodeUrl(redirectUrl);
        return String.format("https://open.work.weixin.qq.com/3rdapp/install?suite_id=%s&pre_auth_code=%s&redirect_uri=%s&state=%s",
                      suiteId, preAuthCode, encodedUrl, state);
    }

    /**
     * 生成用户登录 URL ，通过企业微信的 OAuth 机制
     * https://work.weixin.qq.com/api/doc#10975/%E7%BD%91%E9%A1%B5%E6%8E%88%E6%9D%83%E7%99%BB%E5%BD%95%E7%AC%AC%E4%B8%89%E6%96%B9
     *
     */
    public static String genUserLoginUrl(String suiteId, String redirectUrl, String state) {
        String scope = "snsapi_privateinfo";
        return String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
                             suiteId, encodeUrl(redirectUrl), scope, state);
    }
}