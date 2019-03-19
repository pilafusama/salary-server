package com.tenpay.wxwork.salary.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by win7 on 2018/10/17.
 */
@Mapper
public interface MsgNotifyDAO {

    @Insert("INSERT INTO t_msgnotify "+
            "(Fbusi_type,Fbusi_id,Fstate,Fretry_count,Flast_notify_time,Fcreate_time,Fmodify_time) "+
            "VALUES " +
            "(#{busi_type},#{busi_id},#{state},#{retry_count},NOW(),NOW(),NOW())")
    public void insertMsgNotify(@Param("busi_type") int busi_type,
                                @Param("busi_id") String busi_id,
                                @Param("state") String state,
                                @Param("retry_count") String retry_count
                                );
}
