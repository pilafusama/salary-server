package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.dto.admin.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SalaryOverViewDAO {

    @Insert("INSERT INTO t_salary_overview_${month}" +
            "(Fmonth, Fcorpid, Fsequence_number, Fdepartment_name, Fuserid, Fuser_name, Fcard_number, Fcard_bank_number," +
            " Fbank_cards_relation, Foperator_userid, Factual_salary, Fsalary_sum, Fdeduction_sum, Fremark, Fbatch_no, " +
            " Fcreate_time, Fmodify_time) VALUES " +
            "(#{month}, #{corpid}, #{sequence_number}, #{department_name}, #{userid}, #{user_name}, #{card_number}, #{card_bank_number}," +
            " #{bank_cards_relation}, #{operator_userid}, #{actual_salary}, #{salary_sum}, #{deduction_sum}, #{remark}, #{batch_no}, "  +
            " NOW(), NOW())")
    @Options(useGeneratedKeys=true, keyProperty="salary_overview_id", keyColumn="Fsalary_overview_id")
    public void insertSalaryOverView(SalaryOverViewInfo salaryOverViewInfo);

    @Select(" SELECT Fsalary_overview_id, Fmonth, Fcorpid, Fsequence_number, Fuserid,Fbatch_no FROM t_salary_overview_${month} " +
            " WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} AND Fmonth = #{month} AND Fbatch_no = #{batch_no} ")
    @Results(id="querySalaryOverView",value = {
            @Result(property = "salary_overview_id",column ="Fsalary_overview_id"),
            @Result(property = "month",column ="Fmonth"),
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "sequence_number",column ="Fsequence_number"),
            @Result(property = "batch_no",column ="Fbatch_no"),
            @Result(property = "userid",column ="Fuserid")
    })
    public SalaryOverViewInfo querySalaryOverView(SalaryOverViewInfo salaryOverViewInfo);

    @Update("UPDATE t_salary_overview_${month} SET " +
            "Factual_salary = #{actual_salary}, " +
            "Fsalary_sum = #{salary_sum}, " +
            "Fdeduction_sum = #{deduction_sum}, " +
            "Fremark = #{remark}, " +
            "Foperator_userid = #{operator_userid}, " +
            "Fis_read = #{is_read}, " +
            "Fmodify_time = NOW() " +
            "WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} AND Fmonth = #{month} AND Fbatch_no = #{batch_no} LIMIT 2")
    public void updateSalaryOverView(SalaryOverViewInfo salaryOverViewInfo);

    @Select("SELECT IFNULL(MAX(Fsequence_number),0) as seqMaxNum FROM t_salary_overview_${month} WHERE Fcorpid = #{corpid} AND Fmonth=#{month}")
    public int queryMaxSequenceNumberForSalaryOverView(@Param(value = "corpid") String corpid,@Param(value = "month") String month);

    @Select(" SELECT COUNT(*) AS counter ,IFNULL(SUM(Factual_salary),0) AS salaryAll,IFNULL(SUM(Fsalary_sum),0) AS salarySum " +
            " FROM t_salary_overview_${month} WHERE Fmonth = #{month} AND Fcorpid = #{corpid} AND Fbatch_no = #{batch_no} ")
    public ViewSalaryAllDetail viewSalaryAllDetail(@Param(value = "corpid") String corpid, @Param(value = "month") String month,@Param(value = "batch_no") int batch_no);

    @Select(" SELECT Fcorpid,Fuserid FROM t_salary_overview_${month} WHERE Fmonth = #{month} AND Fcorpid = #{corpid} AND Fbatch_no = #{batch_no} ")
    @Results(id="querySalaryOverviewForMonthAndCorp",value = {
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "userid",column ="Fuserid")
    })
    public List<SalaryOverViewInfo> querySalaryOverviewForMonthAndCorp(@Param(value = "corpid") String corpid, @Param(value = "month") String month,@Param(value = "batch_no") int batch_no);

    @Select(" SELECT Fsequence_number id, #{applyName} as applyName, NOW() AS applyTime, Fuser_name receiveName, " +
            " Fcard_number receiveAccount, Factual_salary totalFee, IF(Fbank_cards_relation='DIFF_BANK', 1, 0) AS crossFlag " +
            " FROM t_salary_overview_${month} WHERE Fmonth = #{month} AND Fcorpid = #{corpid} AND Fbatch_no = #{batch_no} AND " +
            " Fsequence_number between #{start} and #{end} order by Fsequence_number ASC ")
    public List<SalaryFlowDetail> querySalaryForApprove(@Param(value = "corpid") String corpid
            , @Param(value = "month") String month, @Param(value = "batch_no") int batch_no
            , @Param(value = "applyName") String applyName, @Param(value = "start") int start, @Param(value = "end") int end);

    @Select("SELECT IFNULL(MIN(Fsequence_number),0) as seqMinNum, IFNULL(MAX(Fsequence_number),0) as seqMaxNum" +
            " FROM t_salary_overview_${month} WHERE Fcorpid = #{corpid} AND Fmonth=#{month} AND Fbatch_no = #{batch_no}")
    public SalarySequences querySequenceNumberForSalaryApprove(@Param(value = "corpid") String corpid, @Param(value = "month") String month, @Param(value = "batch_no") int batch_no);

    @Select(" SELECT COUNT(*) AS counter ,IFNULL(SUM(Factual_salary),0) AS salaryAll FROM t_salary_overview_${month} WHERE " +
            " Fmonth = #{month} AND Fcorpid = #{corpid} AND Fbatch_no = #{batch_no} AND Fsequence_number between ${start} and ${end} " +
            " order by Fsequence_number ASC")
    public ViewSalaryAllDetail forApproveSalarySum(@Param(value = "corpid") String corpid, @Param(value = "month") String month
            ,@Param(value = "batch_no") int batch_no, @Param(value = "start") int start, @Param(value = "end") int end);

    @Select(" SELECT  Fuserid,Fuser_name, Fcard_number, Factual_salary, Fbank_cards_relation, ' ' f_bankname, Fcard_bank_number, '代发工资' f_mark, '代发工资' f_remark " +
            "FROM t_salary_overview_${month} where Fcorpid = #{f_corpId} AND Fmonth = #{month} AND Fbatch_no = #{batch_no}")
    @Results(id="querySalaryOverViewByCorp",value = {
            @Result(property = "f_userid",column ="Fuserid"),
            @Result(property = "fcre_name",column ="Fuser_name"),
            @Result(property = "fsalary_card_number",column ="Fcard_number"),
            @Result(property = "f_momeny",column ="Factual_salary"),
            @Result(property = "f_bankmark",column ="Fbank_cards_relation"),
            @Result(property = "f_bankno",column ="Fcard_bank_number")
    })
    public List<DownLoadSalaryReq> querySalaryOverViewByCorp(DownLoadSalaryReq downLoadSalaryReq);


    @Select(" SELECT IFNULL(MAX(Fbatch_no),0) batch_no FROM t_salary_overview_${month} WHERE Fmonth = #{month} AND Fcorpid = #{corpId} ")
    public int queryCorpMaxBatchno(@Param(value = "corpId") String corpId, @Param(value = "month") String month);

    @Select(" SELECT Fbatch_no,CONCAT(LEFT(Fmonth,4),'年',SUBSTRING(Fmonth,5),'月') Fmonth,DATE_FORMAT(MAX(Fcreate_time),'%Y年%m月%d日') Fcreate_time,COUNT(*) AS counter ,IFNULL(SUM(Factual_salary),0) AS salaryAll " +
            " FROM t_salary_overview_${month} WHERE Fmonth = #{month} AND Fcorpid = #{corpid} AND Fbatch_no != '0' GROUP BY Fbatch_no, Fmonth ")
    @Results(id="viewSalaryAllDetailForList",value = {
            @Result(property = "batch_no",column ="Fbatch_no"),
            @Result(property = "month",column ="Fmonth"),
            @Result(property = "create_time",column ="Fcreate_time")
    })
    public List<ViewSalaryAllDetail> viewSalaryAllDetailForList(@Param(value = "corpid") String corpid, @Param(value = "month") String month);

    @Update("UPDATE t_salary_overview_${month} SET " +
            "Fbatch_no = 0, " +
            "Fmodify_time = NOW() " +
            "WHERE Fcorpid = #{corpId} AND Fmonth = #{month} AND Fbatch_no = #{batch_no} ")
    public int updateSalaryOverViewForBatchno(@Param("corpId") String corpId,
                                       @Param("month") String month,
                                       @Param("batch_no") String batch_no);
}
