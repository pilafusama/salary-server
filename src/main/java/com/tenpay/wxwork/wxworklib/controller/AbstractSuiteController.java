package com.tenpay.wxwork.wxworklib.controller;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.qq.weixin.mp.aes.AesException;

import com.tenpay.wxwork.wxworklib.WxworkConfig;
import com.tenpay.wxwork.wxworklib.service.WxCryptService;
import com.tenpay.wxwork.wxworklib.service.impl.WxCryptServiceImpl;
import com.tenpay.wxwork.wxworklib.service.impl.WxcryptServiceFactory;
import com.tenpay.wxwork.wxworklib.hanlder.InfoHandlerFactory;
import com.tenpay.wxwork.wxworklib.hanlder.InfoHanlder;

import org.w3c.dom.Document;
import static com.tenpay.wxwork.wxworklib.utils.XmlUtils.findNodeContent;
import static com.tenpay.wxwork.wxworklib.utils.XmlUtils.newDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 第三方应用事件控制基类
 * Created by yaogangli on 2018/3/15.
 */
abstract public class AbstractSuiteController {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractSuiteController.class);

    @Resource
    protected WxworkConfig wxworkConfig;

    @Resource
    protected WxcryptServiceFactory wxcryptServiceFactory;

    @Resource
    private InfoHandlerFactory infoHandlerFactory;

    /**
     * 供企业微信回调验证 URL 有效
     *
     */
    protected String verify(int type, String msgSignature, String timestamp, String nonce, String echostr) {
        String result = "";
        try {
            WxworkConfig.Suite suite = wxworkConfig.getSuite(type);
            if (suite == null){
                LOGGER.info("suite:{}", suite);
            }else {
                WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(suite.token, suite.encodingAESKey, wxworkConfig.corpId);
                WxCryptService wxCryptService = new WxCryptServiceImpl(wxBizMsgCrypt);
                result = wxCryptService.verifyURL(msgSignature, timestamp, nonce, echostr);
                LOGGER.info("result:{}", result);
            }
        } catch (Exception e) {
            LOGGER.error("verify url error", e);
        } finally {
            LOGGER.info("finally......result={}", result);
        }
        return result;
    }

    /**
     * 根据回调指令，分发处理
     *
     */
    public String handleDirective(String msgSignature, String timestamp, String nonce,
                          String postData) throws AesException {
        LOGGER.debug("receive xml:{}", postData);

        // 先解析ToUserName，即suiteId
        String suiteId = findNodeContent(newDocument(postData), "/xml/ToUserName");

        WxCryptService wxCryptService = wxcryptServiceFactory.getInstance(suiteId);
        String message = wxCryptService.decryptMsg(msgSignature, timestamp, nonce, postData);
        LOGGER.debug("decrypt xml:{}", message);

        // 解析xml
        Document document = newDocument(message);

        // 找到infoType
        String infoType = findNodeContent(document, "/xml/InfoType");

        // 根据infoType找到InfoHanlder
        InfoHanlder infoHanlder = infoHandlerFactory.getInfoHanlder(infoType);
        if (infoHanlder == null) {
            LOGGER.error("can not find InfoHandler by InfoType={}", infoType);
        } else {
            infoHanlder.hanle(document);
        }

        return "success";
    }

}
