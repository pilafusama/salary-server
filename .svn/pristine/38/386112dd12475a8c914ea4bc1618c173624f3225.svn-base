package com.tenpay.wxwork.salary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tenpay.wxwork.salary.model.User;

// 示例代码，待删除
@Mapper
public interface UserDAO {
	@Select("select fuser_id, fname, fsession_id from t_user where fuser_id = #{userId}")
	public User getUserById(String corpId, @Param("userId") String userId);
	
	@Select("select fuser_id, fname, fsession_id from t_user where fsession_id = #{sessionId}")
	public User getSessionById(@Param("sessionId") String sessionId);
}
