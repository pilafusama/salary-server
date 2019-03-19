package com.tenpay.wxwork.salary.model;

public class AuthenCkv {
	
	private String corpid;
    private String userid;
    private String type;
    private String suiteid;
    private String authCode;
    private String expired;
    private String createTime;
    
    public AuthenCkv(String authCode, String expired) {
		// TODO Auto-generated constructor stub
    	this.authCode = authCode;
    	this.expired = expired;
		this.corpid = "";
		this.userid = "";
		this.suiteid = "";
		this.type = "";    	
	}

	public AuthenCkv(String corpid, String userid, String suiteid,
			String type) {
		// TODO Auto-generated constructor stub
		this.corpid = corpid;
		this.userid = userid;
		this.suiteid = suiteid;
		this.type = type;
    	this.authCode = "";
    	this.expired = "";
    }

	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuiteid() {
		return suiteid;
	}

	public void setSuiteid(String suiteid) {
		this.suiteid = suiteid;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



    public String toString() {
        return "AccessToken{" +
                "authCode='" + authCode + '\'' +
                "type ='" + type + '\'' +
                ", expired=" + expired +
                //", expireFormat=" + formatTimestamp(expire) +
                '}';
    }
}
