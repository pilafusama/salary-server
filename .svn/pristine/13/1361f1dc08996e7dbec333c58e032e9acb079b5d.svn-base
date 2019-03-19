package com.tenpay.wxwork.salary.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tenpay.wxwork.wxworklib.service.SuiteTicketStoreService;
import com.tenpay.wxwork.salary.common.utils.Constant;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class SuiteTicketCkvService implements SuiteTicketStoreService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SuiteTicketCkvService.class);

    @Resource
    private CkvPlusClientFactory ckvPlusClientFactory;

   
    public void saveSuiteTicket(String suiteId, String suiteTicket) {
        CkvPlusClient client = ckvPlusClientFactory.getClient();
        String reply = client.set(Constant.PRE_SUITE_TICKET + suiteId, suiteTicket);
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!Constant.OK.equals(reply)) {
            LOGGER.error("set suite err: "+reply);
        } else {
            LOGGER.info("save suite ticket to ckv:suiteId={}, suiteTicket={}", suiteId, suiteTicket);
        }
    }

    public String getSuiteTicket(String suiteId) {
        CkvPlusClient client = ckvPlusClientFactory.getClient();
        String suiteTicket = client.get(Constant.PRE_SUITE_TICKET + suiteId).toString();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return suiteTicket;
    }
}
