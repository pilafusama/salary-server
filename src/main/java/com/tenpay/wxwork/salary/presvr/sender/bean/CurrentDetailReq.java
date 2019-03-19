package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.CurrentDetailReqParam;
import org.apache.commons.lang3.StringUtils;

public class CurrentDetailReq extends BankProxyRelayRequestMsg {

    private static final String DbCRD_CARDNO  = "DbCrd_CardNo";
    private static final String HSTCRD_AFCRD_IND  = "HstCrd_AfCrd_Ind";
    private static final String SEQ_FLD_IND  = "Seq_Fld_Ind";
    private static final String BEG_ENQR_DT  = "Beg_Enqr_Dt";
    private static final String CTOF_ENQR_DT  = "CtOf_Enqr_Dt";
    private static final String CCYCD  = "CcyCd";
    private static final String CSHEX_CD  = "CshEx_Cd";
    private static final String CRD_INNR_ACC_CGYCD  = "Crd_Innr_Acc_CgyCd";
    private static final String SMY_CD = "Smy_Cd";
    private static final String NBPAGE_START = "NbPageStart";

    private String DbCrd_CardNo; //借记卡卡号
    private String HstCrd_AfCrd_Ind; //主卡附属卡标志 0-主卡和副卡所有明细 1-只查询上送卡号明细
    private String Seq_Fld_Ind; //排序字段标志 0-查询结果按日期正序返回 1-查询结果按日期倒序返回
    private String Beg_Enqr_Dt; //起始查询日期
    private String CtOf_Enqr_Dt; //截止查询日期
    private String CcyCd; //币种代码
    private String CshEx_Cd; //钞汇代码
    private String Crd_Innr_Acc_CgyCd; //卡内账户类别代码
    private String Smy_Cd; //摘要代码 新一代摘要代码，取值举例： 0001-备用金 0002-工资/奖金 0003-退休金 0004-养老金 0005-公积金 0006-津贴 0251-扣收年费 0252-调息 0253-没收 0254-过户 0255-预存 0256-补存 0257-电话转账 0258-代发工资 0065-自定义 9999-未定义
    private String nbPageStart; //当前页次

    public CurrentDetailReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }

   public void setRequest(CurrentDetailReqParam reqParam, String fsalaryCardNo)
    {
        this.setDbCrd_CardNo(fsalaryCardNo);
        this.setHstCrd_AfCrd_Ind(reqParam.getHstCrd_AfCrd_Ind());
        this.setSeq_Fld_Ind(reqParam.getSeq_Fld_Ind());
        this.setBeg_Enqr_Dt(reqParam.getBeg_Enqr_Dt());
        this.setCtOf_Enqr_Dt(reqParam.getCtOf_Enqr_Dt());
        this.setCcyCd(reqParam.getCcyCd());
        this.setCshEx_Cd(reqParam.getCshEx_Cd());
        this.setCrd_Innr_Acc_CgyCd(reqParam.getCrd_Innr_Acc_CgyCd());
        this.setSmy_Cd(reqParam.getSmy_Cd());
        this.setNbPageStart(reqParam.getNbPageStart());
        this.validate();
    }

    public String getHstCrd_AfCrd_Ind() {
        return HstCrd_AfCrd_Ind;
    }

    public void setHstCrd_AfCrd_Ind(String hstCrd_AfCrd_Ind) {
        this.put(HSTCRD_AFCRD_IND, hstCrd_AfCrd_Ind);
        this.HstCrd_AfCrd_Ind = hstCrd_AfCrd_Ind;
    }

    public String getSeq_Fld_Ind() {
        return Seq_Fld_Ind;
    }

    public void setSeq_Fld_Ind(String seq_Fld_Ind) {
        this.put(SEQ_FLD_IND, seq_Fld_Ind);
        this.Seq_Fld_Ind = seq_Fld_Ind;
    }

    public String getDbCrd_CardNo() {
        return DbCrd_CardNo;
    }

    public void setDbCrd_CardNo(String dbCrd_CardNo) {
        this.put(DbCRD_CARDNO,dbCrd_CardNo);
        this.DbCrd_CardNo = dbCrd_CardNo;
    }

    public String getBeg_Enqr_Dt() {
        return Beg_Enqr_Dt;
    }

    public void setBeg_Enqr_Dt(String beg_Enqr_Dt) {
        this.put(BEG_ENQR_DT, beg_Enqr_Dt);
        this.Beg_Enqr_Dt = beg_Enqr_Dt;
    }

    public String getCtOf_Enqr_Dt() {
        return CtOf_Enqr_Dt;
    }

    public void setCtOf_Enqr_Dt(String ctOf_Enqr_Dt) {
        this.put(CTOF_ENQR_DT, ctOf_Enqr_Dt);
        this.CtOf_Enqr_Dt = ctOf_Enqr_Dt;
    }

    public String getCcyCd() {

        return CcyCd;
    }

    public void setCcyCd(String ccyCd) {
        this.put(CCYCD, ccyCd);
        this.CcyCd = ccyCd;
    }

    public String getCshEx_Cd() {
        return CshEx_Cd;
    }

    public void setCshEx_Cd(String cshEx_Cd) {
        this.put(CSHEX_CD, cshEx_Cd);
        this.CshEx_Cd = cshEx_Cd;
    }

    public String getCrd_Innr_Acc_CgyCd() {
        return Crd_Innr_Acc_CgyCd;
    }

    public void setCrd_Innr_Acc_CgyCd(String crd_Innr_Acc_CgyCd) {
        this.put(CRD_INNR_ACC_CGYCD, crd_Innr_Acc_CgyCd);
        this.Crd_Innr_Acc_CgyCd = crd_Innr_Acc_CgyCd;
    }

    public String getSmy_Cd() {
        return Smy_Cd;
    }

    public void setSmy_Cd(String smy_Cd) {
        this.put(SMY_CD, smy_Cd);
        this.Smy_Cd = smy_Cd;
    }

    public String getNbPageStart() {
        return nbPageStart;
    }

    public void setNbPageStart(String nbPageStart) {
        this.put(NBPAGE_START, nbPageStart);
        this.nbPageStart = nbPageStart;
    }


    private void validate()
    {
        if(StringUtils.isBlank(this.getDbCrd_CardNo())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "DbCrd_CardNo is empty.");
        }
        if(StringUtils.isBlank(this.getNbPageStart())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "NbPageStart is empty.");
        }
        if(StringUtils.isBlank(this.getHstCrd_AfCrd_Ind())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "HstCrd_AfCrd_Ind is empty.");
        }
        if(StringUtils.isBlank(this.getSeq_Fld_Ind())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Seq_Fld_Ind is empty.");
        }
        if(StringUtils.isBlank(this.getCcyCd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "CcyCd is empty.");
        }
        if(StringUtils.isBlank(this.getCshEx_Cd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "CshEx_Cd is empty.");
        }
        if(StringUtils.isBlank(this.getCrd_Innr_Acc_CgyCd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Crd_Innr_Acc_CgyCd is empty.");
        }
    }

}
