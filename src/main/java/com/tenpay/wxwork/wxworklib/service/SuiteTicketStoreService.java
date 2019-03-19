package com.tenpay.wxwork.wxworklib.service;

/**
 * suite ticket 存储接口
 * Created by yaogangli on 2018/3/3.
 */
public interface SuiteTicketStoreService {

    void saveSuiteTicket(String suiteId, String suiteTicket);

    String getSuiteTicket(String suiteId);
}
