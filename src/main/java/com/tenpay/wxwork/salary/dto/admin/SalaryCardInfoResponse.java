package com.tenpay.wxwork.salary.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.model.SalaryCardInfo;

import java.util.List;


/**
 * Created by wg on 2018/11/15
 */
public class SalaryCardInfoResponse extends FrontEndResponse {

    public SalaryCardInfoResponse(PageInfo<SalaryCardInfo> salaryCardInfoList) {
        this.salaryCardInfoList = salaryCardInfoList;
    }

    public SalaryCardInfoResponse(List<SalaryFieldsReq> nameList) {
        this.nameList = nameList;
    }

    @JsonProperty("salaryCardInfoList")
    private PageInfo<SalaryCardInfo> salaryCardInfoList;


    @JsonProperty("nameList")
    private List<SalaryFieldsReq> nameList;

    @JsonIgnore
    public PageInfo<SalaryCardInfo> getSalaryCardInfoList() {
        return salaryCardInfoList;
    }
    @JsonIgnore
    public void setSalaryCardInfoList(PageInfo<SalaryCardInfo> salaryCardInfoList) {
        this.salaryCardInfoList = salaryCardInfoList;
    }

    public List<SalaryFieldsReq> getNameList() {
        return nameList;
    }

    public void setNameList(List<SalaryFieldsReq> nameList) {
        this.nameList = nameList;
    }
}
