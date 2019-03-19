package com.tenpay.wxwork.salary.sqlprovider;

import org.apache.ibatis.annotations.Param;


public class SalaryDetailProvider {



    //查询进三月工资明细
    public String queryUserSalaryCount(@Param("corpid") String corpid, @Param("userid") String userid, @Param("monthone") String monthone,@Param("monthtwo") String monthtwo,@Param("monththree") String monththree){
        StringBuffer stringBuffer = new StringBuffer();

        if(monthone != null && !"".equals(monthone)){
            stringBuffer.append("select Fmonth,Fsalary_sum,Factual_salary ,Fdeduction_sum, Fbatch_no from t_salary_overview_");
            stringBuffer.append(monthone);
            stringBuffer.append(" where Fcorpid = #{corpid} and Fuserid = #{userid} AND Fbatch_no != '0' ");
        }


        if(monthtwo != null && !"".equals(monthone)){
            if(stringBuffer.length() != 0){
                stringBuffer.append(" union all ");
            }
            stringBuffer.append("select Fmonth,Fsalary_sum,Factual_salary ,Fdeduction_sum, Fbatch_no from t_salary_overview_");
            stringBuffer.append(monthtwo);
            stringBuffer.append(" where Fcorpid = #{corpid} and Fuserid = #{userid} AND Fbatch_no != '0' ");
        }

        if(monththree != null && !"".equals(monthone)){
            if(stringBuffer.length() != 0){
                stringBuffer.append(" union all ");
            }
            stringBuffer.append("select Fmonth,Fsalary_sum,Factual_salary ,Fdeduction_sum, Fbatch_no from t_salary_overview_");
            stringBuffer.append(monththree);
            stringBuffer.append(" where Fcorpid = #{corpid} and Fuserid = #{userid} AND Fbatch_no != '0' ");
        }

        return stringBuffer.toString();
    }
}
