package com.tenpay.wxwork.salary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;

public class ApprovalNotifyReqParam extends FrontEndReqBase{
	//银行标识
	@JsonProperty(required=true)
	private String bank_appid;
	
	//银行登录帐号
	@JsonProperty(required=true)
	private String bank_uin;
	
	//企业标识
	@JsonProperty(required=true)
	private String corp_id;
	
	//审批流标识
	@JsonProperty(required=true)
	private String id;
	
	//银行受理标识
	private String bank_listid;
	
	//付款状态
	@JsonProperty(required=true)
	private String pay_status;
	
	//付款说明
	@JsonProperty(required=true)
	private String memo;
	
	//付款完成时间
	@JsonProperty(required=true)
	private String pay_time;
		
	//签名
	@JsonProperty(required=true)
	private String sign;

	//时间戳
	@JsonProperty(required=true)
	private String timestamp;
	
	//随机串
	@JsonProperty(required=true)
	private String nonce;

	public String getBankAppid() {
		return bank_appid;
	}

	public void setBankAppid(String bank_appid) {
		this.bank_appid = bank_appid;
	}

	@JsonIgnore
	public String getBankUin() {
		return bank_uin;
	}

	@JsonIgnore
	public void setBankUin(String bank_uin) {
		this.bank_uin = bank_uin;
	}

	@JsonIgnore
	public String getCorpId() {
		return corp_id;
	}

	@JsonIgnore
	public void setCorpId(String corp_id) {
		this.corp_id = corp_id;
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
	public String getBankListid() {
		return bank_listid;
	}

	@JsonIgnore
	public void setBankListid(String bank_listid) {
		this.bank_listid = bank_listid;
	}

	@JsonIgnore
	public String getPayStatus() {
		return pay_status;
	}

	@JsonIgnore
	public void setPayStatus(String pay_status) {
		this.pay_status = pay_status;
	}

	@JsonIgnore
	public String getMemo() {
		return memo;
	}

	@JsonIgnore
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@JsonIgnore
	public String getPayTime() {
		return pay_time;
	}

	@JsonIgnore
	public void setPayTime(String pay_time) {
		this.pay_time = pay_time;
	}

	@JsonIgnore
	public String getSign() {
		return sign;
	}
	@JsonIgnore
	public void setSign(String sign) {
		this.sign = sign;
	}
		
	@JsonIgnore
	public String getNonce() {
		return nonce;
	}

	@JsonIgnore
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("bank_appid=");
		sb.append(RelayCodeUtils.encode(this.getBankAppid()));
		sb.append("&bank_uin=");
		sb.append(RelayCodeUtils.encode(this.getBankUin()));
		sb.append("&corp_id=");
		sb.append(RelayCodeUtils.encode(this.getCorpId()));
		sb.append("&id=");
		sb.append(RelayCodeUtils.encode(this.getId()));
		sb.append("&bank_listid=");
		sb.append(RelayCodeUtils.encode(this.getBankListid()));
		sb.append("&pay_status=");
		sb.append(RelayCodeUtils.encode(this.getPayStatus()));
		sb.append("&memo=");
		sb.append(RelayCodeUtils.encode(this.getMemo()));
		sb.append("&pay_time=");
		sb.append(RelayCodeUtils.encode(this.getPayTime()));
		sb.append("&sign=");
		sb.append(RelayCodeUtils.encode(this.getSign()));
		sb.append("&nonce=");
		sb.append(RelayCodeUtils.encode(this.getNonce()));
	return sb.toString();
	}
}
