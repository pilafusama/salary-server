package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.model.FinancialPlan;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FinancialPlanDAO {

    @Insert("INSERT INTO t_financial_plan " +
            "(Fcorpid, Fuserid, Ftran_amt, Ftran_prod, Ftran_cycle, Ftran_date,Fstatus," +
            " Fcreate_time,Fmodify_time) VALUES " +
            "(#{corpid}, #{userid}, #{tran_amt}, #{tran_prod},#{tran_cycle},#{tran_date},#{status},now(), now())")
    public void insertNewPlan(@Param("corpid") String corp_id,
                              @Param("userid") String userid,
                              @Param("tran_amt") String tran_amt,
                              @Param("tran_prod") String tran_prod,
                              @Param("tran_cycle") String tran_cycle,
                              @Param("tran_date") String tran_date,
                              @Param("status") String status
    );

    @Update("update t_financial_plan set Ftran_amt = #{tranAmt}, Ftran_prod = #{tranProd}, " +
            " Ftran_cycle = #{tranCycle}, Ftran_date = #{tranDate}, Fmodify_time = now() " +
            " WHERE Ffinancial_plan_id = #{ffinancialPlanId} LIMIT 1")
    public void updatePlan(@Param("ffinancialPlanId") String ffinancialPlanId
            , @Param("tranAmt") String tranAmt, @Param("tranProd") String tranProd,
                           @Param("tranCycle") String tranCycle, @Param("tranDate") String tranDate);

    @Select("SELECT tp.Ffinancial_plan_id, tp.Fcorpid, tp.Fuserid, tp.Ftran_amt, tp.Ftran_prod, tp.Ftran_cycle," +
            " tp.Ftran_date, tp.Fstatus, tp.Fcreate_time, tp.Fmodify_time FROM t_financial_plan tp " +
            " WHERE tp.Fcorpid = #{fcorpid} AND tp.Fuserid = #{fuserid} AND Fstatus = '1'")
    public List<FinancialPlan> queryPlans(@Param("fcorpid") String fcorpid, @Param("fuserid") String fuserid);

}