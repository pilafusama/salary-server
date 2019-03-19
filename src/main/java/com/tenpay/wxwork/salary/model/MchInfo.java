package com.tenpay.wxwork.salary.model;

import java.util.Date;

public class MchInfo {
    private String fmchId;

    private String fmchName;

    private String fservicePhone;

    private String fmchDeveloper;

    private String fwxspId;

    private String fcontact;

    private String fconactPhone;

    private String fconactMail;

    private Integer ftype;

    private Integer fmchStatus;

    private String fsingType;

    private String fsignKey;

    private Date fcreateTime;

    private Date fmodifyTime;

    public String getFmchId() {
        return fmchId;
    }

    public void setFmchId(String fmchId) {
        this.fmchId = fmchId == null ? null : fmchId.trim();
    }

    public String getFmchName() {
        return fmchName;
    }

    public void setFmchName(String fmchName) {
        this.fmchName = fmchName == null ? null : fmchName.trim();
    }

    public String getFservicePhone() {
        return fservicePhone;
    }

    public void setFservicePhone(String fservicePhone) {
        this.fservicePhone = fservicePhone == null ? null : fservicePhone.trim();
    }

    public String getFmchDeveloper() {
        return fmchDeveloper;
    }

    public void setFmchDeveloper(String fmchDeveloper) {
        this.fmchDeveloper = fmchDeveloper == null ? null : fmchDeveloper.trim();
    }

    public String getFwxspId() {
        return fwxspId;
    }

    public void setFwxspId(String fwxspId) {
        this.fwxspId = fwxspId == null ? null : fwxspId.trim();
    }

    public String getFcontact() {
        return fcontact;
    }

    public void setFcontact(String fcontact) {
        this.fcontact = fcontact == null ? null : fcontact.trim();
    }

    public String getFconactPhone() {
        return fconactPhone;
    }

    public void setFconactPhone(String fconactPhone) {
        this.fconactPhone = fconactPhone == null ? null : fconactPhone.trim();
    }

    public String getFconactMail() {
        return fconactMail;
    }

    public void setFconactMail(String fconactMail) {
        this.fconactMail = fconactMail == null ? null : fconactMail.trim();
    }

    public Integer getFtype() {
        return ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    public Integer getFmchStatus() {
        return fmchStatus;
    }

    public void setFmchStatus(Integer fmchStatus) {
        this.fmchStatus = fmchStatus;
    }

    public String getFsingType() {
        return fsingType;
    }

    public void setFsingType(String fsingType) {
        this.fsingType = fsingType == null ? null : fsingType.trim();
    }

    public String getFsignKey() {
        return fsignKey;
    }

    public void setFsignKey(String fsignKey) {
        this.fsignKey = fsignKey == null ? null : fsignKey.trim();
    }

    public Date getFcreateTime() {
        return fcreateTime;
    }

    public void setFcreateTime(Date fcreateTime) {
        this.fcreateTime = fcreateTime;
    }

    public Date getFmodifyTime() {
        return fmodifyTime;
    }

    public void setFmodifyTime(Date fmodifyTime) {
        this.fmodifyTime = fmodifyTime;
    }

//    /**
//     * 
//     * @param mDto
//     * @throws Exception
//     */
//	public void cloneFrom(MchDto mDto) throws Exception {
//		this.setFmchId(mDto.getMchId());
//		this.setFmchName(mDto.getMchName());
//		this.setFservicePhone(mDto.getServicePhone());
//		this.setFmchDeveloper(mDto.getMchDeveloper());
//		this.setFwxspId(mDto.getWxspId());
//		this.setFcontact(mDto.getContact());
//		this.setFconactPhone(mDto.getConactPhone());
//		this.setFconactMail(mDto.getConactMail());
//		this.setFtype(mDto.getType());
//		this.setFmchStatus(mDto.getMchStatus());
//		this.setFsingType(mDto.getSingType());
//		this.setFsignKey(mDto.getSignKey());
//		this.setFcreateTime(mDto.getCreateTime());
//		this.setFmodifyTime(mDto.getModiyTime());
//	}
//	
//	/**
//	 * 
//	 * @param mDto
//	 * @throws Exception
//	 */
//	public void cloneTo(MchDto mDto) throws Exception {
//		mDto.setMchId(this.getFmchId());
//		mDto.setMchName(this.getFmchName());
//		mDto.setServicePhone(this.getFservicePhone());
//		mDto.setMchDeveloper(this.getFmchDeveloper());
//		mDto.setWxspId(this.getFwxspId());
//		mDto.setContact(this.getFcontact());
//		mDto.setConactPhone(this.getFconactPhone());
//		mDto.setConactMail(this.getFconactMail());
//		mDto.setType(this.getFtype());
//		mDto.setMchStatus(this.getFmchStatus());
//		mDto.setSingType(this.getFsingType());
//		mDto.setSignKey(this.getFsignKey());
//		mDto.setCreateTime(this.getFcreateTime());
//		mDto.setModiyTime(this.getFmodifyTime());		
//	}
}