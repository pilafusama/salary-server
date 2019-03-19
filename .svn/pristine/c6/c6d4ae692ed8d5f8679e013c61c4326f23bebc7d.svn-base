package com.tenpay.wxwork.salary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class SalaryCount implements Serializable{
    private static final long serialVersionUID = 55384713290226813L;

    @JsonProperty("month")
    public String month;//月份
    @JsonProperty("total_pre_tax_wages")
    public BigDecimal total_pre_tax_wages;//税前工资合计
    @JsonProperty("actual_amount")
    public BigDecimal actual_amount;//实发金额
    @JsonProperty("batch_no")
    public int batch_no;//此月份发薪次数

    @JsonIgnore
    public String getMonth() {
        return month;
    }
    @JsonIgnore
    public void setMonth(String month) {
        this.month = month;
    }
    @JsonIgnore
    public BigDecimal getTotal_pre_tax_wages() {
        return total_pre_tax_wages;
    }
    @JsonIgnore
    public void setTotal_pre_tax_wages(BigDecimal total_pre_tax_wages) {
        this.total_pre_tax_wages = total_pre_tax_wages;
    }
    @JsonIgnore
    public BigDecimal getActual_amount() {
        return actual_amount;
    }
    @JsonIgnore
    public void setActual_amount(BigDecimal actual_amount) {
        this.actual_amount = actual_amount;
    }
    @JsonIgnore
    public int getBatch_no() {
        return batch_no;
    }
    @JsonIgnore
    public void setBatch_no(int batch_no) {
        this.batch_no = batch_no;
    }
}
