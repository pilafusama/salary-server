package com.tenpay.wxwork.salary.dto.h5;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;

public class GetUserInfoReqParam extends FrontEndReqBase
{
	//银行标识
    @NotBlank(message = "BankType不能为空")
    @Length(min = 4, max = 4)
    @JsonProperty(required=true)
    private String BankType;

	public String getBankType()
	{
		return BankType;
	}

	public void setBankType(String bankType)
	{
		BankType = bankType;
	}
}
