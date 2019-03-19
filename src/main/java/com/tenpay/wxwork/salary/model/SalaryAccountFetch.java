package com.tenpay.wxwork.salary.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//工资条账户提现
public class SalaryAccountFetch implements Serializable {
	private static final long serialVersionUID = 4899191834606998871L;

    private String  salary_account_fetch_id;//自增id,

    private String  corpid;//企业id
    private String  userid;//用户id

    public enum FetchType {
        SAME_BANK,
        DIFF_BANK
    }

    private int  state;//状态
    private String  bank_fetch_id;//银行提现单号
    private String  salary_card_number;//工资账户卡号
    private String  bind_card_number;//绑定账户卡号
    private String  fetch_amount;//提现金额，单位分
    private FetchType  fetch_type;//提现类型
    private String  failure_reason;//失败原因
    private String  fetch_start_time;//提现开始时间
    private String  fetch_end_time;//提现结束时间
    private String  create_time;//创建时间
    private String  modify_time;//最后更新时间

    public String getSalary_account_fetch_id() {
        return salary_account_fetch_id;
    }

    public void setSalary_account_fetch_id(String salary_account_fetch_id) {
        this.salary_account_fetch_id = salary_account_fetch_id;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getint() {
        return state;
    }

    public void setint(int state) {
        this.state = state;
    }

    public String getBank_fetch_id() {
        return bank_fetch_id;
    }

    public void setBank_fetch_id(String bank_fetch_id) {
        this.bank_fetch_id = bank_fetch_id;
    }

    public String getSalary_card_number() {
        return salary_card_number;
    }

    public void setSalary_card_number(String salary_card_number) {
        this.salary_card_number = salary_card_number;
    }

    public String getBind_card_number() {
        return bind_card_number;
    }

    public void setBind_card_number(String bind_card_number) {
        this.bind_card_number = bind_card_number;
    }

    public String getFetch_amount() {
        return fetch_amount;
    }

    public void setFetch_amount(String fetch_amount) {
        this.fetch_amount = fetch_amount;
    }

    public FetchType getFetch_type() {
        return fetch_type;
    }

    public void setFetch_type(FetchType fetch_type) {
        this.fetch_type = fetch_type;
    }

    public String getFailure_reason() {
        return failure_reason;
    }

    public void setFailure_reason(String failure_reason) {
        this.failure_reason = failure_reason;
    }

    public String getFetch_start_time() {
        return fetch_start_time;
    }

    public void setFetch_start_time(String fetch_start_time) {
        this.fetch_start_time = fetch_start_time;
    }

    public String getFetch_end_time() {
        return fetch_end_time;
    }

    public void setFetch_end_time(String fetch_end_time) {
        this.fetch_end_time = fetch_end_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }
}
