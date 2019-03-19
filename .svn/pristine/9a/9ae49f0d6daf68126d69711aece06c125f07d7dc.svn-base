package com.tenpay.wxwork.salary.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

import java.util.List;

public class ViewSalaryAllDetailResponse extends FrontEndResponse {

    public ViewSalaryAllDetailResponse(String counter,String salaryAll,String salarySum,String month,String corpToBank){
        super();
        this.counter = counter;
        this.salaryAll = salaryAll;
        this.month = month;
        this.corpToBank = corpToBank;
        this.salarySum = salarySum;
    }

    @JsonProperty(value="counter")
    private String counter;
    @JsonProperty(value="salaryAll")
    private String salaryAll;
    @JsonProperty(value="salarySum")
    private String salarySum;
    @JsonProperty(value="month")
    private String month;
    @JsonProperty(value = "corpToBank")
    private String corpToBank;
    @JsonProperty(value = "viewSalaryAllDetails")
    private List<ViewSalaryAllDetail> viewSalaryAllDetails;

    public ViewSalaryAllDetailResponse(List<ViewSalaryAllDetail> viewSalaryAllDetails){
        super();
        this.viewSalaryAllDetails = viewSalaryAllDetails;
    }

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

    public String getCorpToBank() {
        return corpToBank;
    }

    public void setCorpToBank(String corpToBank) {
        this.corpToBank = corpToBank;
    }

    public List<ViewSalaryAllDetail> getViewSalaryAllDetails() {
        return viewSalaryAllDetails;
    }

    public void setViewSalaryAllDetails(List<ViewSalaryAllDetail> viewSalaryAllDetails) {
        this.viewSalaryAllDetails = viewSalaryAllDetails;
    }

    public String getSalarySum() {
        return salarySum;
    }

    public void setSalarySum(String salarySum) {
        this.salarySum = salarySum;
    }
}
