package com.tenpay.wxwork.salary.model;

import com.tenpay.wxwork.salary.common.utils.DateUtils;

public class SessionInfo {

    private String sessionId;

    private String corpId;

    private String userId;

    // 活体检测数字
    private String livenessDetectionNumber;

    // 活体检测 token
    private String livenessDetectionToken;

    // 上传银行的身份证正面文件名
    private String idCardFrontFileName;

    // 上传银行的身份证背面文件名
    private String idCardBackFileName;

    //银行的核查流水号
    private String bankVerifySn;

    //一类户银行名称
    private String bankChNameOne;

    //一类户开户联行号
    private String bankCardNumBindOne;

    //一类户银行简称 如'CCB'
    private String bankSnameOne;

    //二类户银行名称
    private String bankChNameTwo;

    //银行名称
    private String bankSname;

    private String bankType;

    //开户模式
    private String openAccountMode;

    //发送短信验证码返回的分组标记
    private String fpgSmsGid;

    // 创建时间
    private int createTime;

    // 过期时间
    private int expireTime;

    public String getOpenAccountMode() {
        return openAccountMode;
    }

    public void setOpenAccountMode(String openAccountMode) {
        this.openAccountMode = openAccountMode;
    }

    public String getBankSname() {
        return bankSname;
    }

    public void setBankSname(String bankSname) {
        this.bankSname = bankSname;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankSnameOne() {
        return bankSnameOne;
    }

    public void setBankSnameOne(String bankSnameOne) {
        this.bankSnameOne = bankSnameOne;
    }

    public String getBankCardNumBindOne() {
        return bankCardNumBindOne;
    }

    public void setBankCardNumBindOne(String bankCardNumBindOne) {
        this.bankCardNumBindOne = bankCardNumBindOne;
    }

    public String getIdCardBackFileName()
    {
        return idCardBackFileName;
    }

    public void setIdCardBackFileName(String idCardBackFileName)
    {
        this.idCardBackFileName = idCardBackFileName;
    }

    public String getIdCardFrontFileName()
    {
        return idCardFrontFileName;
    }

    public void setIdCardFrontFileName(String idCardFrontFileName)
    {
        this.idCardFrontFileName = idCardFrontFileName;
    }

    public String getLivenessDetectionNumber()
    {
        return livenessDetectionNumber;
    }

    public void setLivenessDetectionNumber(String livenessDetectionNumber)
    {
        this.livenessDetectionNumber = livenessDetectionNumber;
    }

    public String getLivenessDetectionToken()
    {
        return livenessDetectionToken;
    }

    public void setLivenessDetectionToken(String livenessDetectionToken)
    {
        this.livenessDetectionToken = livenessDetectionToken;
    }

    public String getBankVerifySn() {
        return bankVerifySn;
    }

    public void setBankVerifySn(String bankVerifySn) {
        this.bankVerifySn = bankVerifySn;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isValidate() {
        return DateUtils.getTimestamp() <= expireTime;
    }

    public String getBankChNameOne() {
        return bankChNameOne;
    }

    public void setBankChNameOne(String bankChNameOne) {
        this.bankChNameOne = bankChNameOne;
    }

    public String getBankChNameTwo() {
        return bankChNameTwo;
    }

    public void setBankChNameTwo(String bankChNameTwo) {
        this.bankChNameTwo = bankChNameTwo;
    }

    public String getFpgSmsGid() {
        return fpgSmsGid;
    }

    public void setFpgSmsGid(String fpgSmsGid) {
        this.fpgSmsGid = fpgSmsGid;
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
                "sessionId='" + sessionId + '\'' +
                ", corpId='" + corpId + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + DateUtils.formatTimestamp(createTime) +
                ", expireTime=" + DateUtils.formatTimestamp(expireTime) +
                "}";
    }

}
