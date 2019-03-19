package com.tenpay.wxwork.salary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.tenpay.wxwork.salary.model.SalaryBankConfInfo;

@Mapper
public interface HomepageEntriesDao {

	@Select("select Fvalue from t_salary_bank_conf where Fbank_sname = #{bankSname} "
			+ "and Fstate = 'AUDITED' and Fkey like 'GRID_1_1%'")
	@Results(id="getGridsInfo",value = {
            @Result(property = "value",column ="Fvalue")
    })
	public List<SalaryBankConfInfo> getGridsInfo(@Param("bankSname") String bankSname);

	@Select("select Fvalue from t_salary_bank_conf where Fbank_sname = #{bankSname} "
			+ "and Fstate = 'AUDITED' and Fkey = 'GRID_1_TITLE'")
	@Results(id="getGridTopic",value = {
            @Result(property = "value",column ="Fvalue")
    })
	public SalaryBankConfInfo getGridTopic(String bankSname);
	
}
