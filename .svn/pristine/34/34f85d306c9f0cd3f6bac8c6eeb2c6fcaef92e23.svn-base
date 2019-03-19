package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.h5.SendSmsCodeReqParam;
import com.tenpay.wxwork.salary.dto.h5.SmsCodeSendReqPamam;
import com.tenpay.wxwork.salary.service.h5.SendSmsCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class SendSmsCodeController {

    private static final Logger logger = LoggerFactory.getLogger(SendSmsCodeController.class);

    @Autowired
    private SendSmsCodeService sendSmsCodeService;

    @RequestMapping(value = "/h5/send_sms_code",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object sendSmsCode(@CookieValue("ssid") String ssid,
                                        @RequestBody SendSmsCodeReqParam request)
    {
        //银行短信验证码接口
        logger.debug("send sms code");
        //1、验证登录
        //2、调用银行接口，发送短信验证码
        sendSmsCodeService.sendSmsCode(ssid, request);
        return new FrontEndResponse();
    }

    @RequestMapping(value = "/h5/send_sms_code_tx",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object sendSmsCodeTX(@CookieValue("ssid") String ssid,
                              @RequestBody SmsCodeSendReqPamam request)
    {
        //腾讯短信验证码接口
        logger.debug("send sms code tx");
        //1、验证登录
        sendSmsCodeService.sendSmsCodeTX(ssid, request);
        return new FrontEndResponse();
    }

    @RequestMapping(value = "/h5/sms_code_verify_tx",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object smsCodeVerifyTX(@CookieValue("ssid") String ssid,
                                @RequestBody SmsCodeSendReqPamam request)
    {
        //腾讯短信验证码接口
        logger.debug("sms code verify tx");
        //1、验证登录
        FrontEndResponse frontEndResponse = sendSmsCodeService.smsCodeVerifyTX(ssid, request);
        return frontEndResponse;
    }
}
