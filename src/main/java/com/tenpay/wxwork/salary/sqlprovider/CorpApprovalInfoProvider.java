package com.tenpay.wxwork.salary.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

import com.tenpay.wxwork.salary.model.CorpApprovalInfo;

public class CorpApprovalInfoProvider {
	
    public String insertApprovalInfo(CorpApprovalInfo approvalInfo)
    {
    	SQL sql = new SQL();
    	sql.INNER_JOIN("t_approval_info");
    	sql.VALUES("Fapproval_id", "#{approval_id}");
    	sql.VALUES("Fcorp_id", "#{corp_id}");
    	sql.VALUES("Fop_userid",  "#{op_userid}");
    	sql.VALUES("Fbank_id",  "#{bank_id}");
    	sql.VALUES("Fname",  "#{name}");
    	sql.VALUES("Ftype",  "#{type}");
    	sql.VALUES("Fapply_name",  "#{apply_name}");
    	sql.VALUES("Fapply_time",  "#{apply_time}");
    	sql.VALUES("Fapproval_name",  "#{approval_name}");
    	sql.VALUES("Fstatus",  "#{status,jdbcType=INTEGER}");
    	sql.VALUES("Freceive_name",  "#{receive_name}");
    	sql.VALUES("Freceive_account",  "#{receive_account}");
    	sql.VALUES("Faccount_bank",  "#{account_bank}");
    	sql.VALUES("Faccount_area",  "#{account_area}");
    	sql.VALUES("Fcross_flag",  "#{cross_flag,jdbcType=INTEGER}");
    	sql.VALUES("Famount",  "#{amount,jdbcType=BIGINT}");
    	sql.VALUES("Fdetail",  "#{detail}");
    	sql.VALUES("Fbank_list",  "#{bank_list}");
    	sql.VALUES("Fpayment_state",  "#{payment_state,jdbcType=INTEGER}");
    	sql.VALUES("Fpay_memo",  "#{pay_memo}");
    	sql.VALUES("Fpay_time",  "#{pay_time}");
    	sql.VALUES("Fcreate_time",  "#{create_time}");
    	sql.VALUES("Fmodify_time",  "#{modify_time}");        
        return sql.toString();
    }    
}
