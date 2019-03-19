package com.tenpay.wxwork.salary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FrontEndResponse {
    //业务结果状态码
    @JsonProperty(value="retcode", required=true)
    private String retCode;

    //结果说明
    @JsonProperty(value="errmsg", required=true)
    private String errMsg;

    public FrontEndResponse(String retCode,String errMsg)
    {
        this.retCode = retCode;
        this.errMsg = errMsg;
    }

    public FrontEndResponse()
    {
        this.retCode = "0";
        this.errMsg = "OK";
    }

    @JsonIgnore
    public String getRetcode() {
        return retCode;
    }

    @JsonIgnore
    public void setRetcode(String retCode) {
        this.retCode = retCode;
    }

    @JsonIgnore
    public String getErrmsg() {
        return errMsg;
    }

    @JsonIgnore
    public void setErrmsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
