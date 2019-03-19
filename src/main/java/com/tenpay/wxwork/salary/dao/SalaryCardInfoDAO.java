package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.dto.admin.SalaryFieldsReq;
import com.tenpay.wxwork.salary.model.SalaryCardInfo;
import com.tenpay.wxwork.salary.sqlprovider.SalaryCardInfoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by wg on 2018/11/15
 */

@Mapper
public interface SalaryCardInfoDAO {

    //根据corpid查询公司中人的账户信息
    @SelectProvider(type = SalaryCardInfoProvider.class, method = "querySalaryCardInfoSql")
    @Results(id="SalaryCardInfoMap",value = {
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "userid",column ="Fuserid"),
            @Result(property = "name",column ="Fname"),
            @Result(property = "departmentId",column ="Fdepartment_id"),
            @Result(property = "departmentName",column ="Fdepartment_name"),
            @Result(property = "bindCardNumber",column ="Fbind_card_number"),
            @Result(property = "bindCardBankSname",column ="Fbind_card_bank_sname"),
            @Result(property = "remark",column ="Fremark"),
            @Result(property = "state",column ="Fstate"),
            @Result(property = "state_ch",column ="Fstate_ch")
    })
    public List<SalaryCardInfo> querySalaryCardInfo(@Param("corpid") String corpid, @Param("name") String name, @Param("department_id") String department_id, @Param("bank_sname") String bank_sname, @Param("state_ch") String state_ch);


    //单条修改账户表
    @Update("UPDATE t_salary_account SET " +
            "Fbind_card_number = #{bindCardNumber}, " +
            "Fbind_card_bank_sname = #{bindCardBankSname}, "  +
            "Fbind_card_bank_number = #{bindCardBankNumber},Fremark = #{fremark}, "  +
            "Fmodify_time = NOW() " +
            "WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} ")
    public void updateSalaryCardInfo(@Param("corpid") String corpid,
                               @Param("userid") String userid,
                               @Param("bindCardNumber") String bindCardNumber,
                               @Param("bindCardBankSname") String bindCardBankSname,
                               @Param("bindCardBankNumber") String bindCardBankNumber,
                                @Param("fremark") String fremark);


    /**
     * 插入单条账户表
     */
    @Insert("INSERT INTO t_salary_account" +
            "(Fcorpid, Fuserid,Fstate,Fbind_card_number,Fbind_card_bank_sname, Fbind_card_bank_number,Fremark, " +
            " Fcreate_time, Fmodify_time) VALUES " +
            "(#{corpid}, #{userid}, #{fstate},#{bindCardNumber}, #{bindCardBankSname}, #{bindCardBankNumber},#{fremark},NOW(), NOW())")
    public void insertSalaryCardInfo(@Param("corpid") String corpid,
                                     @Param("userid") String userid,
                                     @Param("fstate") int fstate,
                                     @Param("bindCardNumber") String bindCardNumber,
                                     @Param("bindCardBankSname") String bindCardBankSname,
                                     @Param("bindCardBankNumber") String bindCardBankNumber,
                                     @Param("fremark") String fremark);



    /**
     *根据企业ID查询员工发卡银行
     **/
    @Select(" select distinct(s.Fbind_card_bank_sname) from  t_salary_account s where s.Fcorpid =  #{corpid} And s.Fbind_card_bank_sname is not null and s.Fbind_card_bank_sname != '' ")
    @Results(id="selectBankSnameByCorpIdMap",value = {
            @Result(property = "salary",column ="Fbind_card_bank_sname")
    })
    public List<SalaryFieldsReq> selectBankSnameByCorpId(@Param("corpid") String corpid);


    /**
     *根据企业ID查询员工部门
     **/
    @Select(" SELECT Fdepartment_id,Fdepartment_name FROM t_corp_department WHERE Fcorpid = #{corpid} ")
    @Results(id="selectDepartmentNameByCorpIdMap",value = {
            @Result(property = "salary",column ="Fdepartment_id"),
            @Result(property = "deduction",column ="Fdepartment_name")
    })
    public List<SalaryFieldsReq> selectDepartmentNameByCorpId(@Param("corpid") String corpid);

}
