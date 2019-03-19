package com.tenpay.wxwork.salary.sqlprovider;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

public class CorpAppRelationProvider {
	
	
	public String updateCorpAppRelationOpenFromOpenSql(@Param("corpid")String corpid,@Param("appId")String appId,@Param("bankType")String bankType,@Param("bankUin")String bankUin,@Param("opUserId")String opUserId,@Param("opUserPhone")String opUserPhone)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	stringBuffer.append("update t_corp_app_relation set ");
  
    	if(StringUtils.isNotBlank(opUserId))
    	{
    		stringBuffer.append("Fop_userid = #{opUserId},");
    	}
    	if(StringUtils.isNotBlank(opUserPhone))
    	{
    		stringBuffer.append("Fop_phone = #{opUserPhone},");
    	}
    	stringBuffer.append("Fbank_type = #{bankType}, Fbank_uin = #{bankUin},Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fstatus ='OPEN'");
        return stringBuffer.toString();
    }
	
	
	public String updateCorpAppRelationOpenFromStopSql(@Param("corpid")String corpid,@Param("appId")String appId,@Param("bankType")String bankType,@Param("bankUin")String bankUin,@Param("opUserId")String opUserId,@Param("opUserPhone")String opUserPhone)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	stringBuffer.append("update t_corp_app_relation set ");
  
    	if(StringUtils.isNotBlank(opUserId))
    	{
    		stringBuffer.append("Fop_userid = #{opUserId},");
    	}
    	if(StringUtils.isNotBlank(opUserPhone))
    	{
    		stringBuffer.append("Fop_phone = #{opUserPhone},");
    	}
    	stringBuffer.append("Fbank_type = #{bankType}, Fbank_uin = #{bankUin},Fstatus ='OPEN',Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fstatus ='STOP'");
        return stringBuffer.toString();
    }
	
	public String updateCorpAppRelationOpenFromCloseSql(@Param("corpid")String corpid,@Param("appId")String appId,@Param("bankType")String bankType,@Param("bankUin")String bankUin,@Param("opUserId")String opUserId,@Param("opUserPhone")String opUserPhone)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	stringBuffer.append("update t_corp_app_relation set ");
  
    	if(StringUtils.isNotBlank(opUserId))
    	{
    		stringBuffer.append("Fop_userid = #{opUserId},");
    	}
    	if(StringUtils.isNotBlank(opUserPhone))
    	{
    		stringBuffer.append("Fop_phone = #{opUserPhone},");
    	}
    	stringBuffer.append("Fbank_type = #{bankType}, Fbank_uin = #{bankUin},Fstatus ='OPEN',Fmodify_time = now() where Fcorpid = #{corpid} and Fapp_id = #{appId} and Fstatus ='CLOSE'");
        return stringBuffer.toString();
    }    
}
