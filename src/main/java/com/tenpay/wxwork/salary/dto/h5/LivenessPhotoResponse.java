package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class LivenessPhotoResponse extends FrontEndResponse {
    @JsonProperty("stateFlag")
    private String stateFlag;

    @JsonProperty("picture")
    private String picture;

    public LivenessPhotoResponse(String retCode, String errMsg, String stateFlag, String picture) {
        super(retCode, errMsg);
        this.stateFlag = stateFlag;
        this.picture = picture;
    }

    public String getStateFlag() {
        return stateFlag;
    }

    public void setStateFlag(String stateFlag) {
        this.stateFlag = stateFlag;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
