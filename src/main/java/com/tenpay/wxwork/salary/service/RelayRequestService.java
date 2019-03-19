package com.tenpay.wxwork.salary.service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.tenpay.bap.client.relay.RelayClient;
import com.tenpay.bap.relay.context.RelaySessionInfo;
import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.bap.relay.protocol.RelayMessage;

@Service
public class RelayRequestService {
    @Resource
    private RelayClient relayClient;

    public RelayMessage sendRequest(RelaySessionInfo sessionInfo, BankProxyRelayRequestMsg request) {
        StopWatch stopWatch = new StopWatch();

        RelayMessage resp;
        try {
            stopWatch.start();
            resp = relayClient.sendRequest(request);
        } finally {
            stopWatch.stop();
            AtomicLong costTime = sessionInfo.getCallRelayTotalCostTime();
            costTime.getAndAdd(stopWatch.getLastTaskTimeMillis());
            AtomicInteger callTime = sessionInfo.getCallRelayTotalCount();
            callTime.getAndAdd(1);
        }
        return resp;
    }
}
