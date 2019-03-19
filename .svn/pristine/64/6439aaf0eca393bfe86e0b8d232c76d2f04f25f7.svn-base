package com.tenpay.wxwork.salary.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAccountModeInfo {
    @JsonProperty("is_open_account_allowed")
    private Boolean isOpenAccountAllowed; //当前是否允许开户
    @JsonProperty("open_account_start_time")
    private String openAccountStartTime; //开户起始时间
    @JsonProperty("open_account_end_time")
    private String openAccountEndTime; //开户截止时间
    @JsonProperty("is_liveness_detection_needed")
    private Boolean isLivenessDetectionNeeded; //是否需要人脸识别

    public OpenAccountModeInfo(Boolean isOpenAccountAllowed, String openAccountStartTime, String openAccountEndTime, Boolean isLivenessDetectionNeeded) {
        this.isOpenAccountAllowed = isOpenAccountAllowed;
        this.openAccountStartTime = openAccountStartTime;
        this.openAccountEndTime = openAccountEndTime;
        this.isLivenessDetectionNeeded = isLivenessDetectionNeeded;
    }

    public Boolean getOpenAccountAllowed() {
        return isOpenAccountAllowed;
    }

    public void setOpenAccountAllowed(Boolean openAccountAllowed) {
        isOpenAccountAllowed = openAccountAllowed;
    }

    public String getOpenAccountStartTime() {
        return openAccountStartTime;
    }

    public void setOpenAccountStartTime(String openAccountStartTime) {
        this.openAccountStartTime = openAccountStartTime;
    }

    public String getOpenAccountEndTime() {
        return openAccountEndTime;
    }

    public void setOpenAccountEndTime(String openAccountEndTime) {
        this.openAccountEndTime = openAccountEndTime;
    }

    public Boolean getLivenessDetectionNeeded() {
        return isLivenessDetectionNeeded;
    }

    public void setLivenessDetectionNeeded(Boolean livenessDetectionNeeded) {
        isLivenessDetectionNeeded = livenessDetectionNeeded;
    }
}
