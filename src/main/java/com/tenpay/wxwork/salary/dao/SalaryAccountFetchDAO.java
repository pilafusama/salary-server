package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.model.SalaryAccountFetch;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SalaryAccountFetchDAO {

    @Select("select Fsalary_account_fetch_id, Fcorpid, Fuserid, Fstate, Fbank_fetch_id, " +
            "Fsalary_card_number, Fbind_card_number, Ffetch_amount, Ffetch_type, Ffailure_reason, " +
            "Ffetch_start_time, Ffetch_end_time, Fcreate_time, Fmodify_time " +
            "FROM t_salary_account_fetch WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} ")
    @Results(id="SalaryAccountFetchMap",value = {
            @Result(property = "salary_account_id",column ="Fsalary_account_fetch_id"),
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "userid",column ="Fuserid"),
            @Result(property = "state",column ="Fstate"),
            @Result(property = "bank_fetch_id",column ="Fbank_fetch_id"),
            @Result(property = "salary_card_number",column ="Fsalary_card_number"),
            @Result(property = "bind_card_number",column ="Fbind_card_number"),
            @Result(property = "fetch_amount",column ="Ffetch_amount"),
            @Result(property = "fetch_type",column ="Ffetch_type"),
            @Result(property = "failure_reason",column ="Ffailure_reason"),
            @Result(property = "fetch_start_time",column ="Ffetch_start_time"),
            @Result(property = "fetch_end_time",column ="Ffetch_end_time"),
            @Result(property = "create_time",column ="Fcreate_time"),
            @Result(property = "modify_time",column ="Fmodify_time")
        })
    public SalaryAccountFetch querySalaryAccountFetch(@Param("corpid") String corpid, @Param("userid") String userid);

    @Insert("INSERT INTO t_salary_account_fetch" +
            "(Fcorpid, Fuserid, Fstate, Fbank_fetch_id, Fsalary_card_number, Fbind_card_number,Ffetch_amount," +
            " Ffetch_type,Ffailure_reason,Ffetch_start_time,Ffetch_end_time,Fcreate_time,Fmodify_time) VALUES " +
            "(#{corpid}, #{userid}, #{state}, #{bank_fetch_id},#{salary_card_number},#{bind_card_number},#{fetch_amount},#{fetch_type}," +
            "#{failure_reason},#{fetch_start_time},#{fetch_end_time},now(), now())")
    public void insertNewAccountFetch(@Param("corpid") String corp_id,
                                      @Param("userid") String userid,
                                      @Param("state") int state,
                                      @Param("bank_fetch_id") String bank_fetch_id,
                                      @Param("salary_card_number") String Falary_card_number,
                                      @Param("bind_card_number") String bind_card_number,
                                      @Param("fetch_amount") String fetch_amount,
                                      @Param("fetch_type") String fetch_type,
                                      @Param("failure_reason") String failure_reason,
                                      @Param("fetch_start_time") String fetch_start_time,
                                      @Param("fetch_end_time") String fetch_end_time
    );

}