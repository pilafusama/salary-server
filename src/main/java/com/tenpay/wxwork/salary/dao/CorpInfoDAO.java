package com.tenpay.wxwork.salary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;

import com.tenpay.wxwork.salary.model.CorpInfo;
import com.tenpay.wxwork.salary.sqlprovider.CorpInfoProvider;

@Mapper
public interface CorpInfoDAO {
	@Select("select Fcorpid,Fcorp_name,Fcert_id,Fcorp_type,Fsubject_type,Fcorp_square_logo_url,Fcorp_user_max,Fcorp_full_name,Fverified_end_time,Fcorp_wxqrcode,Fsrc_mch_id,Fsrc_app_id,Fcorp_status,Fcreate_time,Fmodify_time from t_corp_info where Fcorpid = #{corpid}")
	@Results(id="corpInfoResultMap",value = { 			
			@Result(property = "corpid",column = "Fcorpid"),
			@Result(property = "corpName",column = "Fcorp_name"),
			@Result(property = "certId",column = "Fcert_id"),
			@Result(property = "corpType",column = "Fcorp_type"),
			@Result(property = "subjectType",column = "Fsubject_type"),
			@Result(property = "corpSquareLogoUrl",column = "Fcorp_square_logo_url"),
			@Result(property = "corpUserMax",column = "Fcorp_user_max"),
			@Result(property = "corpFullName",column = "Fcorp_full_name"),
			@Result(property = "verifiedEndTime",column = "Fverified_end_time"),
			@Result(property = "corpWxqrcode",column = "Fcorp_wxqrcode"),
			@Result(property = "srcMchId",column = "Fsrc_mch_id"),
			@Result(property = "srcAppId",column = "Fsrc_app_id"),
			@Result(property = "corpStatus",column = "Fcorp_status"),
			@Result(property = "createTime",column = "Fcreate_time"),
			@Result(property = "modifyTime",column = "Fmodify_time")})
	public CorpInfo queryCorpByCorpid(@Param("corpid") String corpid);

    /**
     * 插入从企业微信过来的授权信息
     *
     */
    @InsertProvider(type=CorpInfoProvider.class, method="insertCorpWhenAuth")
    public int insertCorpWhenAuth(@Param("corpid") String corpid,
                                  @Param("corpStatus") int corpStatus,
                                  @Param("corpName") String corpName,
                                  @Param("corpFullName") String corpFullName,
                                  @Param("corpType") String corpType,
                                  @Param("subjectType") int subjectType,
                                  @Param("corpSquareLogoUrl") String corpSquareLogoUrl,
                                  @Param("corpUserMax") int corpUserMax,
                                  @Param("verifiedEndTime") String verifiedEndTime,
                                  @Param("corpWxqrcode") String corpWxqrcode,
                                  @Param("srcAppId") String srcAppId);

    /**
     * 更新授权信息
     *
     */
    @InsertProvider(type=CorpInfoProvider.class, method="updateCorpWhenAuth")
    public int updateCorpWhenAuth(@Param("corpid") String corpid,
                                  @Param("corpStatus") int corpStatus,
                                  @Param("corpName") String corpName,
                                  @Param("corpFullName") String corpFullName,
                                  @Param("corpType") String corpType,
                                  @Param("subjectType") int subjectType,
                                  @Param("corpSquareLogoUrl") String corpSquareLogoUrl,
                                  @Param("corpUserMax") int corpUserMax,
                                  @Param("verifiedEndTime") String verifiedEndTime,
                                  @Param("corpWxqrcode") String corpWxqrcode);

    @UpdateProvider(type = CorpInfoProvider.class, method = "cancelAuth")
    public int cancelAuth(String corpid);

    /**
     * 审批流使用
     *
     */
	@UpdateProvider(type = CorpInfoProvider.class, method = "updateCorpInfoSql")
	public void updateCorpInfo(@Param("corpid") String corpid,
                               @Param("corpName") String corpName,
                               @Param("certId") String certId);
}
