package com.tenpay.wxwork.salary.dto.wxauth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;


public class LoginRequest {
	//登录ssid
	@JsonProperty
	private String code;

	@JsonProperty
	private String platId;
	
	//登录ssid
	@JsonProperty
	private String logSsid;
	
	//时间戳
	@JsonProperty
	private String timeStamp;
	//时间戳
	@JsonProperty
	private String nonce;
	//签名
	@JsonProperty
	private String sign;
	
	@JsonIgnore
	public String getLogSsid() {
		return logSsid;
	}
	@JsonIgnore
	public void setLogSsid(String logSsid) {
		this.logSsid = logSsid;
	}
	@JsonIgnore
	public String getTimeStamp() {
		return timeStamp;
	}
	@JsonIgnore
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}	
	
	
	@JsonIgnore
	public String getCode() {
		return code;
	}
	@JsonIgnore
	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonIgnore
	public String getPlatId() {
		return platId;
	}
	@JsonIgnore
	public void setPlatId(String platId) {
		this.platId = platId;
	}	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Appid=");

		sb.append(RelayCodeUtils.encode(this.getSign()));
	return sb.toString();
	}
}
