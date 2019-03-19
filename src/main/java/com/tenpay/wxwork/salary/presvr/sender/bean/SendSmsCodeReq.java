package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.SendSmsCodeReqParam;
import org.apache.commons.lang3.StringUtils;

public class SendSmsCodeReq extends BankProxyRelayRequestMsg{

    private static final String MOBILE = "mobile";
    private static final String CRDT_NO = "crdt_no";
    private static final String SIGNDATA = "signdata";

    private String mobile;
    private String crdt_no;
    private String signdata;

    public SendSmsCodeReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }

    public  void setRequest(SendSmsCodeReqParam request, String bankType){
        this.setMobile(request.getMobileNumber());
        this.setCrdt_no(request.getCrdt_no());
        this.setSigndata(request.getSigndata());
        this.validate();

    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.put(MOBILE,mobile);
        this.mobile = mobile;
    }

    public String getCrdt_no() {
        return crdt_no;
    }

    public void setCrdt_no(String crdt_no) {
        this.put(CRDT_NO,crdt_no);
        this.crdt_no = crdt_no;
    }


    public String getSigndata() {
        return signdata;
    }

    public void setSigndata(String signdata) {
        this.put(SIGNDATA,signdata);
        this.signdata = signdata;
    }

    private void validate()
    {
        if(StringUtils.isBlank(this.getMobile())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Mobile_Number is empty.");
        }
    }
}
