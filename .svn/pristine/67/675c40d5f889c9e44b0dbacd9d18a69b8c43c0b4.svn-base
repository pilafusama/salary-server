package com.tenpay.wxwork.salary.dto;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

//付款
public class Flow1003DetailReq{
	//审批流类型
	@JsonProperty("type")
	private String type;
	
	//事由
	@JsonProperty("reason")
	private String reason;
	
	//备注
	@JsonProperty("common")
	private String common;
	
	//发生时间
	@JsonProperty("pay_time")
	private String pay_time;
	
	@JsonIgnore
	public String getType() {
		return type;
	}
	@JsonIgnore
	public void setType(String type) {
		this.type = type;
	}
	@JsonIgnore
	public String getReason() {
		return reason;
	}
	@JsonIgnore
	public void setReason(String reason) {
		this.reason = reason;
	}
	@JsonIgnore
	public String getCommon() {
		return common;
	}
	@JsonIgnore
	public void setCommon(String common) {
		this.common = common;
	}
	@JsonIgnore
	public String getPayTime() {
		return pay_time;
	}
	@JsonIgnore
	public void setPayTime(String pay_time) {
		this.pay_time = pay_time;
	}

	public void validate()
	{
		if(StringUtils.isBlank(this.getType()) || !this.getType().equals("1003")){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "type is empty or not correct.");
    	}
    	if(StringUtils.isBlank(this.getReason())){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "reason is empty.");
    	}
    	if(StringUtils.isBlank(this.getPayTime())){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "pay_time is empty.");
    	}
	}
}
