package com.tenpay.wxwork.salary.service;

import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.IdGenerator;

import com.tenpay.wxwork.wxworklib.client.WxworkHttpClient;
import com.tenpay.wxwork.wxworklib.exception.WxworkException;
import com.tenpay.wxwork.wxworklib.service.AuthService;
import com.tenpay.wxwork.wxworklib.service.WxAccessTokenService;
import com.tenpay.wxwork.wxworklib.service.AbstractContactBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private SessionService sessionService;

    @Resource
    private WxAccessTokenService wxAccessTokenService;

    @Resource
    private WxworkHttpClient wxworkHttpClient;

    // @Autowired
    // private CorpInfoDAO corpInfoDAO;

    // @Autowired
    // private CorpAuthenDAO corpAuthenDAO;

    // @Autowired
    // private UserInfoDAO userInfoDAO;

    public String login(String code, String state) {

        String suiteId = ConfigUtils.getSuiteId();
        JsonObject basicObject = wxworkHttpClient.getBasicUserInfoAsSuite(suiteId, code);
        String corpid = basicObject.get("CorpId").getAsString();
        String userid = basicObject.get("UserId").getAsString();
        if (StringUtils.isEmpty(corpid) || StringUtils.isEmpty(userid)) {
            throw new BizException(BizError.ILLEGAL_HTTARG_ERROR);
        }

        String ssid = idGenerator.genSessionId();
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setCorpId(corpid);
        sessionInfo.setUserId(userid);
        sessionInfo.setSessionId(ssid);
        sessionService.setSession(sessionInfo);

        // JsonObject detailedObject = wxworkHttpClient.getDetailedUserInfoAsSuite(suiteId, basicObject.get("user_ticket").getAsString());

        return ssid;
    }
}
