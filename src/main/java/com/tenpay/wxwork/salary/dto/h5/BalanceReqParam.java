package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BalanceReqParam extends FrontEndReqBase {
    @NotBlank(message = "ccyCd不能为空")
    @Length(max = 3)
    @JsonProperty(required=true)
    private String ccyCd; //币种代码

    @NotBlank(message = "cshEx_Cd不能为空")
    @Length(max = 1)
    @JsonProperty(required=true)
    private String cshEx_Cd; //钞汇代码

    public String getCcyCd() {
        return ccyCd;
    }

    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    public String getCshEx_Cd() {
        return cshEx_Cd;
    }

    public void setCshEx_Cd(String cshEx_Cd) {
        this.cshEx_Cd = cshEx_Cd;
    }
}
