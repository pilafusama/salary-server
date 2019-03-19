package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadLivenessDetectionVideoRequest
{
    @JsonProperty(value="compress_flag", required=true)
    private Boolean compressFlag;

    @JsonProperty(value="number", required=true)
    private String number;

    @JsonProperty(value="video", required=true)
    private String video;

    @JsonProperty(value="type", required=true)
    private Boolean type;//用于判断是忘记手势密码还是正常流程

    public String getVideo()
    {
        return video;
    }

    public void setVideo(String video)
    {
        this.video = video;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getCompressFlag() {
        return compressFlag;
    }

    public void setCompressFlag(Boolean compressFlag) {
        this.compressFlag = compressFlag;
    }
}
