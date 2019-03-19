package com.tenpay.wxwork.wxworklib.service.impl;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.tenpay.wxwork.wxworklib.WxworkConfig;
import com.tenpay.wxwork.wxworklib.service.WxCryptService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaogangli on 2018/3/15.
 */
@Component
public class WxcryptServiceFactory {

    private final static Map<String, WxCryptService> INSTANCES = new HashMap<>();

    @Resource
    private WxworkConfig wxworkConfig;

    @PostConstruct
    public void init() {
        try {
            INSTANCES.put(wxworkConfig.corpId, new WxCryptServiceImpl(new WXBizMsgCrypt(wxworkConfig.token,
                    wxworkConfig.encodingAESKey, wxworkConfig.corpId)));

            for (WxworkConfig.Suite suite : wxworkConfig.suites) {
                INSTANCES.put(suite.id, new WxCryptServiceImpl(new WXBizMsgCrypt(suite.token, suite.encodingAESKey, suite.id)));
            }
        } catch (AesException e) {
            throw new RuntimeException(e);
        }
    }

    public WxCryptService getInstance(String id) {
        return INSTANCES.get(id);
    }

    public WxCryptService getCorpInstance() {
        return INSTANCES.get(wxworkConfig.corpId);
    }
}
