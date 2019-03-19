package com.tenpay.wxwork.salary.dto.deepsea;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LivenessDetectionNumberResponse extends DeepseaResponse {

    private String number;

    private String token;

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("number: %s, token: %s, %s", number, token, super.toString());
    }
}