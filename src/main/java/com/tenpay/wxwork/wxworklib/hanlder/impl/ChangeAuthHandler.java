package com.tenpay.wxwork.wxworklib.hanlder.impl;

import com.tenpay.wxwork.wxworklib.hanlder.AbstractInfoHandler;
import com.tenpay.wxwork.wxworklib.hanlder.InfoHanlder;
import com.tenpay.wxwork.wxworklib.hanlder.InfoType;
import com.tenpay.wxwork.wxworklib.service.AuthService;
import org.w3c.dom.Document;

import javax.annotation.Resource;

import static com.tenpay.wxwork.wxworklib.utils.XmlUtils.findNodeContent;

/**
 * 变更授权通知
 * https://work.weixin.qq.com/api/doc#10982/%E5%8F%98%E6%9B%B4%E6%8E%88%E6%9D%83%E9%80%9A%E7%9F%A5
 * Created by yaogangli on 2018/3/2.
 */
@InfoType(type = "change_auth")
public class ChangeAuthHandler extends AbstractInfoHandler implements InfoHanlder {

    @Resource
    private AuthService authService;

    @Override
    public void hanle(Document document) {
        String suiteId = getSuiteId(document);
        String corpId = findNodeContent(document, "/xml/AuthCorpId");

        authService.changeAuth(suiteId, corpId);
    }
}
