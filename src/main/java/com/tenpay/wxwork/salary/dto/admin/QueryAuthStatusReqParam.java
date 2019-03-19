package com.tenpay.wxwork.salary.dto.admin;

public class QueryAuthStatusReqParam {
	private String corpId;
	private String bankType;

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public QueryAuthStatusReqParam() {
		super();
	}

	public QueryAuthStatusReqParam(String corpId, String bankType) {
		super();
		this.corpId = corpId;
		this.bankType = bankType;
	}
	
	
}
