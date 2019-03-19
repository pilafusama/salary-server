package com.tenpay.wxwork.salary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tenpay.wxwork.salary.model.GridInfo;

@Mapper
public interface GridConfigDao {

	@Select("select * from t_grid_info where Fbank_sname = #{bank_sname} and Fstate = 'USABLE' order by Fgrid_num")
	@Results(id="listGrids",value = {
            @Result(property = "grid_id",column ="Fgrid_id"),
            @Result(property = "grid_num",column ="Fgrid_num"),
            @Result(property = "grid_title",column ="Fgrid_title"),
            @Result(property = "grid_entry",column ="Fgrid_entry"),
            @Result(property = "grid_entry_type",column ="Fgrid_entry_type"),
            @Result(property = "grid_icon",column ="Fgrid_icon"),
            @Result(property = "corp_tag",column ="Fcorp_tag"),
            @Result(property = "user_tag",column ="Fuser_tag")
    })
	public List<GridInfo> listGrids(@Param("bank_sname") String bank_sname);

	@Insert("insert into t_grid_info(Fbank_sname, Fgrid_num, Fgrid_title, Fgrid_entry, Fgrid_entry_type, Fgrid_icon) values "
			+ "(#{bank_sname}, #{grid_num}, #{grid_title}, #{grid_entry}, #{grid_entry_type}, #{grid_icon})")
	public void insertGrid(@Param("bank_sname") String bank_sname, @Param("grid_num") int grid_num, @Param("grid_title") String grid_title, @Param("grid_entry") String grid_entry, 
			@Param("grid_entry_type") String grid_entry_type, @Param("grid_icon") String grid_icon);
	
	@Update("update t_grid_info set Fstate = 'UNUSABLE' where Fgrid_id = #{grid_id}")
	public void deleteGrid(@Param("grid_id") int grid_id);

	@Update("update t_grid_info set Fgrid_num = #{grid_num}, Fgrid_title = #{grid_title}, Fgrid_entry = #{grid_entry}, Fgrid_entry_type = #{grid_entry_type}, "
			+ "Fgrid_icon = #{grid_icon} where Fgrid_id = #{grid_id}")
	public void updateGrid(@Param("grid_id") int grid_id, @Param("grid_num") int grid_num, @Param("grid_title") String grid_title, @Param("grid_entry") String grid_entry, 
			@Param("grid_entry_type") String grid_entry_type, @Param("grid_icon")	String grid_icon);

	@Select("select max(Fgrid_num) from t_grid_info where Fbank_sname = #{bank_sname} and Fstate = 'USABLE'")
	public int getMaxNum(@Param("bank_sname") String bank_sname);
	
	@Select("select count(Fgrid_num) from t_grid_info where Fbank_sname = #{bank_sname} and Fstate = 'USABLE'")
	public int getGridCount(@Param("bank_sname") String bank_sname);

	@Select("select Fgrid_num from t_grid_info where Fgrid_id = #{grid_id}")
	public int getGridNum(@Param("grid_id") int grid_id);
	
	@Select("select Fbank_sname from t_grid_info where Fgrid_id = #{grid_id}")
	public String getBankSname(@Param("grid_id") int grid_id);

	@Update("update t_grid_info set Fgrid_num = Fgrid_num + 1 where Fbank_sname = #{bank_sname} and Fgrid_num >= #{num} and Fstate = 'USABLE'")
	public void backwardNum(@Param("num") int num, @Param("bank_sname") String bank_sname);
	
	@Update("update t_grid_info set Fgrid_num = Fgrid_num + 1 where Fbank_sname = #{bank_sname} and Fgrid_num < #{originNum} and Fgrid_num >= #{num} and Fstate = 'USABLE'")
	public void backwardNumUpdate(@Param("originNum") int originNum, @Param("num") int num, @Param("bank_sname") String bank_sname);
	
	@Update("update t_grid_info set Fgrid_num = Fgrid_num - 1 where Fbank_sname = #{bank_sname} and Fgrid_num > #{num} and Fstate = 'USABLE'")
	public void forwardNum(@Param("num") int num, @Param("bank_sname") String bank_sname);

	@Update("update t_grid_info set Fgrid_num = Fgrid_num - 1 where Fbank_sname = #{bank_sname} and Fgrid_num > #{originNum} and Fgrid_num <= #{num} and Fstate = 'USABLE'")
	public void forwardNumUpdate(@Param("originNum") int originNum, @Param("num") int num, @Param("bank_sname") String bank_sname);


}
