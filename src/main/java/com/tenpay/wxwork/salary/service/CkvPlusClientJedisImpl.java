package com.tenpay.wxwork.salary.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;

/**
 * Redis 客户端
 *
 */
public class CkvPlusClientJedisImpl extends Jedis implements CkvPlusClient {

    public CkvPlusClientJedisImpl() {
    }

    public CkvPlusClientJedisImpl(String host, int port) {
        super(host, port);
    }

}
