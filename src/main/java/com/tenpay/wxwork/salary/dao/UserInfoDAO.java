package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.dto.admin.SalaryDetailInfo;
import com.tenpay.wxwork.salary.model.CorpDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;

import com.tenpay.wxwork.salary.model.UserInfo;
import com.tenpay.wxwork.salary.sqlprovider.UserInfoProvider;

import java.util.List;

@Mapper
public interface UserInfoDAO {
	@Select("SELECT Fcorpid, Fuserid, Fname, Fdepartment, Fposition, Fmobile, Fgender, Femail, Favatar, Flast_logintime, Flast_plat_appid, Fcreate_time, Fmodify_time FROM t_user_info where Fcorpid = #{corpid} AND Fuserid=#{userid}")
    @Results(id="resultMap",value = { 			
            @Result(property="corpid", column="Fcorpid"),
            @Result(property="userid", column="Fuserid"),
            @Result(property="name", column="Fname"),
            @Result(property="department", column="Fdepartment"),
            @Result(property="position", column="Fposition"),
            @Result(property="mobile", column="Fmobile"),
            @Result(property="gender", column="Fgender"),
            @Result(property="email", column="Femail"),
            @Result(property="avatar", column="Favatar"),
            @Result(property="lastLogintime", column="Flast_logintime"),
            @Result(property="lastPlatAppid", column="Flast_plat_appid"),
            @Result(property="createTime", column="Fcreate_time"),
            @Result(property="modifyTime", column="Fmodify_time")
        })
        public UserInfo queryByCorpidUserid(@Param("corpid") String corpid,
                                            @Param("userid") String userid);

    /**
     * 插入从企业微信获取的基本信息
     *
     */
    @Insert("INSERT INTO t_user_info SET Fcorpid=#{corpid}, Fuserid=#{userid}, Fname=#{name}, Fstatus=#{status}, Fdepartment=#{department}, Fgender=#{gender}, Favatar=#{avatar}, Fcreate_time=NOW(), Fmodify_time=NOW()")
    public int insertBasicInfo(@Param("corpid") String corpid,
                               @Param("userid") String userid,
                               @Param("name") String name,
                               @Param("status") String status,
                               @Param("department") String department,
                               @Param("gender") String gender,
                               @Param("avatar") String avatar,
                               @Param("lastPlatAppid") String lastPlatAppid);

    @Update("UPDATE t_user_info SET Fname=#{name}, Fstatus=#{status}, Fdepartment=#{department}, Fgender=#{gender}, Favatar=#{avatar}, Flast_plat_appid=#{lastPlatAppid}, Fmodify_time=NOW() WHERE Fcorpid=#{corpid} AND Fuserid=#{userid} LIMIT 2")
    public int updateBasicInfo(@Param("corpid") String corpid,
                               @Param("userid") String userid,
                               @Param("name") String name,
                               @Param("status") String status,
                               @Param("department") String department,
                               @Param("gender") String gender,
                               @Param("avatar") String avatar,
                               @Param("lastPlatAppid") String lastPlatAppid);

	@UpdateProvider(type = UserInfoProvider.class, method = "updateBasicInfoSelectively")
    public int updateBasicInfoSelectively(@Param("corpid") String corpid,
                                          @Param("userid") String userid,
                                          @Param("newUserid") String newUserid,
                                          @Param("name") String name,
                                          @Param("status") String status,
                                          @Param("department") String department,
                                          @Param("gender") String gender,
                                          @Param("avatar") String avatar,
                                          @Param("lastPlatAppid") String lastPlatAppid);
    
    @Insert("INSERT INTO t_user_info SET Fcorpid=#{corpid}, Fuserid=#{userid}, Fname=#{name}, Fstatus=#{status}, Fmobile=#{mobile}, Fgender=#{gender}, Femail=#{email}, Favatar=#{avatar}, Flast_plat_appid=#{lastPlatAppid}, Fcreate_time=NOW() Fmodify_time=NOW()")
    public int insertUserInfo(@Param("corpid") String corpid,
                              @Param("userid") String userid,
                              @Param("name") String name,
                              @Param("status") String status,
                              @Param("mobile") String mobile,
                              @Param("gender") String gender,
                              @Param("email") String email,
                              @Param("avatar") String avatar,
                              @Param("lastPlatAppid") String lastPlatAppid);

    @Update("UPDATE t_user_info SET Fname=#{name}, Fstatus=#{status}, Fmobile=#{mobile}, Fgender=#{gender}, Femail=#{email}, Favatar=#{avatar}, Flast_plat_appid=#{lastPlatAppid}, Fmodify_time=NOW() WHERE Fcorpid=#{corpid} AND Fuserid=#{userid} LIMIT 2")
    public int updateUserInfo(@Param("corpid") String corpid,
                              @Param("userid") String userid,
                              @Param("name") String name,
                              @Param("status") String status,
                              @Param("mobile") String mobile,
                              @Param("gender") String gender,
                              @Param("email") String email,
                              @Param("avatar") String avatar,
                              @Param("lastPlatAppid") String lastPlatAppid);

    @Select(" SELECT (@i:=@i+1) as sequence_number, Fname,Fuserid,Fdepartment FROM t_user_info,(select @i:=0) as it where Fcorpid = #{corpid}")
    @Results(id="queryUserInfo",value = {
            @Result(property = "name",column ="Fname"),
            @Result(property = "userid",column ="Fuserid"),
            @Result(property = "department",column ="Fdepartment")
    })
    public List<UserInfo> queryUserInfo(UserInfo UserInfo);

    @Select("SELECT Fcorpid, Fuserid, Fname FROM t_user_info where Fcorpid = #{corpid} AND Fname=#{userName}")
    @Results(id="queryUserId",value = {
            @Result(property="corpid", column="Fcorpid"),
            @Result(property="userid", column="Fuserid"),
            @Result(property="name", column="Fname")
    })
    public UserInfo queryUserId(@Param("corpid") String corpid,
                                        @Param("userName") String userName);

    @Select("select d.Fdepartment_id departmentId, d.Fdepartment_name departmentName from t_corp_department d left join t_user_info u on " +
            "u.Fcorpid = d.Fcorpid and u.Fdepartment = CONCAT('[', d.Fdepartment_id, ']') where u.Fcorpid = #{corpid} and u.Fname=#{userName}")
    public CorpDepartment findDepartmentInfo(@Param("corpid") String corpid,
                                             @Param("userName") String userName);

}
