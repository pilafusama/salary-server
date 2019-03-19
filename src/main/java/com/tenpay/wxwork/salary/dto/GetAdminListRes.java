package com.tenpay.wxwork.salary.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAdminListRes {
	private int errcode;
	private String errmsg;
	@JsonProperty(required=false)
	private List<AdminInfo> admin;
	
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public List<AdminInfo> getAdmin() {
		return admin;
	}
	public void setAdmin(List<AdminInfo> admin) {
		this.admin = admin;
	}
	
}
