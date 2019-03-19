package com.tenpay.wxwork.salary.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.common.utils.AesUtil;

public class OpenAccountInfo {
	@JsonProperty("cre_id")
	private String creId;// 证件号
	@JsonProperty("cre_name")
	private String creName;// 证件姓名
	@JsonProperty("bind_card_number")
	private String bindCardNumber;// 绑卡银行卡号
	@JsonProperty("mobile_number")
	private String mobileNumber;// 手机号码
	@JsonProperty("salary_card_number")
	private String salaryCardNumber;// 工资卡号（二类户）
	@JsonProperty("bind_card_bank_number")
	private String bindCardBankNumber;// 绑定银行卡的开户行联行号
	@JsonProperty("card_banks_relation")
	private String cardBanksRelation;// 申请开立E账户客户本他行标志

	public OpenAccountInfo(SalaryAccount salaryAccount,String accountSecret) {
		this.creId = AesUtil.decrypt(salaryAccount.getCre_id(),accountSecret);
		this.creName = AesUtil.decrypt(salaryAccount.getCre_name(),accountSecret);
		this.bindCardNumber = AesUtil.decrypt(salaryAccount.getBind_card_number(),accountSecret);
		this.mobileNumber = AesUtil.decrypt(salaryAccount.getMobile_number(),accountSecret);
		this.salaryCardNumber = AesUtil.decrypt(salaryAccount.getSalary_card_number(),accountSecret);
		this.bindCardBankNumber = salaryAccount.getBind_card_bank_number();
		this.cardBanksRelation = salaryAccount.getCard_banks_relation().name();
	}

	public OpenAccountInfo() {
	}

	public String getCreId() {
		return creId;
	}

	public void setCreId(String creId) {
		this.creId = creId;
	}

	public String getCreName() {
		return creName;
	}

	public void setCreName(String creName) {
		this.creName = creName;
	}

	public String getBindCardNumber() {
		return bindCardNumber;
	}

	public void setBindCardNumber(String bindCardNumber) {
		this.bindCardNumber = bindCardNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSalaryCardNumber() {
		return salaryCardNumber;
	}

	public void setSalaryCardNumber(String salaryCardNumber) {
		this.salaryCardNumber = salaryCardNumber;
	}

	public String getBindCardBankNumber() {
		return bindCardBankNumber;
	}

	public void setBindCardBankNumber(String bindCardBankNumber) {
		this.bindCardBankNumber = bindCardBankNumber;
	}

	public String getCardBanksRelation() {
		return cardBanksRelation;
	}

	public void setCardBanksRelation(String cardBanksRelation) {
		this.cardBanksRelation = cardBanksRelation;
	}

}
