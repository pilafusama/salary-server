package com.tenpay.wxwork.salary.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.alibaba.fastjson.JSONObject;


@Mapper
public interface AuthCkvDAO {

	
	@Update("update t_auth_ckv set Fauth_code = #{auth_code},Fexpired = date_add(now(), interval #{seconds} second), Fcreate_time = now() where Ftype = #{key} and Fsuiteid = #{suiteid} and Fcorpid=#{corpid} and Fuserid=#{userid}")
	public void updateAuthCkv(String auth_code, int seconds, String key, String suiteid, String corpid, String userid);	

}