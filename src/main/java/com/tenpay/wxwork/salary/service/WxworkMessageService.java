package com.tenpay.wxwork.salary.service;

import com.tenpay.wxwork.salary.model.CorpInfo;
import com.tenpay.wxwork.salary.model.CorpAuthen;
import com.tenpay.wxwork.salary.dao.CorpInfoDAO;
import com.tenpay.wxwork.salary.dao.CorpAuthenDAO;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.config.ConfigUtils;

import com.google.gson.JsonObject;
import com.tenpay.wxwork.wxworklib.client.WxworkHttpClient;
import com.tenpay.wxwork.wxworklib.exception.WxworkException;
import com.tenpay.wxwork.wxworklib.service.AuthService;
import com.tenpay.wxwork.wxworklib.service.WxAccessTokenService;
import com.tenpay.wxwork.wxworklib.utils.UrlGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 企业微信发消息服务
 */
@Service
public class WxworkMessageService {

    private final static Logger LOGGER = LoggerFactory.getLogger(WxworkMessageService.class);

    @Resource
    private WxAccessTokenService wxAccessTokenService;

    @Resource
    private WxworkHttpClient wxworkHttpClient;

    @Autowired
    private CorpInfoDAO corpInfoDAO;

    @Autowired
    private CorpAuthenDAO corpAuthenDAO;

    public void sendOpenAccountMsg(String suiteId, String corpid, String userid) {
        String url = UrlGenerator.genUserLoginUrl(suiteId, ConfigUtils.getBaseUrl() + "/salary/h5/user_login", "state0");
        String msg = String.format(ConfigUtils.getOpenAccountMsgTemplate(), url);
        sendTextToUser(suiteId, corpid, userid, msg);
    }

    protected void sendTextToUser(String suiteId, String corpId, String userid, String textMsg) {
        CorpAuthen corpAuthen = corpAuthenDAO.queryByCorpidAndSuiteId(corpId, suiteId);
        if (null == corpAuthen) {
            LOGGER.error("no corp auth record for corpId: {} and suite id: {}", corpId, suiteId);
            throw new BizException(BizError.NO_CORP_AUTHEN);
        }

        String accessToken = wxAccessTokenService.getCorpAccessToken(suiteId, corpId, corpAuthen.getPermanentCode());
        wxworkHttpClient.sendTextToUser(accessToken, corpAuthen.getAgentId(), userid, textMsg);
    }

}
