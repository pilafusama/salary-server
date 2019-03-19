package com.tenpay.wxwork.salary.dto.admin;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;

import java.io.Serializable;
import java.math.BigInteger;

public class SalaryOverViewInfo extends FrontEndResponse implements Serializable {
    /**
     * 工资卡与绑定卡关系
     *
     */
    public enum BankCardsRelation {
        SAME_BANK,
        DIFF_BANK
    }

    /**
     * 员工是否已阅读此工资条
     */
    public enum IsRead{
        YES,
        NO
    }
    private int salary_overview_id;//主键id
    private String month;//月份
    private String corpid;//企业id
    private int sequence_number;//发薪序号
    private String department_name;//部门名称
    private String userid;//企业微信中的用户id
    private String user_name;//用户名
    private String card_number;//工资卡
    private String card_bank_number;//工资卡的发卡联行号
    private String bank_cards_relation;  //工资卡与绑定卡关系
    private String operator_userid;//工资操作人的userid
    private BigInteger actual_salary;//实发工资
    private BigInteger salary_sum;//应发工资总额
    private BigInteger deduction_sum;//扣款总额
    private String remark;//备注
    private String is_read;//员工是否已阅读此工资条
    private int batch_no;//此月份第几次发薪

    public int getSalary_overview_id() {
        return salary_overview_id;
    }

    public void setSalary_overview_id(int salary_overview_id) {
        this.salary_overview_id = salary_overview_id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public int getSequence_number() {
        return sequence_number;
    }

    public void setSequence_number(int sequence_number) {
        this.sequence_number = sequence_number;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_bank_number() {
        return card_bank_number;
    }

    public void setCard_bank_number(String card_bank_number) {
        this.card_bank_number = card_bank_number;
    }

    public String getBank_cards_relation() {
        return bank_cards_relation;
    }

    public void setBank_cards_relation(String bank_cards_relation) {
        this.bank_cards_relation = bank_cards_relation;
    }

    public String getOperator_userid() {
        return operator_userid;
    }

    public void setOperator_userid(String operator_userid) {
        this.operator_userid = operator_userid;
    }

    public BigInteger getActual_salary() {
        return actual_salary;
    }

    public void setActual_salary(BigInteger actual_salary) {
        this.actual_salary = actual_salary;
    }

    public BigInteger getSalary_sum() {
        return salary_sum;
    }

    public void setSalary_sum(BigInteger salary_sum) {
        this.salary_sum = salary_sum;
    }

    public BigInteger getDeduction_sum() {
        return deduction_sum;
    }

    public void setDeduction_sum(BigInteger deduction_sum) {
        this.deduction_sum = deduction_sum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }


    public int getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(int batch_no) {
        this.batch_no = batch_no;
    }
}
