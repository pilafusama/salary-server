package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.model.MchInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MchInfoDAO {

    @Select("select" +
            " Fmch_id, Fmch_name, Fservice_phone, Fmch_developer, Fwxsp_id, Fcontact, Fconact_phone," +
            " Fconact_mail, Ftype, Fmch_status, Fsing_type, Fsign_key, Fcreate_time, Fmodify_time" +
            " from t_mch_info" +
            " where Fmch_id = #{fmchId}")
    @Results(id="mchInfoResult",value = {
            @Result(property = "fmchId", column ="Fmch_id" ),
            @Result(property="fmchName", column="Fmch_name" ),
            @Result(property="fservicePhone", column="Fservice_phone"),
            @Result(property="fmchDeveloper", column="Fmch_developer" ),
            @Result(property="fwxspId", column="Fwxsp_id" ),
            @Result(property="fcontact", column="Fcontact" ),
            @Result(property="fconactPhone", column="Fconact_phone"),
            @Result(property="fconactMail", column="Fconact_mail"),
            @Result(property="ftype", column="Ftype" ),
            @Result(property="fmchStatus", column="Fmch_status"),
            @Result(property="fsingType", column="Fsing_type"),
            @Result(property="fsignKey", column="Fsign_key"),
            @Result(property="fcreateTime", column="Fcreate_time"),
            @Result(property="fmodifyTime", column="Fmodify_time")
    })
    public MchInfo queryMchInfo(@Param("fmchId") String fmchId);

    @Select("select" +
            " Fmch_id, Fmch_name, Fservice_phone, Fmch_developer, Fwxsp_id, Fcontact, Fconact_phone," +
            " Fconact_mail, Ftype, Fmch_status, Fsing_type, Fsign_key, Fcreate_time, Fmodify_time" +
            " from t_mch_info" +
            " where Fmch_status = '1' and Fmch_name = #{fmchName} limit 1")
    @Results(id="mcchInfoByName",value = {
            @Result(property = "fmchId", column ="Fmch_id" ),
            @Result(property="fmchName", column="Fmch_name" ),
            @Result(property="fservicePhone", column="Fservice_phone"),
            @Result(property="fmchDeveloper", column="Fmch_developer" ),
            @Result(property="fwxspId", column="Fwxsp_id" ),
            @Result(property="fcontact", column="Fcontact" ),
            @Result(property="fconactPhone", column="Fconact_phone"),
            @Result(property="fconactMail", column="Fconact_mail"),
            @Result(property="ftype", column="Ftype" ),
            @Result(property="fmchStatus", column="Fmch_status"),
            @Result(property="fsingType", column="Fsing_type"),
            @Result(property="fsignKey", column="Fsign_key"),
            @Result(property="fcreateTime", column="Fcreate_time"),
            @Result(property="fmodifyTime", column="Fmodify_time")
    })
    public MchInfo queryMchInfoByName(@Param("fmchName") String fmchName);
}
