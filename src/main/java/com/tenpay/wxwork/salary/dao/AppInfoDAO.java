package com.tenpay.wxwork.salary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;

import com.tenpay.wxwork.salary.model.AppInfo;

@Mapper
public interface AppInfoDAO {
  
    @Select("SELECT Fapp_id, Fapp_name, Fapp_url, Fapp_square_logo, Fapp_round_logo, Fapp_desc, Fmch_id, Ftype, Fbank_sname, Fbank_type, Fbusiness_style, Fallow_party, Fallow_tag, Fallow_user, Fstate, Fapproved_state, Frefuse_desc, Fapp_scret, Fcreate_time, Fmodify_time FROM t_app_info where Fmch_id = #{mchId} AND Ftype=#{type} AND Fbusiness_style=#{businessStyle}")
    @Results(id="resultMap",value = {
            @Result(property="corpid", column="Fcorpid"),
            @Result(property="appId", column="Fapp_id"),
            @Result(property="appName", column="Fapp_name"),
            @Result(property="appUrl", column="Fapp_url"),
            @Result(property="appSquareLogo", column="Fapp_square_logo"),
            @Result(property="appRoundLogo", column="Fapp_round_logo"),
            @Result(property="appDesc", column="Fapp_desc"),
            @Result(property="mchId", column="Fmch_id"),
            @Result(property="type", column="Ftype"),
            @Result(property="bankSname", column="Fbank_sname"),
            @Result(property="bankType", column="Fbank_type"),
            @Result(property="businessStyle", column="Fbusiness_style"),
            @Result(property="allowParty", column="Fallow_party"),
            @Result(property="allowTag", column="Fallow_tag"),
            @Result(property="allowUser", column="Fallow_user"),
            @Result(property="state", column="Fstate"),
            @Result(property="approvedState", column="Fapproved_state"),
            @Result(property="refuseDesc", column="Frefuse_desc"),
            @Result(property="appScret", column="Fapp_scret"),
            @Result(property="createTime", column="Fcreate_time"),
            @Result(property="modifyTime", column="Fmodify_time")
        })
        // 按照金融机构、应用类型和业务类型查询，应该只有一个记录
        public AppInfo queryByMchAndTypeAndBusiness(@Param("mchId") String mchId,
                                                    @Param("type") int type,
                                                    @Param("businessStyle") String businessStyle);

}
