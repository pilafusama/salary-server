package com.tenpay.wxwork.salary.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.tenpay.wxwork.salary.model.CorpApprovalInfo;
import com.tenpay.wxwork.salary.sqlprovider.CorpApprovalInfoProvider;

@Mapper
public interface CorpApprovalInfoDAO {
    @Select("select Fapproval_id,Fcorp_id,Fop_userid,Fbank_id,Fname,Ftype,Fapply_name,Fapply_time,Fapproval_name,Fstatus,"
            + "Freceive_name,Freceive_account,Faccount_bank,Faccount_area,Fcross_flag,Famount,Fdetail,Fbank_list,"
            + "Fpayment_state,Fpay_memo,Fpay_time,Fcreate_time,Fmodify_time "
            + "from t_approval_info where Fapproval_id = #{approval_id}")
    @Results(id="CorpApprovalInfoMap",value = {
            @Result(property = "approval_id",column ="Fapproval_id"),
            @Result(property = "corp_id",column ="Fcorp_id"),
            @Result(property = "op_userid",column ="Fop_userid"),
            @Result(property = "bank_id",column ="Fbank_id"),
            @Result(property = "name",column ="Fname"),
            @Result(property = "type",column ="Ftype"),
            @Result(property = "apply_name",column ="Fapply_name"),
            @Result(property = "apply_time",column ="Fapply_time"),
            @Result(property = "approval_name" ,column ="Fapproval_name"),
            @Result(property = "status"  ,column ="Fstatus"),
            @Result(property = "receive_name",column ="Freceive_name"),
            @Result(property = "receive_account",column ="Freceive_account"),
            @Result(property = "account_bank",column ="Faccount_bank"),
            @Result(property = "account_area",column ="Faccount_area"),
            @Result(property = "cross_flag",column ="Fcross_flag"),
            @Result(property = "amount",column ="Famount"),
            @Result(property = "detail",column ="Fdetail"),
            @Result(property = "bank_list",column ="Fbank_list"),
            @Result(property = "payment_state",column ="Fpayment_state"),
            @Result(property = "pay_memo",column ="Fpay_memo"),
            @Result(property = "pay_time",column ="Fpay_time"),
            @Result(property = "create_time",column ="Fcreate_time"),
            @Result(property = "modify_time",column ="Fmodify_time")})
    public CorpApprovalInfo queryCorpApprovalInfo(@Param("approval_id") String approval_id);

    @Select("select Fapproval_id,Fcorp_id,Fop_userid,Fbank_id,Fname,Ftype,Fapply_name,Fapply_time,Fapproval_name,"
            + "Fstatus,Freceive_name,Freceive_account,Faccount_bank,Faccount_area,Fcross_flag,Famount,Fdetail,Fbank_list,"
            + "Fpayment_state,Fpay_memo,Fpay_time,Fcreate_time,Fmodify_time from t_approval_info where Fcorp_id = #{corpid} "
            + "order by Fmodify_time DESC limit 1")
    @ResultMap("CorpApprovalInfoMap")
    public CorpApprovalInfo queryLastCorpApprovalInfo(@Param("corpid") String corpid); //查询最后一次审批流信息

    @Update("update t_approval_info set Fpayment_state = #{paymentState}, Fpay_memo = #{payMemo}, Fpay_time = #{payTime}, Fmodify_time = now() where Fpayment_state = 'INIT' and Fapproval_id = #{approvalId}")
    public void updatePaymentState(@Param("paymentState") String paymentState, @Param("payMemo") String payMemo, @Param("payTime") String payTime, @Param("approvalId") String approvalId);

    @Update("update t_approval_info set Fstatus = #{status}, Fbank_list = #{bankList}, Fmodify_time = now() where Fstatus = 2 and Fapproval_id = #{approvalId}")
    public void updateBankApprovalInfo(@Param("status") int status, @Param("bankList") String bankList, @Param("approvalId") String approvalId);

    @Insert("INSERT INTO t_approval_info(Fapproval_id,Fcorp_id,Fop_userid,Fbank_id,Fname,Ftype,Fapply_name,Fapply_time,Fapproval_name,Fstatus,"
            + "Freceive_name,Freceive_account,Faccount_bank,Faccount_area,Fcross_flag,Famount,Fdetail,Fpayment_state,Fbank_list,Fpay_time,Fcreate_time,Fmodify_time)"
            + "values(#{approval_id},#{corp_id},#{op_userid},#{bank_id},#{name},#{type},#{apply_name},#{apply_time},#{approval_name},#{status},"
            + "#{receive_name},#{receive_account},#{account_bank},#{account_area},#{cross_flag},#{amount},#{detail},#{payment_state},'',#{pay_time},now(), now())")
    public void insertApprovalInfo(@Param("approval_id") String approval_id, @Param("corp_id") String corp_id, @Param("op_userid") String op_userid,
                                   @Param("bank_id") String bank_id, @Param("name") String name, @Param("type") String type, @Param("apply_name") String apply_name,
                                   @Param("apply_time") String apply_time, @Param("approval_name") String approval_name, @Param("status") int status, @Param("receive_name") String receive_name,
                                   @Param("receive_account") String receive_account, @Param("account_bank") String account_bank, @Param("account_area") String account_area,
                                   @Param("cross_flag") int cross_flag, @Param("amount") long amount, @Param("detail") String detail, @Param("payment_state") String payment_state, @Param("pay_time") String pay_time);
}