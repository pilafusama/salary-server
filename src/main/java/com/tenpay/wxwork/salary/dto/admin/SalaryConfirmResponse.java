package com.tenpay.wxwork.salary.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

/**
 * Created by win7 on 2018/10/17.
 */
public class SalaryConfirmResponse  extends FrontEndResponse {
    public SalaryConfirmResponse(List<SalaryConfirm> list) {
        super();
        this.list = list;
    }
    public SalaryConfirmResponse(PageInfo<SalaryConfirm> pageInfo) {
        super();
        this.pageInfo = pageInfo;
    }

    @JsonProperty(value="list")
    public List<SalaryConfirm> list;
    @JsonProperty(value="pageInfo")
    public PageInfo<SalaryConfirm> pageInfo;
    @JsonIgnore
    public List<SalaryConfirm> getList() {
        return list;
    }
    @JsonIgnore
    public void setList(List<SalaryConfirm> list) {
        this.list = list;
    }
    @JsonIgnore
    public PageInfo<SalaryConfirm> getPageInfo() {
        return pageInfo;
    }
    @JsonIgnore
    public void setPageInfo(PageInfo<SalaryConfirm> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
