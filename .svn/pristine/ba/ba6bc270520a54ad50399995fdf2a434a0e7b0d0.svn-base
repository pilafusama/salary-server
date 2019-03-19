package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.model.FinancialPlan;

import java.util.List;

public class BalanceResParam extends FrontEndResponse {

    @JsonProperty("totalBal")
    private String totalBal; //资产总额

    @JsonProperty("currentAmt")
    private String currentAmt; //活期金额

    @JsonProperty("fixedAmt")
    private String fixedAmt; //定期金额

    @JsonProperty("fsalary_card_number")
    private String fsalary_card_number; //工资卡号

    @JsonProperty("fsalary_card_bank_type")
    private String fsalary_card_bank_type; //工资卡发卡行 bank_type

    @JsonProperty("fsalary_card_bank_sname")
    private String fsalary_card_bank_sname; //工资卡发卡银行sname

    @JsonProperty("fcre_name")
    private String fcre_name; //证件姓名

    @JsonProperty("fbindCardNumber")
    private String fbindCardNumber; //绑定的银行卡号（一类户）

    @JsonProperty("fcreateTime")
    private String fcreateTime; //创建时间

    @JsonProperty("fbindCardBankSname")
    private String fbindCardBankSname; //绑定银行卡的银行sname

    @JsonProperty("financialPlans")
    private List<FinancialPlan> financialPlans; //薪资理财计划


    public BalanceResParam(String totalBal, String currentAmt, String fixedAmt, String fsalary_card_number,
                           String fsalary_card_bank_type, String fsalary_card_bank_sname, String fcre_name,
                           String fbindCardNumber, String fcreateTime, String fbindCardBankSname){
        this.totalBal = totalBal;
        this.currentAmt = currentAmt;
        this.fixedAmt = fixedAmt;
        this.fsalary_card_number = fsalary_card_number;
        this.fsalary_card_bank_type = fsalary_card_bank_type;
        this.fsalary_card_bank_sname = fsalary_card_bank_sname;
        this.fcre_name = fcre_name;
        this.fbindCardNumber = fbindCardNumber;
        this.fcreateTime = fcreateTime;
        this.fbindCardBankSname = fbindCardBankSname;
    }
    public String getTotalBal() {
        return totalBal;
    }

    public void setTotalBal(String totalBal) {
        this.totalBal = totalBal;
    }

    public String getCurrentAmt() {
        return currentAmt;
    }

    public void setCurrentAmt(String currentAmt) {
        this.currentAmt = currentAmt;
    }

    public String getFixedAmt() {
        return fixedAmt;
    }

    public void setFixedAmt(String fixedAmt) {
        this.fixedAmt = fixedAmt;
    }

    public String getFsalary_card_number() {
        return fsalary_card_number;
    }

    public void setFsalary_card_number(String fsalary_card_number) {
        this.fsalary_card_number = fsalary_card_number;
    }

    public String getFsalary_card_bank_type() {
        return fsalary_card_bank_type;
    }

    public void setFsalary_card_bank_type(String fsalary_card_bank_type) {
        this.fsalary_card_bank_type = fsalary_card_bank_type;
    }

    public String getFsalary_card_bank_sname() {
        return fsalary_card_bank_sname;
    }

    public void setFsalary_card_bank_sname(String fsalary_card_bank_sname) {
        this.fsalary_card_bank_sname = fsalary_card_bank_sname;
    }

    public String getFcre_name() {
        return fcre_name;
    }

    public void setFcre_name(String fcre_name) {
        this.fcre_name = fcre_name;
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

    public List<FinancialPlan> getFinancialPlans() {
        return financialPlans;
    }

    public void setFinancialPlans(List<FinancialPlan> financialPlans) {
        this.financialPlans = financialPlans;
    }
}
