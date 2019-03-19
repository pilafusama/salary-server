package com.tenpay.wxwork.salary.dto.wxauth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.model.User;



public class LoginResponse extends FrontEndResponse {
	public LoginResponse(String retCode, String errMsg) {
		super(retCode, errMsg);
		// TODO Auto-generated constructor stub
	}
	
	//登录ssid
	@JsonProperty
	private String logSsid;
	
	//时间戳
	@JsonProperty
	private String ssKey;
	//时间戳
	@JsonProperty
	private String ssidExpried;
	//签名
	@JsonProperty
	private User user;
	
	@JsonIgnore
	public String getLogSsid() {
		return logSsid;
	}
	@JsonIgnore
	public void setLogSsid(String logSsid) {
		this.logSsid = logSsid;
	}
	@JsonIgnore
	public String getSsKey() {
		return ssKey;
	}
	@JsonIgnore
	public void setSsKey(String ssKey) {
		this.ssKey = ssKey;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Appid=");
		sb.append(RelayCodeUtils.encode(""));
        return sb.toString();
	}
}
