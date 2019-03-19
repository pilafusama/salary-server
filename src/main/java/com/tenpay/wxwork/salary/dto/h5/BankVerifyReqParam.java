package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class BankVerifyReqParam extends FrontEndResponse {
    @JsonProperty("rltv_accno")
    private String rltv_accno;
    @JsonProperty
    public String getRltv_accno() {
        return rltv_accno;
    }
    @JsonProperty
    public void setRltv_accno(String rltv_accno) {
        this.rltv_accno = rltv_accno;
    }
}
