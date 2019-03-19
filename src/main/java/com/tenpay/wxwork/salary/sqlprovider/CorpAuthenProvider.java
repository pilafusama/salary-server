package com.tenpay.wxwork.salary.sqlprovider;

import com.tenpay.wxwork.salary.model.CorpAuthen;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

public class CorpAuthenProvider {

    public String insertWhenAuth(@Param("corpid") String corpid,
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
                                 @Param("extraUser") String extraUser) {
        return "INSERT INTO t_corp_authen SET Fcorpid=#{corpid}, Fplat_app_id=#{platAppId}, Fagent_id=#{agentId}, Fauth_status=#{authStatus}, Fpermanent_code=#{permanentCode}, Fauth_ok_time=NOW(), Fapp_level=#{appLevel}, Fallow_party=#{allowParty}, Fallow_tag=#{allowTag}, Fallow_user=#{allowUser}, Fextra_party=#{extraParty}, Fextra_tag=#{extraTag}, Fextra_user=#{extraUser}, Fcreate_time=NOW(), Fmodify_time=NOW()";
    }

    public String updateWhenAuth(@Param("corpid") String corpid,
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
                                 @Param("extraUser") String extraUser) {
        return "UPDATE t_corp_authen SET Fagent_id=#{agentId}, Fauth_status=#{authStatus}, Fpermanent_code=#{permanentCode}, Fauth_ok_time=NOW(), Fapp_level=#{appLevel}, Fallow_party=#{allowParty}, Fallow_tag=#{allowTag}, Fallow_user=#{allowUser}, Fextra_party=#{extraParty}, Fextra_tag=#{extraTag}, Fextra_user=#{extraUser}, Fmodify_time=NOW() WHERE Fcorpid=#{corpid} AND Fplat_app_id=#{platAppId} LIMIT 2";
    }

    /**
     * 取消授权
     *
     */
    public String cancelAuth(@Param("corpid") String corpid,
                             @Param("platAppId") String platAppId) {
        return String.format("UPDATE t_corp_authen SET Fauth_status=%d WHERE Fcorpid=#{corpid} AND Fplat_app_id=#{platAppId} LIMIT 2", CorpAuthen.AUTH_STATUS_REVOKED);
    }
}
