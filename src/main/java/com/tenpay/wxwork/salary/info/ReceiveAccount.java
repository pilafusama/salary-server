package com.tenpay.wxwork.salary.info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceiveAccount {
	//收款人帐户全名
	@JsonProperty("receive_name")
	private String receive_name;
	
	//收款人帐户
	@JsonProperty("receive_account")
	private String receive_account;
	
	//收款帐户开户行
	@JsonProperty("account_bank")
	private String account_bank;
	
	//收款帐户开户行所在地
	@JsonProperty("account_area")
	private String account_area;
		
	@JsonIgnore
	public String getReceive_name() {
		return receive_name;
	}

	@JsonIgnore
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}

	@JsonIgnore
	public String getReceive_account() {
		return receive_account;
	}

	@JsonIgnore
	public void setReceive_account(String receive_account) {
		this.receive_account = receive_account;
	}

	@JsonIgnore
	public String getAccount_bank() {
		return account_bank;
	}

	@JsonIgnore
	public void setAccount_bank(String account_bank) {
		this.account_bank = account_bank;
	}

	@JsonIgnore
	public String getAccount_area() {
		return account_area;
	}

	@JsonIgnore
	public void setAccount_area(String account_area) {
		this.account_area = account_area;
	}
}

