package com.tenpay.wxwork.salary.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 单笔工资卡信息
 */
public class UserDepartmentReq {
    @NotBlank(message = "name不能为空")
    @JsonProperty(required=true)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
