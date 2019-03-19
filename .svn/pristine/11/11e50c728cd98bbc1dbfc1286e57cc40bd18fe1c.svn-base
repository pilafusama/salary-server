package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.h5.AccountOpenedCallbackParam;
import com.tenpay.wxwork.salary.service.h5.AccountOpenedCallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountOpenedCallbackController {

    @Autowired
    private AccountOpenedCallbackService accountOpenedCallbackService;

    @PostMapping(value = "/h5/account_opened_callback", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object accountOpendedCallback(@CookieValue("ssid") String ssid, @RequestBody AccountOpenedCallbackParam accountOpenedCallbackParam){
        /**
         * 登录态校验
         * 检验银行签名
         * 调用银行接口，确认四要素信息正确
         * 开户信息计入 db
         */
        accountOpenedCallbackService.accountOpendedCallback(ssid, accountOpenedCallbackParam);
        return new FrontEndResponse();
    }
}
