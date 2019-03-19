package com.tenpay.wxwork.salary.dto;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="ApprovalNotify", propOrder={"ToUserName","FromUserName","MsgType","Event","ApprovalInfo"})
@XmlRootElement(name = "xml")
public class ApprovalNotify implements Serializable{
	@XmlElement
	private String ToUserName; //接收方企业Corpid
	@XmlElement
	private String FromUserName;
	@XmlElement
	private String MsgType;
	@XmlElement	
	private String Event;
	@XmlElement
	private ApprovalInfo ApprovalInfo;
	
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public ApprovalInfo getApprovalInfo() {
		return ApprovalInfo;
	}
	public void setApprovalInfo(ApprovalInfo approvalInfo) {
		ApprovalInfo = approvalInfo;
	}		
}
