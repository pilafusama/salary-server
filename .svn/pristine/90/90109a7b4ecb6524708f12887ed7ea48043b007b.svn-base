package com.tenpay.wxwork.salary.dto.admin;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SalaryFieldsParam {

    /**
     * 备注是否有效
     *
     */
    public enum RemarkJud {
        YES,
        NO
    }

    @JsonProperty("shouldList")
    private List<SalaryFieldsReq> shouldList;

    @JsonProperty("deductList")
    private List<SalaryFieldsReq> deductList;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("remark_valid")
    private String remark_valid;

    public List<SalaryFieldsReq> getShouldList() {
        return shouldList;
    }

    public void setShouldList(List<SalaryFieldsReq> shouldList) {
        this.shouldList = shouldList;
    }

    public List<SalaryFieldsReq> getDeductList() {
        return deductList;
    }

    public void setDeductList(List<SalaryFieldsReq> deductList) {
        this.deductList = deductList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark_valid() {
        return remark_valid;
    }

    public void setRemark_valid(String remark_valid) {
        this.remark_valid = remark_valid;
    }
}
