package com.tenpay.wxwork.salary.common.utils;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SendSmsCodeChange {

    /**
     * 1.E账户开户（DSS002）
     SH="1";SN="绑定账户";IN="0";SI="XXXX";|
     其中“XXXX”为DSS002的Rltv_AccNo字段内容
     2.维护E账户平台绑定信息（DSS012）
     SH="1";SN="E账户";IN="0";SI="XXXX";|
     其中“XXXX”为DSS012的Ebnk_Sign_Accno字段内容
     3.建行提现（DSS009）
     SH="1";SN="E账户";IN="0";SI="XXXX";|
     其中“XXXX”为DSS009的Cst_AccNo字段内容
     4.他行提现（DSS010）
     SH="1";SN="E账户";IN="0";SI="XXXX";|
     其中“XXXX”为DSS010的Py_Psn_AccNo字段内容
     */
    public final static String SendSmsCode_01= "SH=\"1\";SN=\"绑定账户\";IN=\"0\";SI=\"";
    public final static String SendSmsCode_02= "SH=\"1\";SN=\"E账户\";IN=\"0\";SI=\"";
    public final static String SendSmsCode_03= "SH=\"1\";SN=\"E账户\";IN=\"0\";SI=\"";

    public final static String OPEN_ACCOUNT ="OPEN_ACCOUNT"; //开户
    public final static String REUSE_ACCOUNT = "REUSE_ACCOUNT"; //使用已有账户
    public final static String FETCH = "FETCH"; //提现

    public static String changeCode(String type ,String str){
        str = str.replace(" ","");
        String returnValue = "";
        switch (type){
            case OPEN_ACCOUNT: returnValue += SendSmsCode_01 + str; break;
            case REUSE_ACCOUNT: returnValue += SendSmsCode_02 + str; break;
            case FETCH: returnValue += SendSmsCode_03 + str; break;
            default: throw new BizException(BizError.SEND_SMS_SCENE_ERROR.errorCode(), BizError.SEND_SMS_SCENE_ERROR.errorMsg());
        }
        returnValue += "\";|";
        /*try {
            returnValue = URLEncoder.encode(returnValue,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return  returnValue;
    }
}
