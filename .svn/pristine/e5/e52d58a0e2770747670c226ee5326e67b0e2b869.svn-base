package com.tenpay.wxwork.salary.dao;

import java.util.List;

import com.tenpay.wxwork.salary.dto.admin.*;
import com.tenpay.wxwork.salary.model.SalaryCount;
import com.tenpay.wxwork.salary.sqlprovider.SalaryDetailProvider;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SalaryDetailDAO {
    @Select("select Fmonth,Fcorpid,Fsequence_number,Fdepartment_name,Fuserid,Fuser_name,Fcard_number,Fcard_bank_number," +
            " Fbank_cards_relation,Foperator_userid,Fbasic_salary,Fperformance_salary,Fovertime_salary,Fphone_allowance,Ftransport_allowance," +
            " Fmeal_allowance,Fdeduction,Fendowment_insurance,Fmedical_insurance,Funemployment_insurance,Femployment_injury_insurance,Fhousing_fund," +
            " Fbefore_tax_salary_sum,Findividual_income_tax,Factual_salary"+
            " from t_salary_detail where Fmonth = #{month} and Fcorpid = #{corpid} and Fuserid = #{userid}")
    @Results(id="querySalaryDetailMap",value = {
            @Result(property = "fmonth",column ="Fmonth"),
            @Result(property = "fcorpid",column ="Fcorpid"),
            @Result(property = "fsequence_number",column ="Fsequence_number"),
            @Result(property = "fdepartment_name",column ="Fdepartment_name"),
            @Result(property = "fuserid",column ="Fuserid"),
            @Result(property = "fuser_name",column ="Fuser_name"),
            @Result(property = "fcard_number",column ="Fcard_number"),
            @Result(property = "fcard_bank_number",column ="Fcard_bank_number"),
            @Result(property = "fbank_cards_relation",column ="Fbank_cards_relation"),
            @Result(property = "foperator_userid",column ="Foperator_userid"),
            @Result(property = "fbasic_salary",column ="Fbasic_salary"),
            @Result(property = "fperformance_salary",column ="Fperformance_salary"),
            @Result(property = "fovertime_salary",column ="Fovertime_salary"),
            @Result(property = "fphone_allowance",column ="Fphone_allowance"),
            @Result(property = "ftransport_allowance",column ="Ftransport_allowance"),
            @Result(property = "fmeal_allowance",column ="Fmeal_allowance"),
            @Result(property = "fdeduction",column ="Fdeduction"),
            @Result(property = "fendowment_insurance",column ="Fendowment_insurance"),
            @Result(property = "fmedical_insurance",column ="Fmedical_insurance"),
            @Result(property = "funemployment_insurance",column ="Funemployment_insurance"),
            @Result(property = "femployment_injury_insurance",column ="Femployment_injury_insurance"),
            @Result(property = "fhousing_fund",column ="Fhousing_fund"),
            @Result(property = "fbefore_tax_salary_sum",column ="Fbefore_tax_salary_sum"),
            @Result(property = "findividual_income_tax",column ="Findividual_income_tax"),
            @Result(property = "factual_salary",column ="Factual_salary")
    })
    public UploadPayrollTransInt querySalaryDetail(@Param("month") String month, @Param("corpid") String corpid, @Param("userid") String userid);

    @Select("select Fmonth,Fbefore_tax_salary_sum,Factual_salary " +
            "from t_salary_detail where Fcorpid = #{corpid} and Fuserid = #{userid} order by Fmonth desc LIMIT 3")
    @Results(id="querySalaryCountMap",value = {
            @Result(property = "month",column ="Fmonth"),
            @Result(property = "total_pre_tax_wages",column ="Fbefore_tax_salary_sum"),
            @Result(property = "actual_amount",column ="Factual_salary")
    })
    public List<SalaryCount> querySalaryCount(@Param("corpid") String corpid, @Param("userid") String userid);

    @Insert("<script>" +
            "REPLACE INTO t_salary_detail(Fmonth,Fcorpid,Fsequence_number,Fdepartment_name,Fuserid,Fuser_name,Fcard_number,Fcard_bank_number," +
            "Fbank_cards_relation,Foperator_userid,Fbasic_salary,Fperformance_salary,Fovertime_salary,Fphone_allowance,Ftransport_allowance," +
            "Fmeal_allowance,Fdeduction,Fendowment_insurance,Fmedical_insurance,Funemployment_insurance,Femployment_injury_insurance,Fhousing_fund," +
            "Fbefore_tax_salary_sum,Findividual_income_tax,Factual_salary,Fcreate_time,Fmodify_time) " +
            "VALUES "+
            "<foreach collection='uplaodPayrollIntList' item='item' index='index' separator=','>"+
            "(#{item.fmonth},#{item.fcorpid},#{item.fsequence_number},#{item.fdepartment_name},#{item.fuserid},#{item.fuser_name},#{item.fcard_number},#{item.fcard_bank_number}," +
            "#{item.fbank_cards_relation},#{item.fuserid},#{item.fbasic_salary},#{item.fperformance_salary},#{item.fovertime_salary},#{item.fphone_allowance},#{item.ftransport_allowance}," +
            "#{item.fmeal_allowance},#{item.fdeduction},#{item.fendowment_insurance},#{item.fmedical_insurance},#{item.funemployment_insurance},#{item.femployment_injury_insurance},#{item.fhousing_fund}," +
            "#{item.fbefore_tax_salary_sum},#{item.findividual_income_tax},#{item.factual_salary},NOW(),NOW())"+
            "</foreach>"+
            "</script> ")
    public void insertUploadPayrollList(@Param(value = "uplaodPayrollIntList") List<UploadPayrollTransInt> uplaodPayrollIntList);

    @Select("SELECT IFNULL(MAX(Fsequence_number),0) as seqMaxNum FROM t_salary_detail WHERE Fcorpid = #{corpid} AND Fmonth=#{month}")
    public int queryMaxSequenceNumber(@Param(value = "corpid") String corpid, @Param(value = "month") String month);

    @Insert("<script>" +
            "REPLACE INTO t_salary_detail_no1(Fmonth,Fcorpid,Fsequence_number,Fdepartment_name,Fuserid,Fuser_name,Fcard_number,Fcard_bank_number," +
            "Fbank_cards_relation,Foperator_userid,Fbasic_salary,Fpost_salary,Fovertime_salary,Fhouse_salary,Fperformance_salary," +
            "Fperformance_grate,Fperformance_actual,fphone_allowance,Fother,Fshould_get_salary,Fhouse_rent,Fsocial_person_tax," +
            "Fshould_tax_money,Fperson_tax,Fsick_later,Fhousing_fund,Fother_deduction,Fshould_deduction_salary,Factual_salary," +
            "Fmark,Fcreate_time,Fmodify_time) " +
            "VALUES "+
            "<foreach collection='salaryDetailTranIntInfo' item='item' index='index' separator=','>"+
            "(#{item.fmonth},#{item.fcorpid},#{item.fsequence_number},#{item.fdepartment_name},#{item.fuserid},#{item.fuser_name},#{item.fcard_number},#{item.fcard_bank_number}," +
            "#{item.fbank_cards_relation},#{item.fuserid},#{item.fbasic_salary},#{item.fpost_salary},#{item.fovertime_salary},#{item.fhouse_salary},#{item.fperformance_salary}," +
            "#{item.fperformance_grate},#{item.fperformance_actual},#{item.fphone_allowance},#{item.fother},#{item.fshould_get_salary},#{item.fhouse_rent},#{item.fsocial_person_tax}," +
            "#{item.fshould_tax_money},#{item.fperson_tax},#{item.fsick_later},#{item.fhousing_fund},#{item.fother_deduction},#{item.fshould_deduction_salary},#{item.factual_salary}," +
            "#{item.fmark},NOW(),NOW())"+
            "</foreach>"+
            "</script> ")
    public void insertSalaryDetailList(@Param(value = "salaryDetailTranIntInfo") List<SalaryDetailTranIntInfo> salaryDetailTranIntInfo);

    @Select("SELECT IFNULL(MAX(Fsequence_number),0) as seqMaxNum FROM t_salary_detail_no1 WHERE Fcorpid = #{corpid} AND Fmonth=#{month}")
    public int querySalaryMaxSequenceNumber(@Param(value = "corpid") String corpid, @Param(value = "month") String month);


    //TODO UPDATE 以下为适应企业过渡版本
    @Select("select Fmonth," +
            "Fcorpid," +
            "Fsequence_number," +
            "Fdepartment_name," +
            "Fuserid," +
            "Fuser_name," +
            "Fcard_number," +
            "Fcard_bank_number," +
            "Fbank_cards_relation," +
            "Foperator_userid," +
            "Fbasic_salary," +
            "Fpost_salary," +
            "Fovertime_salary," +
            "Fhouse_salary," +
            "Fperformance_salary," +
            "Fperformance_grate," +
            "Fperformance_actual," +
            "Fphone_allowance," +
            "Fother," +
            "Fshould_get_salary," +
            "Fhouse_rent," +
            "Fsocial_person_tax," +
            "Fshould_tax_money," +
            "Fperson_tax," +
            "Fsick_later," +
            "Fhousing_fund," +
            "Fother_deduction," +
            "Fshould_deduction_salary," +
            "Factual_salary," +
            "Fmark," +
            "Fsure_state"+
            " from t_salary_detail_no1 where Fmonth = #{month} and Fcorpid = #{corpid} and Fuserid = #{userid}")
    @Results(id="querySalaryDetailInfoMap",value = {
            @Result(property = "fmonth",column ="Fmonth"),
            @Result(property = "fcorpid",column ="Fcorpid"),
            @Result(property = "fsequence_number",column ="Fsequence_number"),
            @Result(property = "fdepartment_name",column ="Fdepartment_name"),
            @Result(property = "fuserid",column ="Fuserid"),
            @Result(property = "fuser_name",column ="Fuser_name"),
            @Result(property = "fcard_number",column ="Fcard_number"),
            @Result(property = "fcard_bank_number",column ="Fcard_bank_number"),
            @Result(property = "fbank_cards_relation",column ="Fbank_cards_relation"),
            @Result(property = "foperator_userid",column ="Foperator_userid"),
            @Result(property = "fbasic_salary",column ="Fbasic_salary"),
            @Result(property = "fpost_salary",column ="Fpost_salary"),
            @Result(property = "fovertime_salary",column ="Fovertime_salary"),
            @Result(property = "fhouse_salary",column ="Fhouse_salary"),
            @Result(property = "fperformance_salary",column ="Fperformance_salary"),
            @Result(property = "fperformance_grate",column ="Fperformance_grate"),
            @Result(property = "fperformance_actual",column ="Fperformance_actual"),
            @Result(property = "fphone_allowance",column ="Fphone_allowance"),
            @Result(property = "fother",column ="Fother"),
            @Result(property = "fshould_get_salary",column ="Fshould_get_salary"),
            @Result(property = "fhouse_rent",column ="Fhouse_rent"),
            @Result(property = "fsocial_person_tax",column ="Fsocial_person_tax"),
            @Result(property = "fshould_tax_money",column ="Fshould_tax_money"),
            @Result(property = "fperson_tax",column ="Fperson_tax"),
            @Result(property = "fsick_later",column ="Fsick_later"),
            @Result(property = "fhousing_fund",column ="Fhousing_fund"),
            @Result(property = "fother_deduction",column ="Fother_deduction"),
            @Result(property = "fshould_deduction_salary",column ="Fshould_deduction_salary"),
            @Result(property = "factual_salary",column ="Factual_salary"),
            @Result(property = "fmark",column ="Fmark"),
            @Result(property = "fsure_state",column ="Fsure_state")
    })
    public SalaryDetailTranIntInfo querySalaryDetailInfo(@Param("month") String month, @Param("corpid") String corpid, @Param("userid") String userid);

    //TODO UPDATE 以下为适应企业过渡版本
    @Update("update t_salary_detail_no1 set Fsure_state = #{sure_state} where Fmonth = #{month} and Fcorpid = #{corpid} and Fuserid = #{userid}")
    public void updateSalaryDetailState(@Param("sure_state") String sure_state, @Param("month") String month, @Param("corpid") String corpid, @Param("userid") String userid);

    //TODO UPDATE 以下为适应企业过渡版本
    @Select("select Fmonth,Fshould_get_salary,Factual_salary " +
            "from t_salary_detail_no1 where Fcorpid = #{corpid} and Fuserid = #{userid} order by Fmonth desc LIMIT 3")
    @Results(id="querySalaryDetailCountMap",value = {
            @Result(property = "month",column ="Fmonth"),
            @Result(property = "total_pre_tax_wages",column ="Fshould_get_salary"),
            @Result(property = "actual_amount",column ="Factual_salary")
    })
    public List<SalaryCount> querySalaryDetailCount(@Param("corpid") String corpid, @Param("userid") String userid);

    //TODO UPDATE 以下为适应企业过渡版本
    @Select("select Fsequence_number, Fuserid, Fuser_name, Fsure_state " +
            "from t_salary_detail_no1 where Fcorpid = #{corpid} and Fmonth = #{month} order by Fsequence_number")
    @Results(id="querySalaryConfirm",value = {
            @Result(property = "sequence_number",column ="Fsequence_number"),
            @Result(property = "userid",column ="Fuserid"),
            @Result(property = "username",column ="Fuser_name"),
            @Result(property = "sure_state",column ="Fsure_state")
    })
    public List<SalaryConfirm> querySalaryConfirm(@Param("corpid") String corpid, @Param("month") String month);

    @Select(" SELECT Fsequence_number, Fuser_name, Fuserid, Fsure_state " +
            "FROM t_salary_detail_no1 where Fcorpid = #{fcorpid} and Fmonth = #{fmonth} order by Fsequence_number")
    @Results(id="querySalaryDetailBackInfo",value = {
            @Result(property = "fuser_name",column ="Fuser_name"),
            @Result(property = "fuserid",column ="Fuserid"),
            @Result(property = "fsequence_number",column ="Fsequence_number"),
            @Result(property = "fsure_state",column ="Fsure_state")
    })
    public List<SalaryDetailBackInfo> querySalaryDetailBackInfo(SalaryDetailBackInfo salaryDetailBackInfo);

   @Insert("<script>" +
            "INSERT INTO wxwork_db_${tableNum}.t_salary_detail_${month} (Fsalary_overview_id,Fcategory,Fdetail_name,Fdetail_amount,Fcreate_time,Fmodify_time) " +
            "VALUES "+
            "<foreach collection='definedSalaryDetailInfoList' item='item' index='index' separator=','>"+
            "(#{item.salary_overview_id},#{item.category},#{item.detail_name},#{item.detail_amount},NOW(),NOW())"+
            "</foreach>"+
            "</script> ")
    public void insertDefinedSalaryDetailList(@Param(value = "definedSalaryDetailInfoList") List<DefinedSalaryDetailInfo> definedSalaryDetailInfoList,
                                              @Param("month") String month,
                                              @Param("tableNum") String tableNum);

    @Select(" SELECT Fsalary_overview_id FROM wxwork_db_${tableNum}.t_salary_detail_${month} where Fsalary_overview_id = #{salary_overview_id}")
    @Results(id="querySalaryDetailList",value = {
            @Result(property = "salary_overview_id",column ="Fsalary_overview_id")
    })
    public List<DefinedSalaryDetailInfo> querySalaryDetailList(@Param("salary_overview_id") int salary_overview_id,
                                                               @Param("month") String month,
                                                               @Param("tableNum") String tableNum);


    @Update("UPDATE wxwork_db_${tableNum}.t_salary_detail_${month} SET " +
            "Fsalary_overview_id = 0, " +
            "Fmodify_time = NOW() " +
            "WHERE Fsalary_overview_id = #{salary_overview_id} ")
    public void updateSalaryDetailList(@Param("salary_overview_id") int salary_overview_id,
                                       @Param("month") String month,
                                       @Param("tableNum") String tableNum);


    //查询工资明细总览
    @Select(" SELECT " +
            "t1.Fis_read," +
            "t1.Fsalary_overview_id " +
            "FROM " +
            "t_salary_overview_${month} t1 " +
            "where t1.Fmonth = #{month} and t1.Fuserid = #{userid} and t1.Fcorpid = #{corpid} and t1.Fbatch_no = #{batch_no} limit 1")
    @Results(id="queryUserSalary",value = {
            @Result(property = "salary_overview_id",column ="Fsalary_overview_id"),
            @Result(property = "batch_no",column ="Fbatch_no"),
            @Result(property = "is_read",column ="Fis_read")
    })
    public UserSalaryDetail queryUserSalary(@Param("month") String month, @Param("corpid") String corpid, @Param("userid") String userid,@Param("batch_no") int batch_no);


    //查询工资明细
    @Select(" SELECT " +
            "t2.Fsalary_detail_id," +
            "t2.Fcategory," +
            "t2.Fdetail_name," +
            "t2.Fdetail_amount " +
            "FROM  wxwork_db_${tablename}.t_salary_detail_${month} t2 " +
            "where t2.Fsalary_overview_id = #{salary_overview_id} "+
            "ORDER BY t2.Fsalary_detail_id ")
    @Results(id="queryUserSalaryDetail",value = {
            @Result(property = "salary_detail_id",column ="Fsalary_detail_id"),
            @Result(property = "is_read",column ="Fis_read"),
            @Result(property = "category",column ="Fcategory"),
            @Result(property = "detail_name",column ="Fdetail_name"),
            @Result(property = "detail_amount",column ="Fdetail_amount")
    })
    public List<UserSalaryDetail> queryUserSalaryDetail(@Param("month") String month, @Param("tablename") String tablename, @Param("salary_overview_id") String salary_overview_id);

    //适配版 查询近三个月工资
    @SelectProvider(type = SalaryDetailProvider.class, method = "queryUserSalaryCount")
    @Results(id="queryUserSalaryCount",value = {
            @Result(property = "month",column ="Fmonth"),
            @Result(property = "total_pre_tax_wages",column ="Fsalary_sum"),
            @Result(property = "actual_amount",column ="Factual_salary"),
            @Result(property = "batch_no",column ="Fbatch_no")
    })
    public List<SalaryCount> queryUserSalaryCount(@Param("corpid") String corpid, @Param("userid") String userid, @Param("monthone") String monthone, @Param("monthtwo") String monthtwo, @Param("monththree") String monththree);

    //适配版 查询工资确认状态
    @Select("select Fsequence_number, Fuserid, Fuser_name, Fis_read, Fbatch_no " +
            "from t_salary_overview_${month} where Fcorpid = #{corpid} and Fmonth = #{month} order by Fsequence_number")
    @Results(id="queryUserSalaryConfirm",value = {
            @Result(property = "sequence_number",column ="Fsequence_number"),
            @Result(property = "userid",column ="Fuserid"),
            @Result(property = "username",column ="Fuser_name"),
            @Result(property = "sure_state",column ="Fis_read"),
            @Result(property = "batch_no",column ="Fbatch_no")
    })
    public List<SalaryConfirm> queryUserSalaryConfirm(@Param("corpid") String corpid, @Param("month") String month);

    //T适配版 工资确认
    @Update("update t_salary_overview_${month} set Fis_read = #{sure_state} where Fmonth = #{month} and Fcorpid = #{corpid} and Fuserid = #{userid} and Fbatch_no = #{batch_no}")
    public void updateUserSalaryDetailState(@Param("sure_state") String sure_state, @Param("month") String month,@Param("batch_no") int batch_no, @Param("corpid") String corpid, @Param("userid") String userid);

    //查询数据库中是否存在表
    @Select("SELECT count(1) FROM information_schema.TABLES WHERE table_name = #{tablename}")
    public int queryExistTable(@Param("tablename") String tablename);
}
