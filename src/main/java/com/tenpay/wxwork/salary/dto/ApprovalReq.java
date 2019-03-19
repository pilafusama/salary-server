package com.tenpay.wxwork.salary.dto;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.model.CorpApprovalInfo;

public class ApprovalReq {
	//审批单标识
	@JsonProperty("id")
	private String id;
	//申请人姓名
	@JsonProperty("apply_name")
	private String apply_name;
	//提交时间
	@JsonProperty("apply_time")
	private String apply_time;
	//收款户名
	@JsonProperty("receive_name")
	private String receive_name;
	//收款帐户
	@JsonProperty("receive_account")
	private String receive_account;
	//付款金额
	@JsonProperty("total_fee")
	private long total_fee;
	//开户行
	@JsonProperty("account_bank")
	private String account_bank;
	//开户行所在地
	@JsonProperty("account_area")
	private String account_area;
	//是否跨行标记
	@JsonProperty("cross_flag")
	private int cross_flag;

	public ApprovalReq(CorpApprovalInfo corpApprovalInfo){
		this.setId(corpApprovalInfo.getApproval_id());
		this.setApplyName(corpApprovalInfo.getApply_name());
		this.setApplyTime(corpApprovalInfo.getApply_time());
		this.setReceiveName(corpApprovalInfo.getReceive_name());
		this.setReceiveAccount(corpApprovalInfo.getReceive_account());
		this.setTotalFee(corpApprovalInfo.getAmount());
		this.setAccountBank(corpApprovalInfo.getAccount_bank());
		this.setAccountArea(corpApprovalInfo.getAccount_area());
		this.setCrossFlag(corpApprovalInfo.getCross_flag());
		
		this.validate();
	}
	
	@JsonIgnore
	public String getId() {
		return id;
	}
	@JsonIgnore
	public void setId(String id) {
		this.id = id;
	}
	@JsonIgnore
	public String getApplyName() {
		return apply_name;
	}
	@JsonIgnore
	public void setApplyName(String apply_name) {
		this.apply_name = apply_name;
	}
	@JsonIgnore
	public String getApplyTime() {
		return apply_time;
	}
	@JsonIgnore
	public void setApplyTime(String apply_time) {
		this.apply_time = apply_time;
	}
	@JsonIgnore
	public String getReceiveName() {
		return receive_name;
	}
	@JsonIgnore
	public void setReceiveName(String receive_name) {
		this.receive_name = receive_name;
	}
	@JsonIgnore
	public String getReceiveAccount() {
		return receive_account;
	}
	@JsonIgnore
	public void setReceiveAccount(String receive_account) {
		this.receive_account = receive_account;
	}
	@JsonIgnore
	public long getTotalFee() {
		return total_fee;
	}
	@JsonIgnore
	public void setTotalFee(long total_fee) {
		this.total_fee = total_fee;
	}
	@JsonIgnore
	public String getAccountBank() {
		return account_bank;
	}
	@JsonIgnore
	public void setAccountBank(String account_bank) {
		this.account_bank = account_bank;
	}
	@JsonIgnore
	public String getAccountArea() {
		return account_area;
	}
	@JsonIgnore
	public void setAccountArea(String account_area) {
		this.account_area = account_area;
	}
	@JsonIgnore
	public int getCrossFlag() {
		return cross_flag;
	}
	@JsonIgnore
	public void setCrossFlag(int cross_flag) {
		this.cross_flag = cross_flag;
	}
	
	public void validate()
	{
		if(StringUtils.isBlank(this.getId())){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "id is empty.");
    	}
    	if(StringUtils.isBlank(this.getApplyName())){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "apply_name is empty.");
    	}
    	if(StringUtils.isBlank(this.getApplyTime())){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "apply_time is empty.");
    	}
    	if(StringUtils.isBlank(this.getReceiveName())){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "receive_name is empty.");
    	}
    	if(StringUtils.isBlank(this.getReceiveAccount())){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "receive_account is empty.");
    	}
    	if(0 == this.getTotalFee()){
    		throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "total_fee is 0.");
    	}
	}
}
