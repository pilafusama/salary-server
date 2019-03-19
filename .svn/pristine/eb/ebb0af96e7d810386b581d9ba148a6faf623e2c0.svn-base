package com.tenpay.wxwork.salary.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;

public class CorpBindConfirmReqParam extends FrontEndReqBase{
	//应用标识
	/*@NotBlank
	@Length(max = 32)
	@JsonProperty(required=true)*/
	private String Appid;
	
	//银行标识(FrontEndReqBase中已有BankType)
/*	@NotBlank
	@Length(min = 4, max = 4)
	@JsonProperty(required=true)
	private String BankType;*/
	
	//银行登录帐号
	@NotBlank
	@Length(max = 128)
	@JsonProperty(required=true)
	private String bankUin;
	
	//企业标识
	@NotBlank
	@Length(max = 64)
	@JsonProperty(required=true)
	private String corpId;
	
	//企业名称
	@NotBlank
	@Length(max = 128)
	@JsonProperty(required=true)
	private String corpName;
	
	//证件号
	@JsonProperty
	private String certId;
	
	//操作员姓名
	@JsonProperty
	private String opUserName;
	
	//操作员标识
	@NotBlank
	@Length(max = 64)
	@JsonProperty(required=true)
	private String opUserId;
	
	//操作员手机号
	@JsonProperty
	@Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}")
	private String opUserPhone;
		
	//业务状态
	@NotBlank
	@Length(max = 16)
	@JsonProperty(required=true)
	private String status;

	//验证码
	@NotBlank
	@Length(max = 16)
	@JsonProperty(required=true)
	private String verifyCode;

	public String getAppid() {
		return Appid;
	}

	public void setAppid(String appid) {
		Appid = appid;
	}

/*	public String getBankType() {
		return BankType;
	}

	public void setBankType(String bankType) {
		BankType = bankType;
	}*/

	@JsonIgnore
	public String getBankUin() {
		return bankUin;
	}

	@JsonIgnore
	public void setBankUin(String bankUin) {
		this.bankUin = bankUin;
	}

	@JsonIgnore
	public String getCorpId() {
		return corpId;
	}

	@JsonIgnore
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@JsonIgnore
	public String getCorpName() {
		return corpName;
	}

	@JsonIgnore
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	@JsonIgnore
	public String getCertId() {
		return certId;
	}

	@JsonIgnore
	public void setCertId(String certId) {
		this.certId = certId;
	}

	@JsonIgnore
	public String getOpUserId() {
		return opUserId;
	}

	@JsonIgnore
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	@JsonIgnore
	public String getOpUserPhone() {
		return opUserPhone;
	}

	@JsonIgnore
	public void setOpUserPhone(String opUserPhone) {
		this.opUserPhone = opUserPhone;
	}

	@JsonIgnore
	public String getVerifyCode() {
		return verifyCode;
	}

	@JsonIgnore
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@JsonIgnore
	public String getStatus() {
		return status;
	}

	@JsonIgnore
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getOpUserName() {
		return opUserName;
	}

	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Appid=");
		sb.append(RelayCodeUtils.encode(this.getAppid()));
		sb.append("&bankType=");
		sb.append(RelayCodeUtils.encode(this.getBankType()));
		sb.append("&bankUin=");
		sb.append(RelayCodeUtils.encode(this.getBankUin()));
		sb.append("&corpId=");
		sb.append(RelayCodeUtils.encode(this.getCorpId()));
		sb.append("&corpName=");
		sb.append(RelayCodeUtils.encode(this.getCorpName()));
		sb.append("&certId=");
		sb.append(RelayCodeUtils.encode(this.getCertId()));
		sb.append("&opUserId=");
		sb.append(RelayCodeUtils.encode(this.getOpUserId()));
		sb.append("&opUserPhone=");
		sb.append(RelayCodeUtils.encode(this.getOpUserPhone()));
		sb.append("&status=");
		sb.append(RelayCodeUtils.encode(this.getStatus()));
		sb.append("&verifyCode=");
		sb.append(RelayCodeUtils.encode(this.getVerifyCode()));
		return sb.toString();
	}
	
	public String toStringBankProxy(String appid) {
		StringBuffer sb = new StringBuffer();
		sb.append("bank_appid=");
		sb.append(RelayCodeUtils.encode(appid));
		sb.append("&bank_type=");
		sb.append(RelayCodeUtils.encode(this.getBankType()));
		sb.append("&bank_uin=");
		sb.append(RelayCodeUtils.encode(this.getBankUin()));
		sb.append("&corp_id=");
		sb.append(RelayCodeUtils.encode(this.getCorpId()));
		sb.append("&corp_name=");
		sb.append(RelayCodeUtils.encode(this.getCorpName()));
		sb.append("&cert_id=");
		sb.append(RelayCodeUtils.encode(this.getCertId()));
		sb.append("&op_user_id=");
		sb.append(RelayCodeUtils.encode(this.getOpUserId()));
		sb.append("&op_user_phone=");
		sb.append(RelayCodeUtils.encode(this.getOpUserPhone()));
		sb.append("&status=");
		sb.append(RelayCodeUtils.encode(this.getStatus()));
		sb.append("&verify_code=");
		sb.append(RelayCodeUtils.encode(this.getVerifyCode()));
		return sb.toString();
	}
}
