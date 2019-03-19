package com.tenpay.wxwork.salary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAdminListReq {
	
	@JsonProperty("auth_corpid")
	private String auth_corpid;
	@JsonProperty
	private String agentid;
	
	public GetAdminListReq(String auth_corpid,String agentid)
	{
		this.setAuthCorpid(auth_corpid);
		this.setAgentid(agentid);
	}
	@JsonIgnore
	public String getAuthCorpid() {
		return auth_corpid;
	}
	@JsonIgnore
	public void setAuthCorpid(String auth_corpid) {
		this.auth_corpid = auth_corpid;
	}
	@JsonIgnore
	public String getAgentid() {
		return agentid;
	}
	@JsonIgnore
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
}
