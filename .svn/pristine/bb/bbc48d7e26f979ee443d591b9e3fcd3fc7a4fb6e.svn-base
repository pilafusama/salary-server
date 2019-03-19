package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class SendSmsCodeReqParam  extends FrontEndReqBase {

    @JsonProperty(value="scene", required=true)
    private String scene;

    @NotBlank(message = "Mobile不能为空")
    @Length(min = 11,max = 11)
    @JsonProperty(value="mobile_number", required=true)
    private String mobileNumber;

    @Length(max = 120)
    @JsonProperty(value="Crdt_No",required=false)
    private String crdt_no;

    @NotBlank(message = "signdata不能为空")
    @Length(max = 800)
    @JsonProperty(value="SIGNDATA", required=true)
    private String signdata;

    @JsonIgnore
    public String getScene() {
        return scene;
    }

    @JsonIgnore
    public void setScene(String scene) {
        this.scene = scene;
    }

    @JsonIgnore
    public String getMobileNumber() {
        return mobileNumber;
    }
    @JsonIgnore
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    @JsonIgnore
    public String getCrdt_no() {
        return crdt_no;
    }
    @JsonIgnore
    public void setCrdt_no(String crdt_no) {
        this.crdt_no = crdt_no;
    }
    @JsonIgnore
    public String getSigndata() {
        return signdata;
    }
    @JsonIgnore
    public void setSigndata(String signdata) {
        this.signdata = signdata;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Mobile=");
        sb.append(RelayCodeUtils.encode(this.getMobileNumber()));
        sb.append("&Crdt_No=");
        sb.append(RelayCodeUtils.encode(this.getCrdt_no()));
        sb.append("&SIGNDATA=");
        sb.append(RelayCodeUtils.encode(this.getSigndata()));
        sb.append("&bankType=");
        sb.append(RelayCodeUtils.encode(this.getBankType()));
        sb.append("&sign=");
        sb.append(RelayCodeUtils.encode(this.getSign()));
        sb.append("&nonce=");
        sb.append(RelayCodeUtils.encode(this.getNonce()));
        return sb.toString();
    }
}
