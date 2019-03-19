package com.tenpay.wxwork.salary.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class SalaryDetailInfoResponse extends FrontEndResponse {

    public SalaryDetailInfoResponse(String batch_no){
        super();
        this.batch_no = batch_no;
    }

    @JsonProperty(value = "batch_no")
    private String batch_no;
    @JsonIgnore
    public String getBatch_no() {
        return batch_no;
    }
    @JsonIgnore
    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }
}
