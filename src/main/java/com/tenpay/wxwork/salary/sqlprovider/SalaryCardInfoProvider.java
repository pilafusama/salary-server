package com.tenpay.wxwork.salary.sqlprovider;

import com.tenpay.wxwork.salary.model.SalaryAccount;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wg on 2018/11/16
 */
public class SalaryCardInfoProvider {

    public String querySalaryCardInfoSql(@Param("corpid") String corpid,@Param("name") String name, @Param("department_id") String department_id, @Param("bank_sname") String bank_sname, @Param("state_ch") String state_ch)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" SELECT sa.Fmodify_time,u.Fcorpid,u.Fuserid, u.Fname,cd.Fdepartment_id,cd.Fdepartment_name,sa.Fbind_card_number,sa.Fbind_card_bank_sname, ") ;
        stringBuffer.append("  sa.Fremark,sa.Fstate,CASE sa.Fstate when 201 then '已开通' ELSE '未开通' END as Fstate_ch  ") ;
        stringBuffer.append("  FROM t_user_info u ") ;
        stringBuffer.append("  LEFT JOIN t_salary_account sa ON u.Fuserid = sa.Fuserid AND u.Fcorpid = sa.Fcorpid ");
        stringBuffer.append("  LEFT JOIN t_corp_department  cd  ON u.Fdepartment = concat('[', cd.Fdepartment_id,']') AND u.Fcorpid = cd.Fcorpid " );
        stringBuffer.append("  WHERE u.Fcorpid = #{corpid}  ");
        if(StringUtils.isNotBlank(name))
        {
            stringBuffer.append(" AND u.Fname like CONCAT('%',#{name},'%') ");
        }
        if(StringUtils.isNotBlank(department_id))
        {
            stringBuffer.append(" AND cd.Fdepartment_id = CONVERT(#{department_id},SIGNED) ");
        }
        if(StringUtils.isNotBlank(bank_sname))
        {
            stringBuffer.append(" AND sa.Fbind_card_bank_sname = #{bank_sname}  ");
        }
        if(StringUtils.isNotBlank(state_ch))
        {
            int state = Integer.parseInt(state_ch);
            if (state == SalaryAccount.State.ACCOUNT_OPENED.toInt()){
                stringBuffer.append(" AND sa.Fstate =   ");
                stringBuffer.append(state);
            }else {
                stringBuffer.append(" AND sa.Fstate != ");
                stringBuffer.append(SalaryAccount.State.ACCOUNT_OPENED.toInt());
            }

        }
        stringBuffer.append("  ORDER BY sa.Fmodify_time DESC  ");
        return stringBuffer.toString();
    }
}
