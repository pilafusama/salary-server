package com.tenpay.wxwork.wxworklib.hanlder.impl;

import com.tenpay.wxwork.wxworklib.hanlder.AbstractInfoHandler;
import com.tenpay.wxwork.wxworklib.hanlder.InfoHanlder;
import com.tenpay.wxwork.wxworklib.hanlder.InfoType;
import com.tenpay.wxwork.wxworklib.service.SuiteTicketStoreService;
import org.w3c.dom.Document;

import javax.annotation.Resource;

import static com.tenpay.wxwork.wxworklib.utils.XmlUtils.findNodeContent;

/**
 * https://work.weixin.qq.com/api/doc#10982/推送suite_ticket
 * Created by yaogangli on 2018/3/2.
 */
@InfoType(type = "suite_ticket")
public class SuiteTicketHandler extends AbstractInfoHandler implements InfoHanlder {

    @Resource
    private SuiteTicketStoreService suiteTicketStoreService;

    @Override
    public void hanle(Document document) {
        String suiteId = getSuiteId(document);
        String suiteTicket = findNodeContent(document, "/xml/SuiteTicket");
        // 更新 suite_ticket
        suiteTicketStoreService.saveSuiteTicket(suiteId, suiteTicket);
    }
}
