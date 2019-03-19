package com.tenpay.wxwork.salary.provider.admin;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.admin.DownLoadSalaryReq;
import com.tenpay.wxwork.salary.dto.admin.UplaodPayroll;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.admin.AdminService;
import com.tenpay.wxwork.salary.service.admin.DownLoadSalaryService;
import com.tenpay.wxwork.salary.util.download.ExcelDownloader;
import com.tenpay.wxwork.salary.util.download.ExcelUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hxyh on 2018/8/22.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

	@RequestMapping(value = "/login",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login(@RequestParam("auth_code") String authCode,
                        HttpServletResponse response) {

        if (authCode.length() != 0) {
            try {
                String ssid = adminService.login(authCode);
                Cookie cookie = new Cookie("ssid", ssid);
                cookie.setMaxAge(3600 * 24); // TODO cookie得过期时间暂时一天吧，改为与 session 的略小
                cookie.setPath("/");
                response.addCookie(cookie);
            } catch (Throwable e) {
                LOGGER.error("failed to login as admin: {}", e);
            }
        }

        return "redirect:/salary_web/h5/#/admin/salaryDetail";
    }
}
