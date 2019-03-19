package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;

public class AccountOpenedCallbackParam extends FrontEndReqBase {
    @JsonProperty("version")
    private String version;
    @JsonProperty("corpid")
    private String corpid;
    @JsonProperty("userid")
    private String userid;
    @JsonProperty("id_card_name")
    private String id_card_name;
    @JsonProperty("id_card_number")
    private String id_card_number;
    @JsonProperty("mobile_number")
    private String mobile_number;
    @JsonProperty("category1_card_number")
    private String category1_card_number;
    @JsonProperty("category2_card_number")
    private String category2_card_number;
    @JsonProperty("sign")
    private String sign;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getId_card_name() {
        return id_card_name;
    }

    public void setId_card_name(String id_card_name) {
        this.id_card_name = id_card_name;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getCategory1_card_number() {
        return category1_card_number;
    }

    public void setCategory1_card_number(String category1_card_number) {
        this.category1_card_number = category1_card_number;
    }

    public String getCategory2_card_number() {
        return category2_card_number;
    }

    public void setCategory2_card_number(String category2_card_number) {
        this.category2_card_number = category2_card_number;
    }

    @Override
    public String getSign() {
        return sign;
    }

    @Override
    public void setSign(String sign) {
        this.sign = sign;
    }
}
