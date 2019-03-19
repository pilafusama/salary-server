package com.tenpay.wxwork.salary.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FrontEndReqBase {
	//登录ssid
	@NotBlank(message = "logSsid不能为空")
	@Length(max = 128)
	@JsonProperty(required=true)
	private String logSsid;
	
	//时间戳
	@NotNull(message = "timeStamp不能为空")
	@Min(0)
	@JsonProperty(required=true)
	private int timeStamp;
	
	//随机串
	@NotBlank(message = "nonce不能为空")
	@Length(max = 64)
	@JsonProperty(required=true)
	private String nonce;
	//签名
	@NotBlank(message = "sign不能为空")
	@Length(max = 64)
	@JsonProperty(required=true)
	private String sign;
    //银行标识
    @NotBlank(message = "BankType不能为空")
    @Length(min = 4, max = 4)
    @JsonProperty(required=true)
    private String BankType;
	
	@JsonIgnore
	public String getLogSsid() {
		return logSsid;
	}
	@JsonIgnore
	public void setLogSsid(String logSsid) {
		this.logSsid = logSsid;
	}
	@JsonIgnore
	public int getTimeStamp() {
		return timeStamp;
	}
	@JsonIgnore
	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}
    @JsonIgnore
	public String getNonce() {
		return nonce;
	}
    @JsonIgnore
	public void setNonce(String nonce) {
		this.nonce = nonce;
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
    public String getBankType() {
        return BankType;
    }
    @JsonIgnore
    public void setBankType(String bankType) {
        BankType = bankType;
    }
}
