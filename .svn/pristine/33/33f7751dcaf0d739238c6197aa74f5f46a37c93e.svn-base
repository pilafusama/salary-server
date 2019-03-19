package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.h5.GestCheckResponse;
import com.tenpay.wxwork.salary.dto.h5.QueryEBindReqParam;
import com.tenpay.wxwork.salary.dto.h5.VerifyUserResponse;
import com.tenpay.wxwork.salary.model.User;
import com.tenpay.wxwork.salary.service.h5.VerifyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class VerifyUserController {

    private static final Logger logger = LoggerFactory.getLogger(VerifyUserController.class);

    @Autowired
    private VerifyUserService verifyUserService;

    @RequestMapping(value = "/h5/verify_user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object verifyUser(@CookieValue("ssid") String ssid)
    {
        logger.debug("verify user");
        //1、参数检查，前置条件检查
        //2、调用银行接口“客户识别”，核查用户身份
        //3、若核查成功，更新状态；把银行的核查流水号放在session中，salary_bank_verify_sn

        try {
            verifyUserService.verifyUser(ssid);
        }catch (BizException e){
            if(e.getRetCode()== BizError.BANK_ERROR_MES.errorCode()){
                //如果是银行的错误，将银行返回的错误信息返回给用户
                return new GestCheckResponse("0",e.getErrMsg(),"1");
            }else {
                //系统错误
                return new GestCheckResponse("0",BizError.DEFAULT_ERROR_MSG.errorMsg(),"2");
            }

        }

        return new GestCheckResponse("0","OK","0");
    }
}
