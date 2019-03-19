package com.tenpay.wxwork.salary.sqlprovider;

import com.tenpay.wxwork.salary.model.UserInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

public class UserInfoProvider {

    public String updateBasicInfoSelectively(@Param("corpid") String corpid,
                                             @Param("userid") String userid,
                                             @Param("newUserid") String newUserid,
                                             @Param("name") String name,
                                             @Param("status") String status,
                                             @Param("department") String department,
                                             @Param("gender") String gender,
                                             @Param("avatar") String avatar,
                                             @Param("lastPlatAppid") String lastPlatAppid) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE t_user_info SET Fmodify_time=NOW()");
        if (null != newUserid && newUserid.length() > 0) {
            builder.append(", Fuserid=#{newUserid}");
        }
        if (null != name && name.length() > 0) {
            builder.append(", Fname=#{name}");
        }
        if (null != status && status.length() > 0) {
            builder.append(", Fstatus=#{status}");
        }
        if (null != department && department.length() > 0) {
            builder.append(", Fdepartment=#{department}");
        }
        if (null != gender && gender.length() > 0) {
            builder.append(", Fgender=#{gender}");
        }
        if (null != avatar && avatar.length() > 0) {
            builder.append(", Favatar=#{avatar}");
        }
        if (null != lastPlatAppid && lastPlatAppid.length() > 0) {
            builder.append(", Flast_plat_appid=#{lastPlatAppid}");
        }
        builder.append(" WHERE Fcorpid=#{corpid} AND Fuserid=#{userid} LIMIT 2");

        return builder.toString();
    }
}
