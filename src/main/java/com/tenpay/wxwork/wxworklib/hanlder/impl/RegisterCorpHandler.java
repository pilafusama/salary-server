package com.tenpay.wxwork.wxworklib.hanlder.impl;

import com.tenpay.wxwork.wxworklib.hanlder.AbstractInfoHandler;
import com.tenpay.wxwork.wxworklib.hanlder.InfoHanlder;
import com.tenpay.wxwork.wxworklib.hanlder.InfoType;
import com.tenpay.wxwork.wxworklib.service.AbstractRegisterCorpService;
import org.w3c.dom.Document;

import javax.annotation.Resource;

/**
 * 注册完成回调事件
 * https://work.weixin.qq.com/api/doc#90001/90143/90585
 */
@InfoType(type = "register_corp")
public class RegisterCorpHandler extends AbstractInfoHandler implements InfoHanlder{
    @Resource
    private AbstractRegisterCorpService abstractRegisterCorpService;

    @Override
    public void hanle(Document document) {
        abstractRegisterCorpService.createCorpConfig(document);
    }
}
