package com.tenpay.wxwork.salary.dto.admin;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import java.io.Serializable;
import java.math.BigInteger;

public class DefinedSalaryDetailInfo extends FrontEndResponse implements Serializable {
    /**
     * 类别
     */
    public enum Category {
        SALARY ,
        DEDUCTION
    }
    private int salary_overview_id;//总览表id
    private String category;//类别
    private String detail_name;//明细项名称
    private BigInteger detail_amount;//明细项金额

    public int getSalary_overview_id() {
        return salary_overview_id;
    }

    public void setSalary_overview_id(int salary_overview_id) {
        this.salary_overview_id = salary_overview_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public void setDetail_name(String detail_name) {
        this.detail_name = detail_name;
    }

    public BigInteger getDetail_amount() {
        return detail_amount;
    }

    public void setDetail_amount(BigInteger detail_amount) {
        this.detail_amount = detail_amount;
    }
}
