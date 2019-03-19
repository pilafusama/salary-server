package com.tenpay.wxwork.salary.sqlprovider;

import com.tenpay.wxwork.salary.model.SalaryAccount;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

public class SalaryAccountProvider {

    public String deleteUser(@Param("corpid") String corpid,
                             @Param("userid") String userid) {
        return String.format("UPDATE t_salary_account SET Fstate = %d Fmodify_time = NOW() WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} LIMIT 2",
                             SalaryAccount.State.ACCOUNT_DELETED.toInt());
    }
}
