package com.tenpay.wxwork.salary.service;

import com.qq.l5.L5Config;
import com.qq.l5.L5EndPoint;
import com.qq.l5.L5Exception;
import com.qq.l5.L5sys;

import com.tenpay.wxwork.salary.common.utils.ExceptionUtils;
import com.tenpay.wxwork.salary.config.CkvConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.io.Closeable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ConnectException;
import java.net.SocketException;

/**
 * Created by yaogangli on 2018/3/10.
 */
@Component
public class CkvPlusClientFactory {

    private final static Logger LOGGER = LoggerFactory.getLogger(CkvPlusClientFactory.class);

    @Resource
    private CkvConfig ckvConfig;

    public CkvPlusClient getClient() {
        if (ckvConfig.getUseRedis()) {
            return getRedisClient();
        } else {
            return getClient(true);
        }
    }

    public CkvPlusClient getRedisClient() {
        return new CkvPlusClientJedisImpl(ckvConfig.getRedis().getIp(),
                                          ckvConfig.getRedis().getPort());
    }

    public CkvPlusClient getClient(boolean autoClose) {
        L5sys l5sys = new L5sys();
        L5Config config = new L5Config(ckvConfig.getL5mid(), ckvConfig.getL5cid());
        L5sys.QosRequest qosRequest = new L5sys.QosRequest(config);

        // 获取 ip port
        L5EndPoint endPoint;
        try {
            endPoint = l5sys.fetchRoute(qosRequest, 0.2f);
        } catch (L5Exception e) {
            throw new RuntimeException(e);
        }
        String ip = endPoint.getHostIp();
        int port = endPoint.getHostPort();
        LOGGER.info("modid={}, cmdid={}, ip={}, port={}", ckvConfig.getL5mid(), ckvConfig.getL5cid(), ip, port);

        CkvPlusClient ckvPlusClient = new CkvPlusClientImpl(ip, port);
        ckvPlusClient.auth(ckvConfig.getL5bid() + ":" + ckvConfig.getL5pwd());

        // 动态代理
        InvocationHandler handler = new CkvPlusClientInvocationHandler(ckvPlusClient, autoClose, l5sys, qosRequest);
        CkvPlusClient proxyInstance = (CkvPlusClient) Proxy.newProxyInstance(ckvPlusClient.getClass().getClassLoader(),
                ckvPlusClient.getClass().getInterfaces(), handler);
        return proxyInstance;
    }

    private static class CkvPlusClientInvocationHandler implements InvocationHandler {
        private Object target;
        private boolean autoClose;

        private L5sys l5sys;
        private L5sys.QosRequest qosRequest;

        public CkvPlusClientInvocationHandler(Object target, boolean autoClose, L5sys l5sys, L5sys.QosRequest qosRequest) {
            this.target = target;
            this.autoClose = autoClose;
            this.l5sys = l5sys;
            this.qosRequest = qosRequest;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long start = System.currentTimeMillis();
            try {
                Object result = method.invoke(target, args);
                if (!isClose(method)) {
                    l5sys.updateRouteResult(qosRequest, L5sys.Status.SUCCESS, System.currentTimeMillis() - start);
                }
                return result;
            } catch (Throwable throwable) {
                if (!isClose(method) && (ExceptionUtils.findFixException(throwable, ConnectException.class) != null ||
                        ExceptionUtils.findFixException(throwable, SocketException.class) != null)) {
                    LOGGER.debug("is connectException or socketException,update l5 status to failed");
                    l5sys.updateRouteResult(qosRequest, L5sys.Status.FAILED, System.currentTimeMillis() - start);
                }
                throw throwable;
            } finally {
                if (autoClose && !isClose(method)) {
                    Closeable closeable = (Closeable) target;
                    closeable.close();
                }
            }
        }

        private boolean isClose(Method method) {
            return method.getName().equals("close");
        }
    }
}
