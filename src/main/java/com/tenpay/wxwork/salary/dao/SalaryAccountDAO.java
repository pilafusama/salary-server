package com.tenpay.wxwork.salary.dao;

import com.tenpay.wxwork.salary.dto.admin.*;
import com.tenpay.wxwork.salary.dto.h5.GestCheckReqParam;
import com.tenpay.wxwork.salary.dto.h5.OpenSalaryAccountReqParam;
import com.tenpay.wxwork.salary.model.SalaryAccount;


import com.tenpay.wxwork.salary.model.SalaryFields;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.tenpay.wxwork.salary.sqlprovider.SalaryAccountProvider;

import java.util.List;

@Mapper
public interface SalaryAccountDAO {


    @Select("select Fsalary_account_id, Fcorpid, Fuserid, Fstate, Fopening_type, " +
            "Fsalary_card_number, Fsalary_card_bank_sname, Fsalary_card_opened_time, Fgesture_password, Fmobile_number, " +
            "Fcre_type, Fcre_id, Fcre_name, Fcre_gender, Fcre_nationality, " +
            "Fcre_birthdate, Fcre_address, Fcre_issue_authority, Fcre_valid_date, Fcre_expire_date, " +
            "Fcre_front_photo, Fcre_back_photo, Fliveness_detection_number, Fliveness_detection_video, Fface_recognition_photo, " +
            "Fbind_card_number, Fbind_card_bank_sname, Fbind_card_bank_number, Fbind_card_photo, Fcreate_time, " +
            "Fmodify_time " +
            "FROM t_salary_account WHERE Fsalary_account_id = #{salary_account_id}")
    @Results(id="SalaryAccountMap",value = {
            @Result(property = "salary_account_id",column ="Fsalary_account_id"),
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "userid",column ="Fuserid"),
            @Result(property = "state",column ="Fstate"),
            @Result(property = "opening_type",column ="Fopening_type"),
            @Result(property = "salary_card_number",column ="Fsalary_card_number"),
            @Result(property = "salary_card_bank_sname",column ="Fsalary_card_bank_sname"),
            @Result(property = "salary_card_opened_time",column ="Fsalary_card_opened_time"),
            @Result(property = "gesture_password",column ="Fgesture_password"),
            @Result(property = "mobile_number",column ="Fmobile_number"),
            @Result(property = "cre_type",column ="Fcre_type"),
            @Result(property = "cre_id",column ="Fcre_id"),
            @Result(property = "cre_name",column ="Fcre_name"),
            @Result(property = "cre_gender",column ="Fcre_gender"),
            @Result(property = "cre_nationality",column ="Fcre_nationality"),
            @Result(property = "cre_birthdate",column ="Fcre_birthdate"),
            @Result(property = "cre_address",column ="Fcre_address"),
            @Result(property = "cre_issue_authority",column ="Fcre_issue_authority"),
            @Result(property = "cre_valid_date",column ="Fcre_valid_date"),
            @Result(property = "cre_expire_date",column ="Fcre_expire_date"),
            @Result(property = "cre_front_photo",column ="Fcre_front_photo"),
            @Result(property = "cre_back_photo",column ="Fcre_back_photo"),
            @Result(property = "liveness_detection_number",column ="Fliveness_detection_number"),
            @Result(property = "liveness_detection_video",column ="Fliveness_detection_video"),
            @Result(property = "face_recognition_photo",column ="Fface_recognition_photo"),
            @Result(property = "bind_card_number",column ="Fbind_card_number"),
            @Result(property = "bind_card_bank_sname",column ="Fbind_card_bank_sname"),
            @Result(property = "bind_card_bank_number",column ="Fbind_card_bank_number"),
            @Result(property = "bind_card_photo",column ="Fbind_card_photo"),
            @Result(property = "create_time",column ="Fcreate_time"),
            @Result(property = "modify_time",column ="Fmodify_time")
    })
    public SalaryAccount querySalaryAccount(@Param("salary_account_id") String salary_account_id);



    @Insert("INSERT INTO t_salary_account" +
            "(Fcorpid, Fuserid, Fstate, Fsalary_card_bank_type, Fsalary_card_bank_sname," +
            " Fcreate_time, Fmodify_time) VALUES " +
            "(#{corpid}, #{userid}, #{state}, #{salaryCardBankType}, #{salaryCardBankSname}, NOW(), NOW())")
    public void insertNewAccount(@Param("corpid") String corpid,
                                 @Param("userid") String userid,
                                 @Param("state") int state,
                                 @Param("salaryCardBankType") int salaryCardBankType,
                                 @Param("salaryCardBankSname") String salaryCardBankSname);


    @Update("UPDATE t_salary_account SET " +
            "Fstate = #{state}, " +
            "Fcre_type = #{cre_type}, " +
            "Fcre_id = #{cre_id}, " +
            "Fcre_name = #{cre_name}, " +
            "Fcre_gender = #{cre_gender}, " +
            "Fcre_nationality = #{cre_nationality}, " +
            "Fcre_birthdate = #{cre_birthdate}, " +
            "Fcre_address = #{cre_address}, " +
            "Fcre_issue_authority = #{cre_issue_authority}, " +
            "Fcre_valid_date = #{cre_valid_date}, " +
            "Fcre_expire_date = #{cre_expire_date}, " +
            "Fcre_front_photo = #{cre_front_photo}, " +
            "Fcre_back_photo = #{cre_back_photo}," +
            "Fmodify_time = NOW() " +
            "WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} AND Fstate = #{from_state} LIMIT 2")
    public void updateCreInfo(@Param("corpid") String corpid,
                              @Param("userid") String userid,
                              @Param("from_state") int from_state,
                              @Param("state") int state,
                              @Param("cre_type") String cre_type,
                              @Param("cre_id") String cre_id,
                              @Param("cre_name") String cre_name,
                              @Param("cre_gender") String cre_gender,
                              @Param("cre_nationality") String cre_nationality,
                              @Param("cre_birthdate") String cre_birthdate,
                              @Param("cre_address") String cre_address,
                              @Param("cre_issue_authority") String cre_issue_authority,
                              @Param("cre_valid_date") String cre_valid_date,
                              @Param("cre_expire_date") String cre_expire_date,
                              @Param("cre_front_photo") String cre_front_photo,
                              @Param("cre_back_photo") String cre_back_photo);

    @Update("UPDATE t_salary_account SET " +
            "Fstate = #{state}, " +
            "Fliveness_detection_number = #{liveness_detection_number}, " +
            "Fliveness_detection_video = #{liveness_detection_video}, " +
            "Fface_recognition_photo = #{face_recognition_photo}, " +
            "Fmodify_time = NOW() " +
            "WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} AND Fstate = #{from_state} LIMIT 2")
    public void updateLivenessDetection(@Param("corpid") String corpid,
                                        @Param("userid") String userid,
                                        @Param("from_state") int from_state,
                                        @Param("state") int state,
                                        @Param("liveness_detection_number") String liveness_detection_number,
                                        @Param("liveness_detection_video") String liveness_detection_video,
                                        @Param("face_recognition_photo") String face_recognition_photo);

    @UpdateProvider(type = SalaryAccountProvider.class, method = "deleteUser")
    public void deleteUser(@Param("corpid") String corpid,
                           @Param("userid") String userid);

    //查询用户信息，去掉图片
    @Select("select Fsalary_account_id, Fcorpid, Fuserid, Fstate, Fopening_type, " +
            "Fsalary_card_number,Fsalary_card_bank_type, Fsalary_card_bank_sname, Fsalary_card_opened_time, Fgesture_password, Fmobile_number, " +
            "Fcre_type, Fcre_id, Fcre_name, Fcre_gender, Fcre_nationality, " +
            "DATE_FORMAT(Fcre_birthdate,'%Y%m%d') Fcre_birthdate, Fcre_address, Fcre_issue_authority, " +
            "DATE_FORMAT(Fcre_valid_date,'%Y%m%d') Fcre_valid_date, DATE_FORMAT(Fcre_expire_date,'%Y%m%d') Fcre_expire_date, " +
            "Fbind_card_number, Fbind_card_bank_sname, Fbind_card_bank_number, Fcard_banks_relation " +
            "FROM t_salary_account  WHERE Fcorpid = #{corpid} AND Fuserid = #{userid}")
    @Results(id="QueryByUserId",value = {
            @Result(property = "salary_account_id",column ="Fsalary_account_id"),
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "userid",column ="Fuserid"),
            @Result(property = "state",column ="Fstate"),
            @Result(property = "opening_type",column ="Fopening_type"),
            @Result(property = "salary_card_number",column ="Fsalary_card_number"),
            @Result(property = "salary_card_bank_type",column ="Fsalary_card_bank_type"),
            @Result(property = "salary_card_bank_sname",column ="Fsalary_card_bank_sname"),
            @Result(property = "salary_card_opened_time",column ="Fsalary_card_opened_time"),
            @Result(property = "gesture_password",column ="Fgesture_password"),
            @Result(property = "mobile_number",column ="Fmobile_number"),
            @Result(property = "cre_type",column ="Fcre_type"),
            @Result(property = "cre_id",column ="Fcre_id"),
            @Result(property = "cre_name",column ="Fcre_name"),
            @Result(property = "cre_gender",column ="Fcre_gender"),
            @Result(property = "cre_nationality",column ="Fcre_nationality"),
            @Result(property = "cre_birthdate",column ="Fcre_birthdate"),
            @Result(property = "cre_address",column ="Fcre_address"),
            @Result(property = "cre_issue_authority",column ="Fcre_issue_authority"),
            @Result(property = "cre_valid_date",column ="Fcre_valid_date"),
            @Result(property = "cre_expire_date",column ="Fcre_expire_date"),
            @Result(property = "bind_card_number",column ="Fbind_card_number"),
            @Result(property = "bind_card_bank_sname",column ="Fbind_card_bank_sname"),
            @Result(property = "bind_card_bank_number",column ="Fbind_card_bank_number"),
            @Result(property = "card_banks_relation",column ="Fcard_banks_relation")
    })
    public SalaryAccount queryByUserId(@Param("corpid") String corpid, @Param("userid") String userid);

    @Select(" select Fcorpid, Fuserid, Fbind_card_number, Fbind_card_bank_sname, Fbind_card_bank_number " +
            " FROM t_salary_account  WHERE Fcorpid = #{corpid} AND Fuserid = #{userid}")
    @Results(id="queryByUserIdAndCorpid",value = {
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "userid",column ="Fuserid"),
            @Result(property = "bind_card_number",column ="Fbind_card_number"),
            @Result(property = "bind_card_bank_sname",column ="Fbind_card_bank_sname"),
            @Result(property = "bind_card_bank_number",column ="Fbind_card_bank_number")
    })
    public SalaryAccount queryByUserIdAndCorpid(@Param("corpid") String corpid, @Param("userid") String userid);

    @Select("select Fsalary_account_id, Fcorpid, Fuserid, Fstate, Fopening_type, " +
            "Fsalary_card_number,Fsalary_card_bank_type, Fsalary_card_bank_sname, Fsalary_card_opened_time, Fgesture_password, Fmobile_number, " +
            "Fcre_type, Fcre_id, Fcre_name, Fcre_gender, Fcre_nationality, " +
            "DATE_FORMAT(Fcre_birthdate,'%Y%m%d') Fcre_birthdate, Fcre_address, Fcre_issue_authority, " +
            "DATE_FORMAT(Fcre_valid_date,'%Y%m%d') Fcre_valid_date, DATE_FORMAT(Fcre_expire_date,'%Y%m%d') Fcre_expire_date, " +
            "Fcre_front_photo, Fcre_back_photo, Fliveness_detection_number, Fliveness_detection_video, Fface_recognition_photo, " +
            "Fbind_card_number, Fbind_card_bank_sname, Fbind_card_bank_number, Fbind_card_photo, Fcreate_time, " +
            "Fmodify_time,Fcard_banks_relation " +
            "FROM t_salary_account  WHERE Fcorpid = #{corpid} AND Fuserid = #{userid}")
    @Results(id="SalaryAccountMapByID",value = {
            @Result(property = "salary_account_id",column ="Fsalary_account_id"),
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "userid",column ="Fuserid"),
            @Result(property = "state",column ="Fstate"),
            @Result(property = "opening_type",column ="Fopening_type"),
            @Result(property = "salary_card_number",column ="Fsalary_card_number"),
            @Result(property = "salary_card_bank_type",column ="Fsalary_card_bank_type"),
            @Result(property = "salary_card_bank_sname",column ="Fsalary_card_bank_sname"),
            @Result(property = "salary_card_opened_time",column ="Fsalary_card_opened_time"),
            @Result(property = "gesture_password",column ="Fgesture_password"),
            @Result(property = "mobile_number",column ="Fmobile_number"),
            @Result(property = "cre_type",column ="Fcre_type"),
            @Result(property = "cre_id",column ="Fcre_id"),
            @Result(property = "cre_name",column ="Fcre_name"),
            @Result(property = "cre_gender",column ="Fcre_gender"),
            @Result(property = "cre_nationality",column ="Fcre_nationality"),
            @Result(property = "cre_birthdate",column ="Fcre_birthdate"),
            @Result(property = "cre_address",column ="Fcre_address"),
            @Result(property = "cre_issue_authority",column ="Fcre_issue_authority"),
            @Result(property = "cre_valid_date",column ="Fcre_valid_date"),
            @Result(property = "cre_expire_date",column ="Fcre_expire_date"),
            @Result(property = "cre_front_photo",column ="Fcre_front_photo"),
            @Result(property = "cre_back_photo",column ="Fcre_back_photo"),
            @Result(property = "liveness_detection_number",column ="Fliveness_detection_number"),
            @Result(property = "liveness_detection_video",column ="Fliveness_detection_video"),
            @Result(property = "face_recognition_photo",column ="Fface_recognition_photo"),
            @Result(property = "bind_card_number",column ="Fbind_card_number"),
            @Result(property = "bind_card_bank_sname",column ="Fbind_card_bank_sname"),
            @Result(property = "bind_card_bank_number",column ="Fbind_card_bank_number"),
            @Result(property = "bind_card_photo",column ="Fbind_card_photo"),
            @Result(property = "create_time",column ="Fcreate_time"),
            @Result(property = "modify_time",column ="Fmodify_time"),
            @Result(property = "card_banks_relation",column ="Fcard_banks_relation")
    })
    public SalaryAccount querySalaryAccountByUserId(@Param("corpid") String corpid, @Param("userid") String userid);



    @Update("UPDATE t_salary_account SET " +
            "Fbind_card_photo = #{cardPhoto}, " +
            "Fstate = #{state}," +
            "Fmodify_time = NOW() " +
            "WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} LIMIT 1")
    public void updateBankCardPhoto(@Param("corpid") String corpid,
                                    @Param("userid") String userid,
                                    @Param("state") int state,
                                    @Param("cardPhoto") String cardPhoto);
    /**
     * 更新用户开户状态
     *
     * @param fstate
     * @param fcorpid
     * @param fuserid
     * @param fromState
     */
    @Update("UPDATE t_salary_account SET " +
            "Fstate = #{fstate}, " +
            "Fmodify_time = NOW() " +
            "WHERE Fcorpid = #{fcorpid} " +
            "and Fuserid = #{fuserid} " +
            "and Fstate = #{fromState} " +
            "LIMIT 1")
    public void updateFstate(@Param("fstate") int fstate,
                             @Param("fcorpid") String fcorpid,
                             @Param("fuserid") String fuserid,
                             @Param("fromState") int fromState);

    @Select("select Fsalary_card_number as salary_card_number, Fsalary_card_bank_type as salary_card_bank_type, " +
            " Fsalary_card_bank_sname as salary_card_bank_sname, " +
            " Fcre_name as cre_name, Fbind_card_number as bind_card_number, Fbind_card_bank_sname as " +
            " bind_card_bank_sname, Fcreate_time as create_time from t_salary_account  where Fcorpid = #{fcorpid} and Fuserid = #{fuserid}")
    public SalaryAccount getFsalaryCardInfo(@Param("fcorpid") String fcorpid, @Param("fuserid") String fuserid);


    @Select("SELECT Fcre_type,Fcre_id,Fcre_name,Fcre_gender,Fcre_address, Fcre_valid_date,Fcre_expire_date,Fuserid, Fcorpid ,Fsalary_card_bank_type" +
            " FROM t_salary_account where Fuserid = #{userId} and Fcorpid = #{corpId}")
    @Results(id="OpenSalaryAccountMap",value = {
            @Result(property = "crdt_tpcd",column ="Fcre_type"),
            @Result(property = "crdt_no",column ="Fcre_id"),
            @Result(property = "cst_nm",column ="Fcre_name"),
            @Result(property = "BankType",column ="Fsalary_card_bank_type"),//工资卡发卡行 bank_type
            @Result(property = "gnd_cd",column ="Fcre_gender"),//性别代码
            @Result(property = "ctc_adr",column ="Fcre_address"),//联系地址
            @Result(property = "crdt_efdt",column ="Fcre_valid_date"),
            @Result(property = "crdt_exdat",column ="Fcre_expire_date"),
            @Result(property = "mbsh_no",column ="Fuserid"),//第三方APP的用户ID
            @Result(property = "corpid",column ="Fcorpid"),//企业ID
    })
    public OpenSalaryAccountReqParam queryOpenSalaryAccountRes(@Param("userId") String userId, @Param("corpId") String corpId);

    @Update("UPDATE t_salary_account SET Fstate = #{fstate}, Fopening_type = #{fopening_type}, Fsalary_card_number = #{dbcrd_cardno}, Fsalary_card_opened_time = #{dtbs_tms}, " +
            "Fcre_name = #{cst_nm}, Fbind_card_number = #{rltv_accno}, Fbind_card_bank_number = #{dpbkbkcd},Fmobile_number = #{mobileNumber}, Fmodify_time = NOW(), Fcard_banks_relation = #{Card_banks_relation},Fbind_card_bank_sname = #{bind_card_bank_sname} " +
            "WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} LIMIT 1")
    public void updateOpenSalaryAccountRes(@Param("userid") String userId,
                                           @Param("corpid") String corpId,
                                           @Param("mobileNumber") String mobileNumber,
                                           @Param("fstate") int fstate,
                                           @Param("fopening_type") String fopening_type,
                                           @Param("dbcrd_cardno") String dbcrd_cardno,
                                           @Param("dtbs_tms") String dtbs_tms,
                                           @Param("cst_nm") String cst_nm,
                                           @Param("rltv_accno") String rltv_accno,
                                           @Param("dpbkbkcd") String dpbkbkcd,
                                           @Param("Card_banks_relation") String Card_banks_relation,
                                           @Param("bind_card_bank_sname") String bind_card_bank_sname
    );

    @Select(" SELECT (@i:=@i+1) as f_no, Fcre_name, Fsalary_card_number, '0.00' f_momeny, Fcard_banks_relation, ' ' f_bankname, Fbind_card_bank_number, '代发工资' f_mark, '代发工资' f_remark " +
            "FROM t_salary_account,(select @i:=0) as it where Fcorpid = #{f_corpId}")
    @Results(id="querySalary",value = {
            @Result(property = "fcre_name",column ="Fcre_name"),
            @Result(property = "fsalary_card_number",column ="Fsalary_card_number"),
            @Result(property = "f_bankmark",column ="Fcard_banks_relation"),
            @Result(property = "f_bankno",column ="Fbind_card_bank_number")
    })
    public List<DownLoadSalaryReq> querySalary(DownLoadSalaryReq downLoadSalaryReq);

    @Select(" SELECT (@i:=@i+1) as fsequence_number, Fcre_name,Fuserid FROM t_salary_account,(select @i:=0) as it where Fcorpid = #{fcorpid}")
    @Results(id="querySalarydetail",value = {
            @Result(property = "fuser_name",column ="Fcre_name"),
            @Result(property = "fuserid",column ="Fuserid")
    })
    public List<UplaodPayroll> querySalarydetail(UplaodPayroll uplaodPayroll);

    @Select(" SELECT (@i:=@i+1) as fsequence_number, Fcre_name,Fuserid FROM t_salary_account,(select @i:=0) as it where Fcorpid = #{fcorpid}")
    @Results(id="querySalarydetailInfo",value = {
            @Result(property = "fuser_name",column ="Fcre_name"),
            @Result(property = "fuserid",column ="Fuserid")
    })
    public List<SalaryDetailInfo> querySalarydetailInfo(SalaryDetailInfo salaryDetailInfo);

    @Update("update t_salary_account "
            + "set fgesture_password = #{fgesturePassword} "
            + ", Fmodify_time = now() "
            + "where Fuserid = #{userId} "
            + "and Fcorpid =#{fcorpId} "
            + "and fstate = #{finitSatatus} LIMIT 1")
    public void setdataFgesture(GestCheckReqParam gestCheckReqParam);

    @Update("update t_salary_account set fgesture_password = #{fgesturePassword}, Fmodify_time = now() where Fuserid = #{userId} and Fcorpid =#{fcorpId} LIMIT 1")
    public void updataFgesture(GestCheckReqParam gestCheckReqParam);

    @Select("SELECT ts.Fgesture_password FROM t_salary_account ts WHERE  ts.Fuserid = #{userId} and ts.Fcorpid =#{fcorpId}")
    public String getPasswordById(GestCheckReqParam gestCheckReqParam);


    @Select("SELECT u.Fcorpid,u.Fuserid, s.Fbind_card_number,s.Fbind_card_bank_number,s.Fcard_banks_relation,u.Fdepartment FROM t_user_info u " +
            " LEFT JOIN t_salary_account s ON u.Fuserid = s.Fuserid AND u.Fcorpid = s.Fcorpid " +
            " where u.Fcorpid = #{corpid}")
    @Results(id="querySalaryAccountByCorpid",value = {
            @Result(property = "userid",column ="Fuserid"),
            @Result(property = "corpid",column ="Fcorpid"),
            @Result(property = "bind_card_number",column ="Fbind_card_number"),
            @Result(property = "bind_card_bank_number",column ="Fbind_card_bank_number"),
            @Result(property = "department",column ="Fdepartment"),
            @Result(property = "card_banks_relation",column ="Fcard_banks_relation")
    })
    public List<SalaryAccount> querySalaryAccountByCorpid(@Param(value = "corpid") String corpid);

    @Select(" SELECT u.Fcorpid,u.Fuserid, u.Fname ,cd.Fdepartment_id,cd.Fdepartment_name FROM t_user_info u " +
            "  LEFT JOIN t_salary_account sa ON u.Fuserid = sa.Fuserid AND u.Fcorpid = sa.Fcorpid " +
            "  LEFT JOIN t_salary_corp_conf scc ON u.Fcorpid = scc.Fcorpid AND scc.Fkey = #{fkey} AND scc.Fvalue = #{fvalue} " +
            "  LEFT JOIN t_corp_department  cd  ON u.Fdepartment = concat('[', cd.Fdepartment_id,']') AND u.Fcorpid = cd.Fcorpid " +
            "  WHERE u.Fcorpid = #{fcorpid} AND (sa.Fstate != #{fstate} OR sa.Fstate IS NULL)  AND (sa.Fbind_card_number is null OR sa.Fbind_card_number = '') ")
    @Results(id="querySalaryAccountForCmb",value = {
            @Result(property = "fuserid",column ="Fuserid"),
            @Result(property = "fcorpid",column ="Fcorpid"),
            @Result(property = "fuser_name",column ="Fname")
    })
    public List<SalaryAccountForCmbInfo> querySalaryAccountForCmb(@Param(value = "fcorpid") String corpid,
                                                                  @Param(value = "fkey") String fkey,
                                                                  @Param(value = "fvalue") String fvalue,
                                                                  @Param(value = "fstate") int fstate);


    @Update("UPDATE t_salary_account SET Fstate = #{fstate}, Fcre_name = #{fcre_name}, Fbind_card_bank_sname = #{fbind_card_bank_sname}, Fbind_card_number = #{fbind_card_number}, " +
            "Fbind_card_bank_number = #{fbind_card_bank_number},Fremark = #{fremark},Fmodify_time = NOW() " +
            "WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} LIMIT 1")
    public void updateSalaryAccountForCmb(@Param("userid") String userId,
                                          @Param("corpid") String corpId,
                                          @Param("fstate") int fstate,
                                          @Param("fcre_name") String fcre_name,
                                          @Param("fbind_card_bank_sname") String fbind_card_bank_sname,
                                          @Param("fbind_card_number") String fbind_card_number,
                                          @Param("fbind_card_bank_number") String fbind_card_bank_number,
                                          @Param("fremark") String fremark);

    @Update("UPDATE t_salary_account SET Fstate = #{fstate}, Fopening_type = #{fopening_type}, Fsalary_card_number = #{salary_card_number}, Fsalary_card_opened_time = #{salary_card_opened_time}, " +
            "Fcre_name = #{cst_nm}, Fbind_card_number = #{bind_card_number}, Fbind_card_bank_number = #{bind_card_bank_number}," +
            "Fcre_id = #{cre_id}, Fcre_type = #{cre_type}" +
            "Fmobile_number = #{mobileNumber}, Fmodify_time = NOW(), Fcard_banks_relation = #{Card_banks_relation},Fbind_card_bank_sname = #{bind_card_bank_sname} " +
            "WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} LIMIT 1")
    public void updateOpenedCallbackSalaryAccount(@Param("userid") String userId,
                                           @Param("corpid") String corpId,
                                           @Param("mobileNumber") String mobileNumber,
                                           @Param("fstate") int fstate,
                                           @Param("fopening_type") String fopening_type,
                                           @Param("salary_card_number") String salary_card_number,
                                           @Param("salary_card_opened_time") String salary_card_opened_time,
                                           @Param("cst_nm") String cst_nm,
                                           @Param("cre_id") String cre_id,
                                           @Param("cre_type") String cre_type,
                                           @Param("bind_card_number") String bind_card_number,
                                           @Param("bind_card_bank_number") String bind_card_bank_number,
                                           @Param("Card_banks_relation") String Card_banks_relation,
                                           @Param("bind_card_bank_sname") String bind_card_bank_sname
    );

    /**
     * 仅绑卡模式银行插入工资卡账户信息
     */
    @Insert("INSERT INTO t_salary_account" +
            "(Fcorpid, Fuserid, Fstate, Fbind_card_number, Fbind_card_bank_sname, Fbind_card_bank_number," +
            " Fcreate_time, Fmodify_time) VALUES " +
            "(#{corpid}, #{userid}, #{state}, #{cardNum}, #{bankName}, #{bankNum}, NOW(), NOW())")
    public void insertBindCardInfo(@Param("corpid") String corpid,
                                 @Param("userid") String userid,
                                 @Param("state") int state,
                                 @Param("cardNum") String cardNum,
                                 @Param("bankName") String bankName, @Param("bankNum")String bankNum);

    /**
     * 跟新工资账户的绑卡信息
     */
    @Insert("UPDATE t_salary_account SET Fbind_card_number=#{cardNum}, Fbind_card_bank_sname=#{bankName}, " +
            "Fbind_card_bank_number=#{bankNum}, Fmodify_time=NOW() WHERE Fcorpid = #{corpid} AND Fuserid = #{userid} LIMIT 1")
    public void updateBindCardInfo(@Param("corpid") String corpid,
                                   @Param("userid") String userid,
                                   @Param("cardNum") String cardNum,
                                   @Param("bankName") String bankName, @Param("bankNum")String bankNum);


    /**
     *根据企业ID查询员工
     */
    @Select(" SELECT Fuserid  FROM t_salary_account WHERE Fcorpid = #{corpid}")
    public List<String> selectSalaryAccountUserId(@Param("corpid") String corpid);

    /**
     * 插入工资卡账户信息
     */
    @Insert("INSERT INTO t_salary_account" +
            "(Fcorpid, Fuserid, Fstate, Fcre_name,Fbind_card_bank_sname,Fbind_card_number, Fbind_card_bank_number,Fremark, " +
            " Fcreate_time, Fmodify_time) VALUES " +
            "(#{corpid}, #{userid}, #{fstate}, #{fcre_name}, #{fbind_card_bank_sname},#{fbind_card_number},#{fbind_card_bank_number},#{fremark}, NOW(), NOW())")
    public void insertSalaryAccountForCmb(@Param("userid") String userId,
                                          @Param("corpid") String corpId,
                                          @Param("fstate") int fstate,
                                          @Param("fcre_name") String fcre_name,
                                          @Param("fbind_card_bank_sname") String fbind_card_bank_sname,
                                          @Param("fbind_card_number") String fbind_card_number,
                                          @Param("fbind_card_bank_number") String fbind_card_bank_number,
                                          @Param("fremark") String fremark);


    /**
     * 银行H5开户完成后插入工资卡账户信息
     */
    @Insert("INSERT INTO t_salary_account" +
            "(Fcorpid, Fuserid,Fstate, Fcre_type,Fcre_id,Fcre_address, Fcre_name,Fcre_gender,Fmobile_number,Fsalary_card_opened_time " +
            " Fbind_card_bank_sname,Fbind_card_number,Fbind_card_bank_number,Fsalary_card_number ,Fsalary_card_bank_type, "+
            " Fsalary_card_bank_sname,Fcard_banks_relation  "+
            " Fcreate_time, Fmodify_time) VALUES " +
            "(#{corpid}, #{userid}, #{fstate}, #{fcre_type}, #{fcre_id},#{fcre_address},#{fcre_name}, " +
            "  #{fcre_gender}, #{fmobile_number}, date_format(#{fsalary_card_opened_time},'%Y-%m-%d %T'), #{fbind_card_bank_sname}, #{fbind_card_number},#{fbind_card_bank_number}  "+
            " #{fsalary_card_number},#{fsalary_card_bank_type}, #{fsalary_card_bank_sname}, #{fcard_banks_relation}, "+
            " NOW(), NOW())")
    public void insertSalaryAccountForBankH5(
                                          @Param("corpid") String corpId,
                                          @Param("userid") String userId,
                                          @Param("fstate") int state,
                                          @Param("fcre_type") String cre_type,
                                          @Param("fcre_id") String cre_id,
                                          @Param("fcre_address") String cre_address,
                                          @Param("fcre_name") String cre_name,
                                         @Param("fcre_gender") String cre_gender,
                                         @Param("fmobile_number") String mobile_number,
                                         @Param("fsalary_card_opened_time") String salary_card_opened_time,
                                         @Param("fbind_card_bank_sname") String bind_card_bank_sname,
                                         @Param("fbind_card_number") String bind_card_number,
                                         @Param("fbind_card_bank_number") String bind_card_bank_number,
                                         @Param("fsalary_card_number") String salary_card_number,
                                         @Param("fsalary_card_bank_type") int salary_card_bank_type,
                                         @Param("fsalary_card_bank_sname") String salary_card_bank_sname,
                                         @Param("fcard_banks_relation") String card_banks_relation);


    @Select("SELECT " +
            "  COUNT(CASE WHEN t1.Fstate = #{state} AND DATE_FORMAT(t1.Fmodify_time, '%Y%m' ) = #{month} THEN 1" +
            "  ELSE NULL END) AS userLeave," +
            "  COUNT(CASE WHEN  DATE_FORMAT(t2.Fcreate_time, '%Y%m' ) = #{month} THEN 1" +
            "  ELSE NULL END) AS userComein " +
            " FROM " +
            " t_user_info t2 " +
            " LEFT JOIN t_salary_account t1 " +
            " ON t1.Fcorpid = t2.Fcorpid " +
            " AND t1.Fuserid = t2.Fuserid " +
            " WHERE t1.Fcorpid = #{corpid} ")
    public UserChangeResponse queryUserComeinAndLeave(@Param("corpid") String corpId , @Param("state") int state,@Param("month") String month);
}