package com.tenpay.wxwork.salary.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 单笔工资卡信息
 */
public class SingleCardInfoReq{
    @NotBlank(message = "name不能为空")
    @JsonProperty(required=true)
    private String name;

    @NotBlank(message = "departmentId不能为空")
    @Length(max = 10)
    @JsonProperty(required=true)
    private String departmentId;

    @NotBlank(message = "departmentName不能为空")
    @JsonProperty(required=true)
    private String departmentName;

    @NotBlank(message = "bindCardNumber不能为空")
    @JsonProperty(required=true)
    private String bindCardNumber;

    @JsonProperty(required=true)
    private String bindCardBankSname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBindCardNumber() {
        return bindCardNumber;
    }

    public void setBindCardNumber(String bindCardNumber) {
        this.bindCardNumber = bindCardNumber;
    }

    public String getBindCardBankSname() {
        return bindCardBankSname;
    }

    public void setBindCardBankSname(String bindCardBankSname) {
        this.bindCardBankSname = bindCardBankSname;
    }
}
