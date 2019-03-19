package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.dto.h5.BankVerifyReqParam;
import com.tenpay.wxwork.salary.dto.h5.OpenSalaryAccountResponse;
import com.tenpay.wxwork.salary.service.h5.BindCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class BindCardController {

    private static final Logger logger = LoggerFactory.getLogger(BindCardController.class);

    @Autowired
    private BindCardService bankVerifyService;

    @RequestMapping(value = "/h5/bind_card",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object verifyUser(@CookieValue("ssid") String ssid, @RequestBody BankVerifyReqParam bankVerifyReqParam) {
        logger.debug("bind card");
        //调用腾讯内部接口进行银行卡四要素核验
        OpenSalaryAccountResponse openSalaryAccountResponse = bankVerifyService.bankVerify(ssid, bankVerifyReqParam.getRltv_accno());
        return openSalaryAccountResponse;
    }

}
