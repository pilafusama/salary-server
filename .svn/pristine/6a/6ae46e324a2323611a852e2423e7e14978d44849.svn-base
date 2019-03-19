package com.tenpay.wxwork.salary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;

import com.tenpay.wxwork.salary.model.CorpAuthen;
import com.tenpay.wxwork.salary.sqlprovider.CorpAuthenProvider;

@Mapper
public interface CorpAuthenDAO {
  
    @Select("SELECT Fcorpid, Fplat_app_id, Fagent_id, Fauth_status, Fpermanent_code, Fauth_ok_time, Fapp_level, Fallow_party, Fallow_tag, Fallow_user, Fextra_party, Fextra_tag, Fextra_user, Fcreate_time, Fmodify_time FROM t_corp_authen where Fcorpid = #{corpid} AND Fplat_app_id=#{platAppId}")
    @Results(id="corpInfoResultMap",value = { 			
            @Result(property="corpid", column="Fcorpid"),
            @Result(property="platAppId", column="Fplat_app_id"),
            @Result(property="agentId", column="Fagent_id"),
            @Result(property="authStatus", column="Fauth_status"),
            @Result(property="permanentCode", column="Fpermanent_code"),
            @Result(property="authOkTime", column="Fauth_ok_time"),
            @Result(property="appLevel", column="Fapp_level"),
            @Result(property="allowParty", column="Fallow_party"),
            @Result(property="allowTag", column="Fallow_tag"),
            @Result(property="allowUser", column="Fallow_user"),
            @Result(property="extraParty", column="Fextra_party"),
            @Result(property="extraTag", column="Fextra_tag"),
            @Result(property="extraUser", column="Fextra_user"),
            @Result(property="createTime", column="Fcreate_time"),
            @Result(property="modifyTime", column="Fmodify_time")
        })
        public CorpAuthen queryByCorpidAndSuiteId(@Param("corpid") String corpid,
                                                  @Param("platAppId") String platAppId);

    /**
     * 插入从企业微信过来的授权信息
     *
     */
    @InsertProvider(type=CorpAuthenProvider.class, method="insertWhenAuth")
    public int insertWhenAuth(@Param("corpid") String corpid,
                              @Param("platAppId") String platAppId,
                              @Param("agentId") int agentId,
                              @Param("authStatus") int authStatus,
                              @Param("permanentCode") String permanentCode,
                              @Param("appLevel") int appLevel,
                              @Param("allowParty") String allowParty,
                              @Param("allowTag") String allowTag,
                              @Param("allowUser") String allowUser,
                              @Param("extraParty") String extraParty,
                              @Param("extraTag") String extraTag,
                              @Param("extraUser") String extraUser);

    /**
     * 更新授权信息
     *
     */
    @InsertProvider(type=CorpAuthenProvider.class, method="updateWhenAuth")
    public int updateWhenAuth(@Param("corpid") String corpid,
                              @Param("platAppId") String platAppId,
                              @Param("agentId") int agentId,
                              @Param("authStatus") int authStatus,
                              @Param("permanentCode") String permanentCode,
                              @Param("appLevel") int appLevel,
                              @Param("allowParty") String allowParty,
                              @Param("allowTag") String allowTag,
                              @Param("allowUser") String allowUser,
                              @Param("extraParty") String extraParty,
                              @Param("extraTag") String extraTag,
                              @Param("extraUser") String extraUser);

    @UpdateProvider(type = CorpAuthenProvider.class, method = "cancelAuth")
    public int cancelAuth(@Param("corpid") String corpid,
                          @Param("platAppId") String platAppId);

}
