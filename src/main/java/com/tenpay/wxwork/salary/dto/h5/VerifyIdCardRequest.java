package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifyIdCardRequest {

    @JsonProperty(value="id_card_photo", required=true)
    private String idCardPhoto;

    @JsonProperty(value="is_front_photo", required=true)
    private boolean isFrontPhoto;

    public String getIdCardPhoto() {
        return idCardPhoto;
    }

    public void setIdCardPhoto(String idCardPhoto) {
        this.idCardPhoto = idCardPhoto;
    }

    public boolean isFrontPhoto() {
        return isFrontPhoto;
    }

    public void setFrontPhoto(boolean frontPhoto) {
        isFrontPhoto = frontPhoto;
    }
}
