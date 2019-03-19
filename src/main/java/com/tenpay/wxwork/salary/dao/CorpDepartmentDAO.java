package com.tenpay.wxwork.salary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.InsertProvider;

import com.tenpay.wxwork.salary.model.CorpDepartment;

@Mapper
public interface CorpDepartmentDAO {
	@Select("select Fcorp_department_id, Fcorpid, Fdepartment_id, Fdepartment_name, Fparent_department_id, Forder, Fcreate_time, Fmodify_time from t_corp_department where Fcorpid = #{corpid} and Fdepartment_id=#{departmentId}")
	@Results(id="resultMap",value = {
            @Result(property="corpDepartmentId", column="Fcorp_department_id"),
            @Result(property="corpid", column="Fcorpid"),
            @Result(property="departmentId", column="Fdepartment_id"),
            @Result(property="departmentName", column="Fdepartment_name"),
            @Result(property="parentDepartmentId", column="Fparent_department_id"),
            @Result(property="order", column="Forder"),
            @Result(property="createTime", column="Fcreate_time"),
            @Result(property="modifyTime", column="Fmodify_time")})
    public CorpDepartment queryByCorpidDepartmentId(@Param("corpid") String corpid,
                                                    @Param("departmentId") int departmentId);

    @Insert("INSERT INTO t_corp_department SET Fcorpid=#{corpid}, Fdepartment_id=#{departmentId}, Fdepartment_name=#{departmentName}, Fparent_department_id=#{parentDepartmentId}, Forder=#{order}, Fcreate_time=NOW(), Fmodify_time=NOW()")
    public void insert(@Param("corpid") String corpid,
                       @Param("departmentId") int departmentId,
                       @Param("departmentName") String departmentName,
                       @Param("parentDepartmentId") int parentDepartmentId,
                       @Param("order") int order);

    @Update("UPDATE t_corp_department SET Fdepartment_name=#{departmentName}, Fparent_department_id=#{parentDepartmentId}, Forder=#{order}, Fmodify_time=NOW() WHERE Fcorpid=#{corpid} AND Fdepartment_id=#{departmentId} LIMIT 1")
    public void update(@Param("corpid") String corpid,
                       @Param("departmentId") int departmentId,
                       @Param("departmentName") String departmentName,
                       @Param("parentDepartmentId") int parentDepartmentId,
                       @Param("order") int order);
}