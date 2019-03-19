package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.FetchAmountReqParam;
import org.apache.commons.lang3.StringUtils;

public class FetchAmountReq extends BankProxyRelayRequestMsg {

    private static final String CST_ACCNO = "Cst_AccNo";
    private static final String ZZ_AMT = "ZZ_amt";
    private static final String ZZ_ASS_ACCT_NO = "ZZ_ass_acct_no";
    private static final String S_MS = "SMS" ;
    private static final String CRDHLDR_CRDT_NO = "CrdHldr_Crdt_No" ;
    private static final String CRDHLDR_NM = "CrdHldr_Nm" ;
    private static final String RPPDBNK_BRNO = "RPPDBnk_BrNo" ;
    private static final String OPEN_BRANCH_ID = "Open_Branch_id" ;

    public FetchAmountReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }



    @JsonProperty(value="SMS", required=true)
    private String SMS;//短信验证码

    @JsonProperty(value="CrdHldr_Crdt_No", required=true)
    private String CrdHldr_Crdt_No;//持卡人证件号码
    @JsonProperty(value="CrdHldr_Nm", required=true)
    private String CrdHldr_Nm;//持卡人姓名

    @JsonProperty(value="Cst_AccNo", required=true)
    private String Cst_AccNo;//客户账号
    @JsonProperty(value="ZZ_amt", required=true)
    private Double ZZ_amt;//交易金额

    @JsonProperty( value="ZZ_ass_acct_no",required=true)
    private String ZZ_ass_acct_no;//账号2

    @JsonProperty(value="RPPDBnk_BrNo", required=true)
    private String RPPDBnk_BrNo;//收款人开户行行号


    @JsonProperty(value="Open_Branch_id", required=true)
    private String Open_Branch_id;


    public void setRequest(FetchAmountReqParam request, String bankType)
    {
        this.setCst_AccNo(request.getCst_AccNo());
        this.setZZ_ass_acct_no(request.getZZ_ass_acct_no());
        this.setZZ_amt(request.getZZ_amt());
        this.setSMS(request.getSMS());
        this.setCrdHldr_Crdt_No(request.getCrdHldr_Crdt_No());
        this.setCrdHldr_Nm(request.getCrdHldr_Nm());
        this.setRPPDBnk_BrNo(request.getRPPDBnk_BrNo());
        this.setOpen_Branch_id(request.getOpen_Branch_id());
        this.validate();
    }


    private void validate()
    {
        if(StringUtils.isBlank(this.getCst_AccNo())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Cst_AccNo is empty.");
        }
        if(StringUtils.isBlank(this.getZZ_ass_acct_no())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "ZZ_ass_acct_no is empty.");
        }
        if(StringUtils.isBlank(this.getZZ_amt().toString())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "ZZ_amt is empty.");
        }
        if(StringUtils.isBlank(this.getSMS())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "SMS is empty.");
        }
        if(StringUtils.isBlank(this.getCrdHldr_Crdt_No())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "CrdHldr_Crdt_No is empty.");
        }
        if(StringUtils.isBlank(this.getCrdHldr_Nm())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "CrdHldr_Nm is empty.");
        }
    }



    public String getSMS() {
        return SMS;
    }

    public void setSMS(String SMS) {
        this.put(S_MS,SMS);
        this.SMS = SMS;
    }


    public String getCrdHldr_Crdt_No() {
        return CrdHldr_Crdt_No;
    }

    public void setCrdHldr_Crdt_No(String CrdHldr_Crdt_No) {
        this.put(CRDHLDR_CRDT_NO,CrdHldr_Crdt_No);
        this.CrdHldr_Crdt_No = CrdHldr_Crdt_No;
    }

    public String getCrdHldr_Nm() {
        return CrdHldr_Nm;
    }

    public void setCrdHldr_Nm(String CrdHldr_Nm) {
        this.put(CRDHLDR_NM,CrdHldr_Nm);
        this.CrdHldr_Nm = CrdHldr_Nm;
    }



    public String getCst_AccNo() {
        return Cst_AccNo;
    }

    public void setCst_AccNo(String Cst_AccNo) {
        this.put(CST_ACCNO,Cst_AccNo);
        this.Cst_AccNo = Cst_AccNo;
    }

    public Double getZZ_amt() {
        return ZZ_amt;
    }

    public void setZZ_amt(Double ZZ_amt) {
        this.put(ZZ_AMT,ZZ_amt.toString());
        this.ZZ_amt = ZZ_amt;
    }


    public String getZZ_ass_acct_no() {
        return ZZ_ass_acct_no;
    }

    public void setZZ_ass_acct_no(String ZZ_ass_acct_no)
    {   this.put(ZZ_ASS_ACCT_NO,ZZ_ass_acct_no);
        this.ZZ_ass_acct_no = ZZ_ass_acct_no;
    }


    public String getRPPDBnk_BrNo() {
        return RPPDBnk_BrNo;
    }

    public void setRPPDBnk_BrNo(String RPPDBnk_BrNo) {
        this.put(RPPDBNK_BRNO,RPPDBnk_BrNo);
        this.RPPDBnk_BrNo = RPPDBnk_BrNo;
    }
    public String getOpen_Branch_id() {
        return this.get(OPEN_BRANCH_ID);
    }

    public void setOpen_Branch_id(String Open_Branch_id) {
        this.put(OPEN_BRANCH_ID,Open_Branch_id);
        this.Open_Branch_id = Open_Branch_id;

    }

}