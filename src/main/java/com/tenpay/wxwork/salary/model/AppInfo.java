package com.tenpay.wxwork.salary.model;

public class AppInfo {
    public static int TYPE_PERSONAL_FINANCE = 1; // 个人金融服务
    public static int TYPE_ENTERPRISE_FINANCE = 2; // 企业金融服务
    public static int TYPE_SALARY = 3; // 工资条

    public static String BUSINESS_STYLE_OPEN_ACCOUNT = "1003"; // 开户

    private String modifyTime;
    private String createTime;
    private String appScret;
    private String refuseDesc;
    private int approvedState;
    private int state;
    private String allowUser;
    private String allowTag;
    private String allowParty;
    private int businessStyle;
    private int bankType;
    private String bankSname;
    private int type;
    private String mchId;
    private String appDesc;
    private String appRoundLogo;
    private String appSquareLogo;
    private String appUrl;
    private String appName;
    private String appId;

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAppScret() {
        return appScret;
    }

    public void setAppScret(String appScret) {
        this.appScret = appScret;
    }

    public String getRefuseDesc() {
        return refuseDesc;
    }

    public void setRefuseDesc(String refuseDesc) {
        this.refuseDesc = refuseDesc;
    }

    public int getApprovedState() {
        return approvedState;
    }

    public void setApprovedState(int approvedState) {
        this.approvedState = approvedState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAllowUser() {
        return allowUser;
    }

    public void setAllowUser(String allowUser) {
        this.allowUser = allowUser;
    }

    public String getAllowTag() {
        return allowTag;
    }

    public void setAllowTag(String allowTag) {
        this.allowTag = allowTag;
    }

    public String getAllowParty() {
        return allowParty;
    }

    public void setAllowParty(String allowParty) {
        this.allowParty = allowParty;
    }

    public int getBusinessStyle() {
        return businessStyle;
    }

    public void setBusinessStyle(int businessStyle) {
        this.businessStyle = businessStyle;
    }

    public int getBankType() {
        return bankType;
    }

    public void setBankType(int bankType) {
        this.bankType = bankType;
    }

    public String getBankSname() {
        return bankSname;
    }

    public void setBankSname(String bankSname) {
        this.bankSname = bankSname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getAppRoundLogo() {
        return appRoundLogo;
    }

    public void setAppRoundLogo(String appRoundLogo) {
        this.appRoundLogo = appRoundLogo;
    }

    public String getAppSquareLogo() {
        return appSquareLogo;
    }

    public void setAppSquareLogo(String appSquareLogo) {
        this.appSquareLogo = appSquareLogo;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}