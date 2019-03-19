package com.tenpay.wxwork.wxworklib.model;

/**
 * 适用多种企业微信定义的 access token
 *
 */
public class AccessToken {
    private String token;
    private int expire;

    public AccessToken(String token, int expire) {
        this.token = token;
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "token='" + token + '\'' +
                ", expire=" + expire +
                //", expireFormat=" + formatTimestamp(expire) +
                '}';
    }
}
