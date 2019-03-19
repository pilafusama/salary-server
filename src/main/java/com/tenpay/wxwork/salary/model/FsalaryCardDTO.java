package com.tenpay.wxwork.salary.model;

public class FsalaryCardDTO {

    private String fsalaryfCardNumber; //工资卡号（二类户）
    private String fsalaryCardBankType; //工资卡发卡行 bank_type
    private String fsalaryCardBankSname; //发卡银行
    private String fcreName; //证件姓名
    //数据库缺一个卡的有效期
    private String fbindCardNumber; //绑定的银行卡号（一类户）
    private String fbindCardBankSname; //绑定银行卡的银行sname
    private String fcreateTime; //创建日期

    public String getFsalaryfCardNumber() {
        return fsalaryfCardNumber;
    }

    public void setFsalaryfCardNumber(String fsalaryfCardNumber) {
        this.fsalaryfCardNumber = fsalaryfCardNumber;
    }

    public String getFsalaryCardBankType() {
        return fsalaryCardBankType;
    }

    public void setFsalaryCardBankType(String fsalaryCardBankType) {
        this.fsalaryCardBankType = fsalaryCardBankType;
    }

    public String getFsalaryCardBankSname() {
        return fsalaryCardBankSname;
    }

    public void setFsalaryCardBankSname(String fsalaryCardBankSname) {
        this.fsalaryCardBankSname = fsalaryCardBankSname;
    }

    public String getFcreName() {
        return fcreName;
    }

    public void setFcreName(String fcreName) {
        this.fcreName = fcreName;
    }

    public String getFbindCardNumber() {
        return fbindCardNumber;
    }

    public void setFbindCardNumber(String fbindCardNumber) {
        this.fbindCardNumber = fbindCardNumber;
    }

    public String getFbindCardBankSname() {
        return fbindCardBankSname;
    }

    public void setFbindCardBankSname(String fbindCardBankSname) {
        this.fbindCardBankSname = fbindCardBankSname;
    }

    public String getFcreateTime() {
        return fcreateTime;
    }

    public void setFcreateTime(String fcreateTime) {
        this.fcreateTime = fcreateTime;
    }
}
