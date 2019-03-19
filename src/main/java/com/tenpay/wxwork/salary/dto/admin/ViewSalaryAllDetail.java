package com.tenpay.wxwork.salary.dto.admin;


import java.io.Serializable;

public class ViewSalaryAllDetail implements Serializable {

    private String counter;//发薪人数
    private String salaryAll;//实发金额
    private String salarySum;//应发金额
    private String month;//发薪月份
    private int batch_no;//此月份第几次发薪
    private String create_time;//发薪时间

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getSalaryAll() {
        return salaryAll;
    }

    public void setSalaryAll(String salaryAll) {
        this.salaryAll = salaryAll;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(int batch_no) {
        this.batch_no = batch_no;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSalarySum() {
        return salarySum;
    }

    public void setSalarySum(String salarySum) {
        this.salarySum = salarySum;
    }
}
