package com.tenpay.fingate.l5;

/**
 * L5信息的录入
 * 
 * @author robenzhang
 * @date 2015-12-7
 */
public class L5Info {
	private int modId;
	private int cmdId;
	private float timeout;
	// 传入调用接口的参数信息
	private String param;
	// 调用的cgi路劲
	private String cgi;
	// 实际域名
	private String domainName;

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getCgi() {
		return cgi;
	}

	public void setCgi(String cgi) {
		this.cgi = cgi;
	}

	public float getTimeout() {
		return timeout;
	}

	public void setTimeout(float timeout) {
		this.timeout = timeout;
	}

	public int getModId() {
		return modId;
	}

	public void setModId(int modId) {
		this.modId = modId;
	}

	public int getCmdId() {
		return cmdId;
	}

	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
