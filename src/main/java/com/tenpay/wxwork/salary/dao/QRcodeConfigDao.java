package com.tenpay.wxwork.salary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tenpay.wxwork.salary.dto.admin.BankInfo;
import com.tenpay.wxwork.salary.dto.admin.QRcodeConfigParam;

@Mapper
public interface QRcodeConfigDao {

	@Select("select Fpromotion_qrcode_id, Fbank_name, Fqrcode_name, Fcreate_time, Fmodify_time from t_promotion_qrcode where Fstate = 'USABLE'")
	@Results(id="listQRcodes",value = {
            @Result(property = "promotion_qrcode_id",column ="Fpromotion_qrcode_id"),
            @Result(property = "bank_name",column ="Fbank_name"),
            @Result(property = "qrcode_name",column ="Fqrcode_name"),
            @Result(property = "create_time",column ="Fcreate_time"),
            @Result(property = "modify_time",column ="Fmodify_time")
    })
	List<QRcodeConfigParam> listQRcodes();
	
	@Select("select Fbank_sname, Fbank_name, Fqrcode_name, Ftemplate_id, Fqrcode_logo from t_promotion_qrcode"
			+ " where Fpromotion_qrcode_id = #{promotion_qrcode_id}")
	@Results(id="queryQRcodeById",value = {
			@Result(property = "bank_sname",column ="Fbank_sname"),
            @Result(property = "bank_name",column ="Fbank_name"),
            @Result(property = "qrcode_name",column ="Fqrcode_name"),
            @Result(property = "template_id",column ="Ftemplate_id"),
            @Result(property = "qrcode_logo",column ="Fqrcode_logo")
    })
	QRcodeConfigParam queryQRcodeById(@Param("promotion_qrcode_id") int promotion_qrcode_id);
	
	@Insert("insert into t_promotion_qrcode(Fbank_sname, Fbank_name, Fqrcode_name, Ftemplate_id, Fqrcode_logo, Fcreate_time) "
			+ "values(#{bank_sname}, #{bank_name}, #{qrcode_name}, #{template_id}, #{qrcode_logo}, #{create_time})")
	void insertQRcode(@Param("bank_sname") String bank_sname, @Param("bank_name") String bank_name,
			@Param("qrcode_name") String qrcode_name, @Param("template_id") String template_id,
			@Param("qrcode_logo") String qrcode_logo, @Param("create_time") String create_time);

	@Select("select LAST_INSERT_ID()")
	int getLastInsertId();

	@Update("update t_promotion_qrcode set Fbank_sname = #{bank_sname}, Fbank_name = #{bank_name}, Fqrcode_name = #{qrcode_name}, "
			+ "Fqrcode_logo = #{qrcode_logo}, Fmodify_time = #{modify_time} where Fpromotion_qrcode_id = #{promotion_qrcode_id}")
	void updateQRcode(@Param("promotion_qrcode_id") int promotion_qrcode_id,  @Param("bank_sname") String bank_sname, @Param("bank_name") String bank_name, 
			@Param("qrcode_name") String qrcode_name, @Param("qrcode_logo") String qrcode_logo, @Param("modify_time") String modify_time);

	@Select("select Fbank_sname,Fvalue from t_salary_bank_conf where Fstate = 'AUDITED' and Fkey = 'BANK_NAME'")
	@Results(id="queryBankList",value = {
            @Result(property = "bank_sname",column ="Fbank_sname"),
            @Result(property = "bank_name",column ="Fvalue")
    })
	List<BankInfo> queryBankList();
	

}
