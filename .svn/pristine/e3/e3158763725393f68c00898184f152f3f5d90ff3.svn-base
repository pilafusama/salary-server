package com.tenpay.wxwork.salary.dto.h5;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.model.SalaryCount;

public class SalaryCountResponse extends FrontEndResponse{
    public SalaryCountResponse(List<SalaryCount> list) {
        super();

        this.list = list;
    }

    @JsonProperty(value="list")
    public List<SalaryCount> list;
    @JsonIgnore
    public List<SalaryCount> getList() {
        return list;
    }
    @JsonIgnore
    public void setList(List<SalaryCount> list) {
        this.list = list;
    }
}
