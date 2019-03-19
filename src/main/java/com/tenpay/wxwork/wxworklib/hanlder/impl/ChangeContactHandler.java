package com.tenpay.wxwork.wxworklib.hanlder.impl;

import com.tenpay.wxwork.wxworklib.hanlder.AbstractInfoHandler;
import com.tenpay.wxwork.wxworklib.hanlder.InfoHanlder;
import com.tenpay.wxwork.wxworklib.hanlder.InfoType;
import com.tenpay.wxwork.wxworklib.service.AbstractContactBookService;
import com.tenpay.wxwork.wxworklib.utils.XmlUtils;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

/**
 * 通讯录变更事件处理
 * https://work.weixin.qq.com/api/doc#10982/%E9%80%9A%E8%AE%AF%E5%BD%95%E5%8F%98%E6%9B%B4%E4%BA%8B%E4%BB%B6%E9%80%9A%E7%9F%A5
 * Created by yaogangli on 2018/3/3.
 */
@InfoType(type = "change_contact")
public class ChangeContactHandler extends AbstractInfoHandler implements InfoHanlder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeContactHandler.class);

    @Resource
    private AbstractContactBookService abstractContactBookService;

    @Override
    public void hanle(Document document) {
        String changeType = XmlUtils.findNodeContent(document, "/xml/ChangeType");
        LOGGER.info("receive change_contact directive, ChangeType={}", changeType);

        if (changeType.equals("create_user")) {
            abstractContactBookService.createUser(document);
        } else if (changeType.equals("update_user")) {
            abstractContactBookService.updateUser(document);
        } else if (changeType.equals("delete_user")) {
            String corpid = XmlUtils.findNodeContent(document, "/xml/AuthCorpId");
            String userid = XmlUtils.findNodeContent(document, "/xml/UserID");
            abstractContactBookService.deleteUser(corpid, userid);
        } else {
            LOGGER.error("unknown ChangeType: {}", changeType);
        }
    }
}
