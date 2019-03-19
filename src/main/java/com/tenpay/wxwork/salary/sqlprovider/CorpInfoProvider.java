package com.tenpay.wxwork.salary.sqlprovider;

import com.tenpay.wxwork.salary.model.CorpInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

public class CorpInfoProvider {
    public String updateCorpInfoSql(@Param("corpid")String corpid,
                                    @Param("corpName")String corpName,
                                    @Param("certId")String certId)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	stringBuffer.append("update t_corp_info set ");
    	if(StringUtils.isNotBlank(corpName))
    	{
    		stringBuffer.append("Fcert_id = #{certId},");
    	}
    	stringBuffer.append("Fcorp_name = #{corpName},Fmodify_time = now() where Fcorpid = #{corpid}");
        return stringBuffer.toString();
    }

    public String insertCorpWhenAuth(@Param("corpid") String corpid,
                                     @Param("corpStatus") int corpStatus,
                                     @Param("corpName") String corpName,
                                     @Param("corpFullName") String corpFullName,
                                     @Param("corpType") String corpType,
                                     @Param("subjectType") int subjectType,
                                     @Param("corpSquareLogoUrl") String corpSquareLogoUrl,
                                     @Param("corpUserMax") int corpUserMax,
                                     @Param("verifiedEndTime") String verifiedEndTime,
                                     @Param("corpWxqrcode") String corpWxqrcode,
                                     @Param("srcAppId") String srcAppId) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("INSERT INTO t_corp_info SET Fcorpid=#{corpid}, Fcorp_status=#{corpStatus}, Fcorp_name=#{corpName}, ");
        stringBuffer.append("Fcorp_full_name=#{corpFullName}, Fcorp_type=#{corpType}, Fsubject_type=#{subjectType}, ");
        stringBuffer.append("Fcorp_square_logo_url=#{corpSquareLogoUrl}, Fcorp_user_max=#{corpUserMax}, ");
        stringBuffer.append("Fverified_end_time=#{verifiedEndTime}, Fcorp_wxqrcode=#{corpWxqrcode}, ");
        stringBuffer.append("Fsrc_app_id=#{srcAppId}, Fcreate_time=NOW(), Fmodify_time=NOW()");

        return stringBuffer.toString();
    }

    public String updateCorpWhenAuth(@Param("corpid") String corpid,
                                     @Param("corpStatus") int corpStatus,
                                     @Param("corpName") String corpName,
                                     @Param("corpFullName") String corpFullName,
                                     @Param("corpType") String corpType,
                                     @Param("subjectType") int subjectType,
                                     @Param("corpSquareLogoUrl") String corpSquareLogoUrl,
                                     @Param("corpUserMax") int corpUserMax,
                                     @Param("verifiedEndTime") String verifiedEndTime,
                                     @Param("corpWxqrcode") String corpWxqrcode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("UPDATE t_corp_info SET Fcorp_status=#{corpStatus}, Fcorp_name=#{corpName}, ");
        stringBuffer.append("Fcorp_full_name=#{corpFullName}, Fcorp_type=#{corpType}, Fsubject_type=#{subjectType}, ");
        stringBuffer.append("Fcorp_square_logo_url=#{corpSquareLogoUrl}, Fcorp_user_max=#{corpUserMax}, ");
        stringBuffer.append("Fverified_end_time=#{verifiedEndTime}, Fcorp_wxqrcode=#{corpWxqrcode}, ");
        stringBuffer.append("Fmodify_time=NOW() WHERE Fcorpid=#{corpid} LIMIT 2");

        return stringBuffer.toString();
    }

    /**
     * 取消授权
     *
     */
    public String cancelAuth(String corpid) {
        return String.format("UPDATE t_corp_info SET Fcorp_status=%d WHERE Fcorpid=#{corpid} LIMIT 2",
                             CorpInfo.CORP_STATUS_UNAUTHED);
    }
}
