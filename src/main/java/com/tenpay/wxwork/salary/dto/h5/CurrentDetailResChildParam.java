package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by win7 on 2018/8/23.
 */
public class CurrentDetailResChildParam {

    @JsonProperty("year")
    private String year; //年份

    @JsonProperty("smyCd")
    private String smyCd; //摘要代码

    @JsonProperty("smyCntnt")
    private String smyCntnt; //摘要内容

    @JsonProperty("txnMonth")
    private String txnMonth; //交易月份

    @JsonProperty("txnDay")
    private String txnDay; //交易日期

    @JsonProperty("txnTm")
    private String txnTm; //交易时间

    @JsonProperty("depTxnAmt")
    private String depTxnAmt; //交易金额

    public CurrentDetailResChildParam(String year, String smyCd, String smyCntnt, String txnMonth, String txnDay,
                                      String txnTm, String depTxnAmt){
        this.year = year;
        this.smyCd = smyCd;
        this.smyCntnt = smyCntnt;
        this.txnMonth = txnMonth;
        this.txnDay = txnDay;
        this.txnTm = txnTm;
        this.depTxnAmt = depTxnAmt;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSmyCd() {
        return smyCd;
    }

    public void setSmyCd(String smyCd) {
        this.smyCd = smyCd;
    }

    public String getSmyCntnt() {
        return smyCntnt;
    }

    public void setSmyCntnt(String smyCntnt) {
        this.smyCntnt = smyCntnt;
    }

    public String getTxnMonth() {
        return txnMonth;
    }

    public void setTxnMonth(String txnMonth) {
        this.txnMonth = txnMonth;
    }

    public String getTxnDay() {
        return txnDay;
    }

    public void setTxnDay(String txnDay) {
        this.txnDay = txnDay;
    }

    public String getTxnTm() {
        return txnTm;
    }

    public void setTxnTm(String txnTm) {
        this.txnTm = txnTm;
    }

    public String getDepTxnAmt() {
        return depTxnAmt;
    }

    public void setDepTxnAmt(String depTxnAmt) {
        this.depTxnAmt = depTxnAmt;
    }
}
