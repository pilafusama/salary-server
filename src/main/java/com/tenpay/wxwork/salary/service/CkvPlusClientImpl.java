package com.tenpay.wxwork.salary.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;

/**
 * Created by yaogangli on 2018/3/12.
 */
public class CkvPlusClientImpl extends Jedis implements CkvPlusClient {
    public CkvPlusClientImpl() {
    }

    public CkvPlusClientImpl(String host) {
        super(host);
    }

    public CkvPlusClientImpl(String host, int port) {
        super(host, port);
    }

    public CkvPlusClientImpl(String host, int port, boolean ssl) {
        super(host, port, ssl);
    }

    public CkvPlusClientImpl(String host, int port, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(host, port, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public CkvPlusClientImpl(String host, int port, int timeout) {
        super(host, port, timeout);
    }

    public CkvPlusClientImpl(String host, int port, int timeout, boolean ssl) {
        super(host, port, timeout, ssl);
    }

    public CkvPlusClientImpl(String host, int port, int timeout, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(host, port, timeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public CkvPlusClientImpl(String host, int port, int connectionTimeout, int soTimeout) {
        super(host, port, connectionTimeout, soTimeout);
    }

    public CkvPlusClientImpl(String host, int port, int connectionTimeout, int soTimeout, boolean ssl) {
        super(host, port, connectionTimeout, soTimeout, ssl);
    }

    public CkvPlusClientImpl(String host, int port, int connectionTimeout, int soTimeout, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(host, port, connectionTimeout, soTimeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public CkvPlusClientImpl(JedisShardInfo shardInfo) {
        super(shardInfo);
    }

    public CkvPlusClientImpl(URI uri) {
        super(uri);
    }

    public CkvPlusClientImpl(URI uri, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public CkvPlusClientImpl(URI uri, int timeout) {
        super(uri, timeout);
    }

    public CkvPlusClientImpl(URI uri, int timeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public CkvPlusClientImpl(URI uri, int connectionTimeout, int soTimeout) {
        super(uri, connectionTimeout, soTimeout);
    }

    public CkvPlusClientImpl(URI uri, int connectionTimeout, int soTimeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, connectionTimeout, soTimeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }

}
