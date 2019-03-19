/**
 * 处理管理员身份相关逻辑
 *
 */
package com.tenpay.wxwork.salary.service.admin;

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
public class AdminService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private SessionService sessionService;

    @Resource
    private WxAccessTokenService wxAccessTokenService;

    @Resource
    private WxworkHttpClient wxworkHttpClient;

    public String login(String authCode) {

        JsonObject loginInfo = wxworkHttpClient.getLoginInfo(authCode);

        String corpid = loginInfo.get("corp_info").getAsJsonObject().get("corpid").getAsString();
        String userid = loginInfo.get("user_info").getAsJsonObject().get("userid").getAsString();
        if (StringUtils.isEmpty(corpid) || StringUtils.isEmpty(userid)) {
            throw new BizException(BizError.ILLEGAL_HTTARG_ERROR);
        }

        int usertype = loginInfo.get("usertype").getAsInt();
        // 1.创建者 2.内部系统管理员 3.外部系统管理员 4.分级管理员 5.成员
        if (1 != usertype && 2 != usertype && 4 != usertype) {
            LOGGER.error("usertype {} of userid {} not allowed to login as admin",
                         usertype, userid);
            throw new BizException(BizError.ILLEGAL_HTTARG_ERROR);
        }

        LOGGER.info("corp {} user {} login as admin", corpid, userid);
        String ssid = idGenerator.genAdminSessionId();
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setCorpId(corpid);
        sessionInfo.setUserId(userid);
        sessionInfo.setSessionId(ssid);
        sessionService.setSession(sessionInfo);

        return ssid;
    }
}
