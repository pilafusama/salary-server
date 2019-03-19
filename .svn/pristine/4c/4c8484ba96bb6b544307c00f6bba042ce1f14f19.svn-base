package com.tenpay.wxwork.wxworklib;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaogangli on 2018/3/3.
 */
@Component
@ConfigurationProperties(prefix="wxworklib")
public class WxworkConfig {

    public String url;
    public String corpId;
    public String providerSecret;
    public String token;
    public String encodingAESKey;
    public String templateId;
//    public String verifyCodeAesKey;
    public int sessionExpiresIn;

    public List<Suite> suites = new ArrayList<>();

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public void setProviderSecret(String providerSecret) {
        this.providerSecret = providerSecret;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<Suite> getSuites() {
        return suites;
    }

    public void setSessionExpiresIn(int sessionExpiresIn) {
        this.sessionExpiresIn = sessionExpiresIn;
    }

//    public void setVerifyCodeAesKey(String verifyCodeAesKey) {
//        this.verifyCodeAesKey = verifyCodeAesKey;
//    }

    public Suite getSuite(String suiteId) {
        for (Suite suite : suites) {
            if (suite.id.equals(suiteId)) {
                return suite;
            }
        }
        return null;
    }

    public Suite getSuite(int type) {
        for (Suite suite : suites) {
            if (suite.type == type) {
                return suite;
            }
        }
        return null;
    }

    public Suite getSuiteByAppid(String appId) {
        int type =  Integer.parseInt(appId.substring(16,18));
        return getSuite(type);
    }

    public static class Suite {
        public String id;
        public String secret;
        public String token;
        public String encodingAESKey;
        public int type;
        private boolean released;

        public boolean getReleased()
        {
            return released;
        }

        public void setReleased(boolean released)
        {
            this.released = released;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setEncodingAESKey(String encodingAESKey) {
            this.encodingAESKey = encodingAESKey;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public int getSessionExpiresIn() {
        return sessionExpiresIn;
    }
}
