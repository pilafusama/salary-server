package com.tenpay.wxwork.salary.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalaryApprovalResponse {
	@JsonProperty(value="retCode", required=true)
	private String retCode;
	
	//结果说明
	@JsonProperty(value="errMsg", required=true)
	private String errMsg;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public SalaryApprovalResponse() {
		super();
	}

	public SalaryApprovalResponse(String retCode, String errMsg) {
		super();
		this.retCode = retCode;
		this.errMsg = errMsg;
	}
	
}
