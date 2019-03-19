package com.tenpay.wxwork.salary.provider.wxwork_server;

import com.qq.weixin.mp.aes.AesException;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.wxworklib.controller.AbstractSuiteController;
import com.tenpay.wxwork.wxworklib.service.AuthService;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.IdGenerator;

import com.tenpay.wxwork.salary.service.WxworkMessageService;
import com.tenpay.wxwork.salary.service.WxworkContactBookService;
import com.tenpay.wxwork.salary.config.ConfigUtils;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {

    @Resource
    private AuthService authService;

    @Resource
    private WxworkMessageService wxworkMessageService;

    @Resource
    private WxworkContactBookService wxworkContactBookService;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/test/test_install_page", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String testInstallPage() {
        String suiteId = ConfigUtils.getSuiteId();
        String redirectUrl = "http://testwxwork.tenpay.com/salary/pc/corp_auth_callback"; // 需要与下方 doCorpAuth 处理的一致
        String authUrl = authService.genCorpAuthUrl(suiteId, redirectUrl, suiteId);
        return String.format("<html><head> <title>install salary app</title> </head> <body><a href=\"%s\">click me</a> </body> </html>", authUrl);
    }

    @RequestMapping(value = "/test/send-msg", method = RequestMethod.GET)
    public String sendMsg(@RequestParam("corpid") String corpid,
                          @RequestParam("userid") String userid) {
        wxworkMessageService.sendOpenAccountMsg(ConfigUtils.getSuiteId(), corpid, userid);

        return "done";
    }

    @RequestMapping(value = "/test/user-login", method = RequestMethod.GET)
    public Object sendMsg(@RequestParam("corpid") String corpid,
                          @RequestParam("userid") String userid,
                          HttpServletResponse response) {
        String ssid = idGenerator.genSessionId();
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setCorpId(corpid);
        sessionInfo.setUserId(userid);
        sessionInfo.setSessionId(ssid);
        sessionService.setSession(sessionInfo);

        Cookie cookie = new Cookie("ssid", ssid);
        cookie.setMaxAge(3600 * 24); // TODO cookie得过期时间暂时一天吧
        cookie.setPath("/");
        response.addCookie(cookie);

        return new FrontEndResponse();
    }

    @RequestMapping(value = "/test/sync-address-book", method = RequestMethod.GET)
    public String syncContactBook(@RequestParam("corpid") String corpid) {
        wxworkContactBookService.syncContactBook(ConfigUtils.getSuiteId(), corpid);

        return "done";
    }

    @RequestMapping(value = "/test/oauth-welcome", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String welcome() {
        return "<html><body><h1>welcome to the free land!</h1></body></html>";
    }

}
