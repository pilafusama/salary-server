package com.tenpay.wxwork.wxworklib.hanlder.impl;

import com.tenpay.wxwork.wxworklib.hanlder.AbstractInfoHandler;
import com.tenpay.wxwork.wxworklib.hanlder.InfoHanlder;
import com.tenpay.wxwork.wxworklib.hanlder.InfoType;
import com.tenpay.wxwork.wxworklib.service.AuthService;
import com.tenpay.wxwork.wxworklib.utils.XmlUtils;
import org.w3c.dom.Document;

import javax.annotation.Resource;

/**
 * 授权成功，通过应用市场安装和推广二维码安装会发此指令
 * https://work.weixin.qq.com/api/doc#10982/%E6%8E%88%E6%9D%83%E6%88%90%E5%8A%9F%E9%80%9A%E7%9F%A5
 * Created by yaogangli on 2018/3/2.
 */
@InfoType(type = "create_auth")
public class CreateAuthHandler extends AbstractInfoHandler implements InfoHanlder {

    @Resource
    private AuthService authService;

    @Override
    public void hanle(Document document) {
        String suiteId = getSuiteId(document);
        String authCode = XmlUtils.findNodeContent(document, "/xml/AuthCode");
        authService.createAuth(suiteId, authCode);
    }
}
