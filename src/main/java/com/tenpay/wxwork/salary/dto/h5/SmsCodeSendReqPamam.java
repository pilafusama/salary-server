package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.NotBlank;

public class SmsCodeSendReqPamam  extends FrontEndReqBase {
    @NotBlank(message = "Mobile不能为空")
    @JsonProperty(value="mobile_number", required=true)
    private String mobileNumber;

    @JsonProperty(value="verify_code")
    private String verifyCode;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
