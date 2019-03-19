package com.tenpay.wxwork.salary.model;

public class FinancialPlanExec {
    private String ffinancial_plan_exec_id; //id
    private String ffinancial_plan_id; //理财计划id
    private String fcre_id; //证件号码
    private String fcre_name; //证件姓名
    private String facct_no; //交易账号
    private String ftran_amt; //交易金额
    private String facct_no1; //对方账号
    private String fplan_tran_time; //计划交易时间
    private String factual_tran_time; //实际交易完成时间
    private String fstatus; //交易状态(0失败 1成功 2计划执行)

    public String getFfinancial_plan_exec_id() {
        return ffinancial_plan_exec_id;
    }

    public void setFfinancial_plan_exec_id(String ffinancial_plan_exec_id) {
        this.ffinancial_plan_exec_id = ffinancial_plan_exec_id;
    }

    public String getFfinancial_plan_id() {
        return ffinancial_plan_id;
    }

    public void setFfinancial_plan_id(String ffinancial_plan_id) {
        this.ffinancial_plan_id = ffinancial_plan_id;
    }

    public String getFcre_id() {
        return fcre_id;
    }

    public void setFcre_id(String fcre_id) {
        this.fcre_id = fcre_id;
    }

    public String getFcre_name() {
        return fcre_name;
    }

    public void setFcre_name(String fcre_name) {
        this.fcre_name = fcre_name;
    }

    public String getFacct_no() {
        return facct_no;
    }

    public void setFacct_no(String facct_no) {
        this.facct_no = facct_no;
    }

    public String getFtran_amt() {
        return ftran_amt;
    }

    public void setFtran_amt(String ftran_amt) {
        this.ftran_amt = ftran_amt;
    }

    public String getFacct_no1() {
        return facct_no1;
    }

    public void setFacct_no1(String facct_no1) {
        this.facct_no1 = facct_no1;
    }

    public String getFplan_tran_time() {
        return fplan_tran_time;
    }

    public void setFplan_tran_time(String fplan_tran_time) {
        this.fplan_tran_time = fplan_tran_time;
    }

    public String getFactual_tran_time() {
        return factual_tran_time;
    }

    public void setFactual_tran_time(String factual_tran_time) {
        this.factual_tran_time = factual_tran_time;
    }

    public String getFstatus() {
        return fstatus;
    }

    public void setFstatus(String fstatus) {
        this.fstatus = fstatus;
    }
}
