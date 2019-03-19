package com.tenpay.wxwork.salary.provider;

import com.tenpay.wxwork.salary.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {

    protected final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

    /**
     * 响应用户从企业微信跳转链接中登录
     *
     */
	@RequestMapping(value = "/h5/user_login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String login(@RequestParam("code") String code, @RequestParam String state,
                        HttpServletResponse response) {

        if (code.length() != 0) {
            try {
                String ssid = userService.login(code, state);
                Cookie cookie = new Cookie("ssid", ssid);
                cookie.setMaxAge(3600 * 24); // TODO cookie得过期时间暂时一天吧，改为与 session 的略小
                cookie.setPath("/");
                response.addCookie(cookie);
            } catch (Throwable e) {
                LOGGER.error("failed to login: {}", e);
            }
        }

        return "redirect:/salary_web/h5/";
    }
}
