package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.model.FinancialPlan;
import com.tenpay.wxwork.salary.model.FinancialPlanExec;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FinancialPlanExecDAO {

    @Delete("DELETE FROM t_financial_plan_exec WHERE Ffinancial_plan_id = #{ffinancialPlanId} AND Fstatus='2' LIMIT 1")
    public void deletePlanExec(@Param("ffinancialPlanId") String ffinancialPlanId);

    @Select("SELECT pe.Ffinancial_plan_exec_id, pe.Ffinancial_plan_id, pe.Fcre_id, pe.Fcre_name, " +
            " pe.Facct_no, pe.Ftran_amt, pe.Facct_no1, pe.Fplan_tran_time, pe.Factual_tran_time, " +
            " pe.Fstatus FROM t_financial_plan_exec pe" +
            " WHERE pe.Ffinancial_plan_id = #{planId} AND pe.Fstatus = '1' order by pe.Factual_tran_time DESC")
    public List<FinancialPlanExec> queryExecPlans(@Param("planId") String planId);


}