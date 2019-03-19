/**
 * 金融网关，在金融网关提供的代码之上的封装
 *
 */
package com.tenpay.wxwork.salary.client;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.model.CardBin;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

import com.tenpay.fingate.FundTransferService;

import com.tenpay.wxwork.salary.model.SmsCode;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import java.util.Map;

@Component
@DependsOn("configUtils") // 配置 bean 必须先创建
public class FinGateClient {
    private FundTransferService finGateService;

    public FinGateClient() {

        finGateService = new FundTransferService(ConfigUtils.getFinGateAppid(),
                                                 ConfigUtils.getFinGateAppkey(),
                                                 ConfigUtils.getFinGateCardNumberEncryptPasswd(),
                                                 ConfigUtils.getFinGateL5ModId(),
                                                 ConfigUtils.getFinGateL5CmdId(),
                                                 ConfigUtils.getFinGateL5Timeout());

        finGateService.setCardbinQueryUrl(ConfigUtils.getFinGateCardbinUrl() + "?");
        finGateService.setSmsCodeSendUrl(ConfigUtils.getSmsCodeSendUrl()+"?");
        finGateService.setSmsCodeVerifyUrl(ConfigUtils.getSmsCodeVerifyUrl()+"?");
    }

    public CardBin queryCardBin(String cardNumber) {
        String cardNumberCompat = cardNumber.replace(" ", "");
        Map<String, String> result = finGateService.queryCardBin(cardNumberCompat);
        if (!result.get("FinalResult").equals("0")) {
    		throw new BizException(BizError.CARDBIN_QUERY.errorCode(), result.get("FinalError"));
        }

        CardBin cardbin = new CardBin();
        cardbin.setCardNumber(cardNumberCompat);
        cardbin.setBankSname(result.get("bank_sname"));
        cardbin.setCardType(Integer.parseInt(result.get("bankacc_type")));
        return cardbin;
    }

    public SmsCode smsCodeSend(String phoneNumber, String uin){
        Map<String, String> result = finGateService.smsCodeSend(phoneNumber, uin);
        if (!result.get("result").equals("0")) {
            throw new BizException(BizError.SMS_CODE_SEND_FAIL.errorCode(), result.get("res_info"));
        }
        SmsCode smsCode = new SmsCode();
        smsCode.setFpgSmsGid(result.get("fpg_sms_gid"));
        return smsCode;
    }

    public FrontEndResponse smsCodeVerify(String mobileNo,String verifyCode, String fpgSmsGid,  String uin){
        Map<String, String> result = finGateService.smsCodeVerify(mobileNo, verifyCode, fpgSmsGid, uin);
        if (!result.get("result").equals("0")) {
            throw new BizException(BizError.SMS_CODE_VERIFY_FAIL.errorCode(), result.get("res_info"));
        }
        return new FrontEndResponse(result.get("result"),result.get("res_info"));
    }
}