package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.model.SalaryCorpConfInfo;
import org.apache.ibatis.annotations.*;


@Mapper
public interface SalaryCorpConfDAO {

    @Select(" select Fcorpid,Fstate,Ftype,Fkey,Fvalue " +
            " from t_salary_corp_conf where Fcorpid = #{corpid} and Fkey = #{key} and Fstate = #{state}")
    @Results(id="querySalaryCorpConfInfo",value = {
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "state",column ="Fstate"),
            @Result(property = "type",column ="Ftype"),
            @Result(property = "key",column ="Fkey"),
            @Result(property = "value",column ="Fvalue")
    })
    public SalaryCorpConfInfo querySalaryCorpConfInfo(@Param("corpid") String corpid, @Param("key") String key,@Param("state") String state);

    /**
     * 插入从企业微信获取的银行配置信息
     *
     */
    @Insert("INSERT INTO t_salary_corp_conf SET Fcorpid=#{corpid}, Fstate=#{state}, Ftype=#{type}, Fkey=#{key}, Fvalue=#{value}, Fcreate_time=NOW(), Fmodify_time=NOW()")
    public int insertCorpConf(@Param("corpid") String corpid,
                               @Param("state") String state,
                               @Param("type") String type,
                               @Param("key") String key,
                               @Param("value") String value);

    /**
     * 更新从企业微信获取的银行配置信息
     *
     */
    @Insert("UPDATE t_salary_corp_conf SET Fcorpid=#{corpid}, Fstate=#{state}, Ftype=#{type}, Fkey=#{key}, Fvalue=#{value}, Fcreate_time=NOW(), Fmodify_time=NOW() WHERE Fcorpid=#{corpid} AND Fkey=#{key} LIMIT 1")
    public int updateCorpConf(@Param("corpid") String corpid,
                              @Param("state") String state,
                              @Param("type") String type,
                              @Param("key") String key,
                              @Param("value") String value);
}
