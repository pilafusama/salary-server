package com.tenpay.wxwork.salary.dto;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="ApprovalChangeInfo", propOrder={"ChangeTime","Status"})
@XmlRootElement(name="ApprovalChangeInfo")
public class ApprovalChangeInfo implements Serializable{
	@XmlElement
	private String ChangeTime;
	@XmlElement
	private String Status;
	
	public String getChangeTime() {
		return ChangeTime;
	}
	public void setChangeTime(String changeTime) {
		ChangeTime = changeTime;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
