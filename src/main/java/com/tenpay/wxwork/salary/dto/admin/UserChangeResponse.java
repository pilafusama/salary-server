package com.tenpay.wxwork.salary.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class UserChangeResponse extends FrontEndResponse {
    @JsonProperty(value="isChange")
    private boolean isChange;
    @JsonProperty(value="userComein")
    private Long userComein;
    @JsonProperty(value="userLeave")
    private Long userLeave;

    private String month;

    @JsonIgnore
    public boolean isChange() {
        return isChange;
    }
    @JsonIgnore
    public void setChange(boolean change) {
        isChange = change;
    }
    @JsonIgnore
    public Long getUserComein() {
        return userComein;
    }
    @JsonIgnore
    public void setUserComein(Long userComein) {
        this.userComein = userComein;
    }
    @JsonIgnore
    public Long getUserLeave() {
        return userLeave;
    }
    @JsonIgnore
    public void setUserLeave(Long userLeave) {
        this.userLeave = userLeave;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
