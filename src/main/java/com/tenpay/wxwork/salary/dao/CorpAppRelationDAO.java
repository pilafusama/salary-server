package com.tenpay.wxwork.salary.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.tenpay.wxwork.salary.model.CorpAppRelation;
import com.tenpay.wxwork.salary.sqlprovider.CorpAppRelationProvider;

@Mapper
public interface CorpAppRelationDAO {
	@Select("select Fcorpid,Fapp_id,Fbank_type,Fop_userid,Fbank_uin,Fop_phone,Fstatus,Fauthen_status,Fauthen_id,Fauthen_time,Fcreate_time,Fmodify_time from t_corp_app_relation where Fcorpid = #{corpid} and Fapp_id = #{appId}")
	@Results(id="CorpAppRelationResultMap",value = { 
			@Result(property = "corpid",column ="Fcorpid"),
			@Result(property = "appId",column ="Fapp_id"),
			@Result(property = "bankType",column ="Fbank_type"),
			@Result(property = "opUserid",column ="Fop_userid"),
			@Result(property = "bankUin",column ="Fbank_uin"),
			@Result(property = "opPhone",column ="Fop_phone"),
			@Result(property = "status",column ="Fstatus"),
			@Result(property = "authenStatus",column ="Fauthen_status"),
			@Result(property = "authenId",column ="Fauthen_id"),
			@Result(property = "authenTime",column ="Fauthen_time"),
			@Result(property = "createTime",column ="Fcreate_time"),
			@Result(property = "modifyTime",column ="Fmodify_time")})
	public CorpAppRelation queryCorpAppRelation(@Param("corpid")String corpid,@Param("appId")String appId);

	@UpdateProvider(type = CorpAppRelationProvider.class, method = "updateCorpAppRelationOpenFromOpenSql")
	public void updateCorpAppRelationFromOpen(@Param("corpid")String corpid,@Param("appId")String appId,@Param("bankType")String bankType, @Param("bankUin")String bankUin, @Param("opUserId")String opUserId, @Param("opUserPhone")String opUserPhone);
	
	@UpdateProvider(type = CorpAppRelationProvider.class, method = "updateCorpAppRelationOpenFromCloseSql")
	public void updateCorpAppRelationFromClose(@Param("corpid")String corpid,@Param("appId")String appId,@Param("bankType")String bankType, @Param("bankUin")String bankUin, @Param("opUserId")String opUserId, @Param("opUserPhone")String opUserPhone);
	
	@UpdateProvider(type = CorpAppRelationProvider.class, method = "updateCorpAppRelationOpenFromStopSql")
	public void updateCorpAppRelationFromStop(@Param("corpid")String corpid,@Param("appId")String appId,@Param("bankType")String bankType, @Param("bankUin")String bankUin, @Param("opUserId")String opUserId, @Param("opUserPhone")String opUserPhone);
	
	@Insert("INSERT INTO t_corp_app_relation (Fcorpid,Fapp_id,Fbank_type,Fop_userid,Fbank_uin,Fop_phone,Fstatus,Fcreate_time,Fmodify_time) values (#{corpid},#{appId},#{bankType},#{opUserId},#{bankUin},#{opPhone},#{status},now(), now())")
	public void insertCorpAppRelation(@Param("corpid")String corpid,@Param("appId")String appId,@Param("bankType")String bankType, @Param("bankUin")String bankUin, @Param("opUserId")String opUserId, @Param("opPhone")String opPhone,@Param("status")String status);
	
	@Update("update t_corp_app_relation set Fauthen_status = 2,Fauthen_time = now(), Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fauthen_status = 1")
	public void updateAuthenStatusCloseFromNormal(@Param("corpid")String corpid,@Param("appId")String appId);
	
	@Update("update t_corp_app_relation set Fauthen_id = #{corpAuthId},Fauthen_status = 1,Fauthen_time = now(), Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fauthen_status = 0")
	public void updateAuthenStatusNormalFromInitial(@Param("corpid")String corpid,@Param("appId")String appId,@Param("corpAuthId")String corpAuthId);		
	
	@Update("update t_corp_app_relation set Fauthen_id = #{corpAuthId},Fauthen_status = 1,Fauthen_time = now(), Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fauthen_status = 2")
	public void updateAuthenStatusNormalFromClose(@Param("corpid")String corpid,@Param("appId")String appId,@Param("corpAuthId")String corpAuthId);
	
	@Update("update t_corp_app_relation set Fstatus = 'OPEN',Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fstatus = 'CLOSE'")
	public void updateStatusOpenFromClose(@Param("corpid")String corpid, @Param("appId")String appId);
	
	@Update("update t_corp_app_relation set Fstatus = 'OPEN',Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fstatus = 'STOP'")
	public void updateStatusOpenFromStop(@Param("corpid")String corpid, @Param("appId")String appId);
	
	@Update("update t_corp_app_relation set Fstatus = 'STOP',Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fstatus = 'OPEN'")
	public void updateStatusStopFromOpen(@Param("corpid")String corpid, @Param("appId")String appId);

	@Update("update t_corp_app_relation set Fstatus = 'CLOSE',Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fstatus = 'OPEN'")
	public void updateStatusCloseFromOpen(@Param("corpid")String corpid, @Param("appId")String appId);
	
	@Update("update t_corp_app_relation set Fstatus = 'CLOSE',Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fstatus = 'STOP'")
	public void updateStatusCloseFromStop(@Param("corpid")String corpid, @Param("appId")String appId);		
}
