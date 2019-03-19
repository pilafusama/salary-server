package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.model.SalaryBankConfInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SalaryBankConfDAO {

    @Select(" select Fbank_sname,Fstate,Ftype,Fkey,Fvalue " +
            " from t_salary_bank_conf where Fbank_sname = #{bank_sname} and Fkey = #{key} and Fstate = #{state} ")
    @Results(id="querySalaryCorpConfInfo",value = {
            @Result(property = "bank_sname",column ="Fbank_sname"),
            @Result(property = "state",column ="Fstate"),
            @Result(property = "type",column ="Ftype"),
            @Result(property = "key",column ="Fkey"),
            @Result(property = "value",column ="Fvalue")
    })
    public SalaryBankConfInfo querySalaryCorpConfInfo(@Param("bank_sname") String bank_sname, @Param("key") String key ,@Param("state") String state);

    @Select(" select b.Fbank_sname, b.Fstate, b.Ftype, b.Fkey, b.Fvalue from t_salary_bank_conf b" +
            " where b.Fbank_sname = #{bankName} and b.Fstate = #{baudited}")
    @Results(id="querySalaryBankConf",value = {
            @Result(property = "bank_sname",column ="Fbank_sname"),
            @Result(property = "state",column ="Fstate"),
            @Result(property = "type",column ="Ftype"),
            @Result(property = "key",column ="Fkey"),
            @Result(property = "value",column ="Fvalue")
    })
    public List<SalaryBankConfInfo> querySalaryBankConf(@Param("bankName") String bankName, @Param("baudited") String baudited);

    @Select(" select Fbank_sname,Fstate,Ftype,Fkey,Fvalue " +
            " from t_salary_bank_conf where  Fkey = #{key} and Fvalue = #{value} and Fstate = #{state} limit 1")
    @Results(id="querySalaryBankSname",value = {
            @Result(property = "bank_sname",column ="Fbank_sname"),
            @Result(property = "state",column ="Fstate"),
            @Result(property = "type",column ="Ftype"),
            @Result(property = "key",column ="Fkey"),
            @Result(property = "value",column ="Fvalue")
    })
    public SalaryBankConfInfo querySalaryBankSname(@Param("key") String key ,@Param("value") String value ,@Param("state") String state);
}
