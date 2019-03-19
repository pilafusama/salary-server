package com.tenpay.wxwork.salary.dto;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

//费用
public class Flow1002DetailReq{
	//审批流类型
	@JsonProperty("type")
	private String type;
	
	//费用类型
	@JsonProperty("expense_type")
	private String expense_type;
	
	//事由
	@JsonProperty("reason")
	private String reason;
	
	//备注
	@JsonProperty("common")
	private String common;
	
	//发生时间
	@JsonProperty("time")
	private String time;
	
	@JsonIgnore
	public String getType() {
		return type;
	}
	@JsonIgnore
	public void setType(String type) {
		this.type = type;
	}
	@JsonIgnore
	public String getExpenseType() {
		return expense_type;
	}
	@JsonIgnore
	public void setExpenseType(String expense_type) {
		this.expense_type = expense_type;
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
	public String getTime() {
		return time;
	}
	@JsonIgnore
	public void setTime(String time) {
		this.time = time;
	}
	
	public void validate()
	{
		if(StringUtils.isBlank(this.getType()) || !this.getType().equals("1002")){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "type is empty or not correct.");
    	}
    	if(StringUtils.isBlank(this.getExpenseType())){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "expense_type is empty.");
    	}
    	if(StringUtils.isBlank(this.getReason())){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "reason is empty.");
    	}
    	if(StringUtils.isBlank(this.getTime())){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "time is empty.");
    	}
	}
}
