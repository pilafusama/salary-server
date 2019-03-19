package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class SalaryCountReqParam extends FrontEndReqBase {

    @JsonProperty(value="month", required=true)
    public String month;//月份
    @JsonProperty(value="batch_no", required=true)
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
    public int getBatch_no() {
        return batch_no;
    }
    @JsonIgnore
    public void setBatch_no(int batch_no) {
        this.batch_no = batch_no;
    }
}
