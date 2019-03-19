package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.model.SalaryFields;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SalaryFieldsDAO {

    @Select("select  Fcorpid, Fsalary_fields, Fdeduction_fields, Fremark, Ftemplate ," +
            "Fcreate_time, Fmodify_time " +
            "FROM t_salary_fields WHERE Fcorpid = #{corpid} AND Ftemplate = #{template} ")
    @Results(id="SalaryFieldsMap",value = {
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "salaryFields",column ="Fsalary_fields"),
            @Result(property = "deductionFields",column ="Fdeduction_fields"),
            @Result(property = "remark",column ="Fremark"),
            @Result(property = "template",column ="Ftemplate"),
            @Result(property = "createTime",column ="Fcreate_time"),
            @Result(property = "modifyTime",column ="Fmodify_time")
        })
    public SalaryFields querySalaryFields(@Param("corpid") String corpid ,@Param("template") String template);


    @Insert("INSERT INTO t_salary_fields " +
            "(Fcorpid, Fsalary_fields, Fdeduction_fields, Fremark,Ftemplate, " +
            " Fcreate_time,Fmodify_time) VALUES " +
            " (#{corpid}, #{salaryFields}, #{deductionFields}, #{remark},#{template},now(), now())")
    public void insertSalaryFields(@Param("corpid") String corpid,
                                   @Param("salaryFields") String salaryFields,
                                   @Param("deductionFields") String deductionFields,
                                   @Param("remark") String remark,
                                   @Param("template") String template
    );


    @Update("UPDATE t_salary_fields SET " +
            "Fsalary_fields = #{salaryFields}, " +
            "Fdeduction_fields = #{deductionFields}, " +
            "Fremark = #{remark}, " +
            "Fmodify_time = NOW() " +
            "WHERE Fcorpid = #{corpid} AND Ftemplate = #{template} ")
    public void updateByCorpid(@Param("corpid") String corpid,
                                        @Param("salaryFields") String salaryFields,
                                        @Param("deductionFields") String deductionFields,
                                        @Param("remark") String remark,
                                        @Param("template") String template
    );


}