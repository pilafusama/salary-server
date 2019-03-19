package com.tenpay.wxwork.salary.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties(prefix="ckv")
public class CkvConfig {

    private boolean useRedis;

    private int l5mid;

    private int l5cid;

    private String l5bid;

    private String l5pwd;

    private Redis redis;

    public static class Redis {

        private String ip;

        private int port;

        public int getPort()
        {
            return port;
        }

        public void setPort(int port)
        {
            this.port = port;
        }

        public String getIp()
        {
            return ip;
        }

        public void setIp(String ip)
        {
            this.ip = ip;
        }
    }

    public boolean getUseRedis()
    {
        return useRedis;
    }

    public void setUseRedis(boolean useRedis)
    {
        this.useRedis = useRedis;
    }

    public int getL5mid()
    {
        return l5mid;
    }

    public void setL5mid(int l5mid)
    {
        this.l5mid = l5mid;
    }

    public int getL5cid()
    {
        return l5cid;
    }

    public void setL5cid(int l5cid)
    {
        this.l5cid = l5cid;
    }

    public String getL5bid()
    {
        return l5bid;
    }

    public void setL5bid(String l5bid)
    {
        this.l5bid = l5bid;
    }

    public String getL5pwd()
    {
        return l5pwd;
    }

    public void setL5pwd(String l5pwd)
    {
        this.l5pwd = l5pwd;
    }

    public Redis getRedis()
    {
        return redis;
    }

    public void setRedis(Redis redis)
    {
        this.redis = redis;
    }

}
