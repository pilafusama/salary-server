package com.tenpay.wxwork.salary.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

import java.util.List;

public class UserSalaryDetailResponse extends FrontEndResponse{
    public UserSalaryDetailResponse(List<UserSalaryDetail> salarylist, List<UserSalaryDetail> deductionlist, String is_read) {
        this.salarylist = salarylist;
        this.deductionlist = deductionlist;
        this.is_read = is_read;
    }

    @JsonProperty(value="salarylist")
    public List<UserSalaryDetail> salarylist;
    @JsonProperty(value="deductionlist")
    public List<UserSalaryDetail> deductionlist;
    @JsonProperty(value="is_read")
    public String is_read;
    @JsonIgnore
    public List<UserSalaryDetail> getSalarylist() {
        return salarylist;
    }
    @JsonIgnore
    public void setSalarylist(List<UserSalaryDetail> salarylist) {
        this.salarylist = salarylist;
    }
    @JsonIgnore
    public List<UserSalaryDetail> getDeductionlist() {
        return deductionlist;
    }
    @JsonIgnore
    public void setDeductionlist(List<UserSalaryDetail> deductionlist) {
        this.deductionlist = deductionlist;
    }
    @JsonIgnore
    public String getIs_read() {
        return is_read;
    }
    @JsonIgnore
    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }
}
