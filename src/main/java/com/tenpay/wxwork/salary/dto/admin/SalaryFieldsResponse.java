package com.tenpay.wxwork.salary.dto.admin;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

import java.util.List;


public class SalaryFieldsResponse extends FrontEndResponse{



    @JsonProperty("shouldList")
    private List<SalaryFieldsReq> shouldList;

    @JsonProperty("deductList")
    private List<SalaryFieldsReq> deductList;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("isSubmit")
    private String isSubmit;

    @JsonProperty("remark_valid")
    private String remark_valid;

    @JsonProperty("isConfigTemplate")
    private String is_config_template;


    public SalaryFieldsResponse(List<SalaryFieldsReq> shouldList, List<SalaryFieldsReq> deductList, String remark, String isSubmit, String remark_valid) {
        this.shouldList = shouldList;
        this.deductList = deductList;
        this.remark = remark;
        this.isSubmit = isSubmit;
        this.remark_valid = remark_valid;
    }
    public SalaryFieldsResponse(String is_config_template) {
        super();
        this.is_config_template = is_config_template;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

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

    public String getRemark_valid() {
        return remark_valid;
    }

    public void setRemark_valid(String remark_valid) {
        this.remark_valid = remark_valid;
    }

    public String getIs_config_template() {
        return is_config_template;
    }

    public void setIs_config_template(String is_config_template) {
        this.is_config_template = is_config_template;
    }
}
