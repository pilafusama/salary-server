package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/19
 */
public class GestCheckResponse extends FrontEndResponse {
    @JsonProperty("stateFlag")
    private String stateFlag;

    public GestCheckResponse(String retCode,String errMsg,String stateFlag){

        super(retCode,errMsg);
        this.stateFlag = stateFlag;
    }


    public String getStateFlag() {
        return stateFlag;
    }

    public void setStateFlag(String stateFlag) {
        this.stateFlag = stateFlag;
    }
}
