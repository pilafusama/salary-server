package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/19
 */
public class GestCheckReqParam extends FrontEndReqBase {

    @NotBlank(message = "userID不能为空")
    @Length(min = 1, max = 100)
    @JsonProperty(required=true)
    private String userId;

    @NotBlank(message = "fgesturePassword不能为空")
    @Length(min = 1, max = 100)
    @JsonProperty(value="gesture_password", required=true)
    private String fgesturePassword;

    @NotBlank(message = "fcorpid不能为空")
    @Length(min = 1, max = 100)
    @JsonProperty(required=true)
    private String fcorpId;
    private int fstatus;
    private int finitSatatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFgesturePassword() {
        return fgesturePassword;
    }

    public void setFgesturePassword(String fgesturePassword) {
        this.fgesturePassword = fgesturePassword;
    }

    public String getFcorpId() {
        return fcorpId;
    }

    public void setFcorpId(String fcorpId) {
        this.fcorpId = fcorpId;
    }

    public int getFstatus() {
        return fstatus;
    }

    public void setFstatus(int fstatus) {
        this.fstatus = fstatus;
    }

    public int getFinitSatatus() {
        return finitSatatus;
    }

    public void setFinitSatatus(int finitSatatus) {
        this.finitSatatus = finitSatatus;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("userID=");
        sb.append(RelayCodeUtils.encode(this.getUserId()));
        sb.append("&fgesturePassword=");
        sb.append(RelayCodeUtils.encode(this.getFgesturePassword()));
        sb.append("&fcorpId=");
        sb.append(RelayCodeUtils.encode(this.getFcorpId()));
        return sb.toString();
    }
}
