package com.tenpay.wxwork.salary.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

/**
 * 从审批流拉取的response中，消息码JsonProperty(value="retCode")
 * 而薪管家前端添加响应拦截器是对retcode作判断
 * 因此需要重新封装
 * @author v_dongzhao
 *
 */
public class SalaryApprovalStateResponse extends FrontEndResponse {
	
	@JsonProperty("stateFlag")
	private String stateFlag;

	public String getStateFlag() {
		return stateFlag;
	}

	public void setStateFlag(String stateFlag) {
		this.stateFlag = stateFlag;
	}

	public SalaryApprovalStateResponse(String retCode, String errMsg, String stateFlag) {
		super(retCode, errMsg);
		this.stateFlag = stateFlag;
	}

}
