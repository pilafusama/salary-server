package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.OpenSalaryAccountReqParam;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by hxyh on 2018/8/21.
 */
public class OpenSalaryAccountReq extends BankProxyRelayRequestMsg {

    private static final String MOBILE_NUMBER = "mobile_number";
    private static final String SMS_CODE = "sms_code";
    private static final String BANKTYPE = "banktype";

    private static final String SMS = "sms";
    private static final String CRDT_TPCD = "crdt_tpcd";
    private static final String CRDT_NO = "crdt_no";
    private static final String CST_NM = "cst_nm";
    private static final String HDL_INSID = "hdl_insid";
    private static final String RLTV_ACCNO = "rltv_accno";
    private static final String RSRV_TPCD = "rsrv_tpcd";
    private static final String DPBKBKCD = "dpbkbkcd";
    private static final String GND_CD = "gnd_cd";
    private static final String CTC_ADR = "ctc_adr";
    private static final String CSTMGR_ID = "cstmgr_id";
    private static final String BRTH_DT = "brth_dt";
    private static final String OCP_CD = "ocp_cd";
    private static final String FAM_ADR = "fam_adr";
    private static final String FAM_ADR_ZIPECD = "fam_adr_zipecd";
    private static final String EMAIL_ADR = "email_adr";
    private static final String EMGR_CTCPSN = "emgr_ctcpsn";
    private static final String PRSZ_INF_1 = "prsz_inf_1";
    private static final String EMGR_CTC_TEL = "emgr_ctc_tel";
    private static final String FIX_TELNO = "fix_telno";
    private static final String WRK_UNIT_NM = "wrk_unit_nm";
    private static final String UNIT_ADR = "unit_adr";
    private static final String ZIPECD = "zipecd";
    private static final String UNIT_TEL = "unit_tel";
    private static final String MBLPH_NO = "mblph_no";
    private static final String CRDT_EFDT = "crdt_efdt";
    private static final String CRDT_EXDAT = "crdt_exdat";
    private static final String EBNK_LND_PSWD = "ebnk_lnd_pswd";
    private static final String PLTFRM_NM = "pltfrm_nm";
    private static final String PLAT_FLOW_NO = "plat_flow_no";
    private static final String MBSH_NO = "mbsh_no";
    private static final String RSRV_FLD_1 = "rsrv_fld_1";
    private static final String RSRV_FLD_2 = "rsrv_fld_2";
    private static final String RSRV_FLD_3 = "rsrv_fld_3";
    private static final String RSRV_FLD_4 = "rsrv_fld_4";

    private String mobileNumber;
    private String smsCode;
    private String banktype;

    private String sms;
    private String crdt_tpcd;
    private String crdt_no;
    private String cst_nm;
    private String hdl_insid;
    private String rltv_accno;
    private String rsrv_tpcd;
    private String dpbkbkcd;
    private String gnd_cd;
    private String ctc_adr;
    private String cstmgr_id;
    private String brth_dt;
    private String ocp_cd;
    private String fam_adr;
    private String fam_adr_zipecd;
    private String email_adr;
    private String emgr_ctcpsn;
    private String prsz_inf_1;
    private String emgr_ctc_tel;
    private String fix_telno;
    private String wrk_unit_nm;
    private String unit_adr;
    private String zipecd;
    private String unit_tel;
    private String mblph_no;
    private String crdt_efdt;
    private String crdt_exdat;
    private String ebnk_lnd_pswd;
    private String pltfrm_nm;
    private String plat_flow_no;
    private String mbsh_no;
    private String rsrv_fld_1;
    private String rsrv_fld_2;
    private String rsrv_fld_3;
    private String rsrv_fld_4;

    public OpenSalaryAccountReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }

    public void setRequest(OpenSalaryAccountReqParam request, String bankType)
    {
        this.setMobileNumber(request.getMobileNumber());
        this.setSmsCode(request.getSmsCode());
        this.setSms(request.getSms());
        this.setCrdt_tpcd(request.getCrdt_tpcd());
        this.setCrdt_no(request.getCrdt_no());
        this.setCst_nm(request.getCst_nm());
        this.setHdl_insid(request.getHdl_insid());
        this.setRltv_accno(request.getRltv_accno());
        this.setRsrv_tpcd(request.getRsrv_tpcd());
        this.setDpbkbkcd(request.getDpbkbkcd());
        this.setGnd_cd(request.getGnd_cd());
        this.setCtc_adr(request.getCtc_adr());
        this.setCstmgr_id(request.getCstmgr_id());
        this.setBrth_dt(request.getBrth_dt());
        this.setOcp_cd(request.getOcp_cd());
        this.setFam_adr(request.getFam_adr());
        this.setFam_adr_zipecd(request.getFam_adr_zipecd());
        this.setEmail_adr(request.getEmail_adr());
        this.setEmgr_ctcpsn(request.getEmgr_ctcpsn());
        this.setPrsz_inf_1(request.getPrsz_inf_1());
        this.setEmgr_ctc_tel(request.getEmgr_ctc_tel());
        this.setFix_telno(request.getFix_telno());
        this.setWrk_unit_nm(request.getWrk_unit_nm());
        this.setUnit_adr(request.getUnit_adr());
        this.setZipecd(request.getZipecd());
        this.setUnit_tel(request.getUnit_tel());
        this.setMblph_no(request.getMblph_no());
        this.setCrdt_efdt(request.getCrdt_efdt());
        this.setCrdt_exdat(request.getCrdt_exdat());
        this.setEbnk_lnd_pswd(request.getEbnk_lnd_pswd());
        this.setPltfrm_nm(request.getPltfrm_nm());
        this.setPlat_flow_no(request.getPlat_flow_no());
        this.setMbsh_no(request.getMbsh_no());
        this.setRsrv_fld_1(request.getRsrv_fld_1());
        this.setRsrv_fld_2(request.getRsrv_fld_2());
        this.setRsrv_fld_3(request.getRsrv_fld_3());
        this.setRsrv_fld_4(request.getRsrv_fld_4());
        this.validate();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.put(MOBILE_NUMBER,mobileNumber);
        this.mobileNumber = mobileNumber;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.put(SMS_CODE,smsCode);
        this.smsCode = smsCode;
    }

    public String getSms() {
        return sms;
    }

    public String getCrdt_tpcd() {
        return crdt_tpcd;
    }

    public String getCrdt_no() {
        return crdt_no;
    }

    public String getCst_nm() {
        return cst_nm;
    }

    public String getHdl_insid() {
        return hdl_insid;
    }

    public String getRltv_accno() {
        return rltv_accno;
    }

    public String getRsrv_tpcd() {
        return rsrv_tpcd;
    }

    public String getDpbkbkcd() {
        return dpbkbkcd;
    }

    public String getGnd_cd() {
        return gnd_cd;
    }

    public String getCtc_adr() {
        return ctc_adr;
    }

    public String getCstmgr_id() {
        return cstmgr_id;
    }

    public String getBrth_dt() {
        return brth_dt;
    }

    public String getOcp_cd() {
        return ocp_cd;
    }

    public String getFam_adr() {
        return fam_adr;
    }

    public String getFam_adr_zipecd() {
        return fam_adr_zipecd;
    }

    public String getEmail_adr() {
        return email_adr;
    }

    public String getEmgr_ctcpsn() {
        return emgr_ctcpsn;
    }

    public String getPrsz_inf_1() {
        return prsz_inf_1;
    }

    public String getEmgr_ctc_tel() {
        return emgr_ctc_tel;
    }

    public String getFix_telno() {
        return fix_telno;
    }

    public String getWrk_unit_nm() {
        return wrk_unit_nm;
    }

    public String getUnit_adr() {
        return unit_adr;
    }

    public String getZipecd() {
        return zipecd;
    }

    public String getUnit_tel() {
        return unit_tel;
    }

    public String getMblph_no() {
        return mblph_no;
    }

    public String getCrdt_efdt() {
        return crdt_efdt;
    }

    public String getCrdt_exdat() {
        return crdt_exdat;
    }

    public String getEbnk_lnd_pswd() {
        return ebnk_lnd_pswd;
    }

    public String getPltfrm_nm() {
        return pltfrm_nm;
    }

    public String getPlat_flow_no() {
        return plat_flow_no;
    }

    public String getMbsh_no() {
        return mbsh_no;
    }

    public String getRsrv_fld_1() {
        return rsrv_fld_1;
    }

    public String getRsrv_fld_2() {
        return rsrv_fld_2;
    }

    public String getRsrv_fld_3() {
        return rsrv_fld_3;
    }

    public String getRsrv_fld_4() {
        return rsrv_fld_4;
    }

    public void setSms(String sms) {
        this.put(SMS,sms);
        this.sms = sms;
    }

    public void setCrdt_tpcd(String crdt_tpcd) {
        this.put(CRDT_TPCD,crdt_tpcd);
        this.crdt_tpcd = crdt_tpcd;
    }

    public void setCrdt_no(String crdt_no) {
        this.put(CRDT_NO,crdt_no);
        this.crdt_no = crdt_no;
    }

    public void setCst_nm(String cst_nm) {
        this.put(CST_NM,cst_nm);
        this.cst_nm = cst_nm;
    }

    public void setHdl_insid(String hdl_insid) {
        this.put(HDL_INSID,hdl_insid);
        this.hdl_insid = hdl_insid;
    }

    public void setRltv_accno(String rltv_accno) {
        this.put(RLTV_ACCNO,rltv_accno);
        this.rltv_accno = rltv_accno;
    }

    public void setRsrv_tpcd(String rsrv_tpcd) {
        this.put(RSRV_TPCD,rsrv_tpcd);
        this.rsrv_tpcd = rsrv_tpcd;
    }

    public void setDpbkbkcd(String dpbkbkcd) {
        this.put(DPBKBKCD,dpbkbkcd);
        this.dpbkbkcd = dpbkbkcd;
    }

    public void setGnd_cd(String gnd_cd) {
        this.put(GND_CD,gnd_cd);
        this.gnd_cd = gnd_cd;
    }

    public void setCtc_adr(String ctc_adr) {
        this.put(CTC_ADR,ctc_adr);
        this.ctc_adr = ctc_adr;
    }

    public void setCstmgr_id(String cstmgr_id) {
        this.put(CSTMGR_ID,cstmgr_id);
        this.cstmgr_id = cstmgr_id;
    }

    public void setBrth_dt(String brth_dt) {
        this.put(BRTH_DT,brth_dt);
        this.brth_dt = brth_dt;
    }

    public void setOcp_cd(String ocp_cd) {
        this.put(OCP_CD,ocp_cd);
        this.ocp_cd = ocp_cd;
    }

    public void setFam_adr(String fam_adr) {
        this.put(FAM_ADR,fam_adr);
        this.fam_adr = fam_adr;
    }

    public void setFam_adr_zipecd(String fam_adr_zipecd) {
        this.put(FAM_ADR_ZIPECD,fam_adr_zipecd);
        this.fam_adr_zipecd = fam_adr_zipecd;
    }

    public void setEmail_adr(String email_adr) {
        this.put(EMAIL_ADR,email_adr);
        this.email_adr = email_adr;
    }

    public void setEmgr_ctcpsn(String emgr_ctcpsn) {
        this.put(EMGR_CTCPSN,emgr_ctcpsn);
        this.emgr_ctcpsn = emgr_ctcpsn;
    }

    public void setPrsz_inf_1(String prsz_inf_1) {
        this.put(PRSZ_INF_1,prsz_inf_1);
        this.prsz_inf_1 = prsz_inf_1;
    }

    public void setEmgr_ctc_tel(String emgr_ctc_tel) {
        this.put(EMGR_CTC_TEL,emgr_ctc_tel);
        this.emgr_ctc_tel = emgr_ctc_tel;
    }

    public void setFix_telno(String fix_telno) {
        this.put(FIX_TELNO,fix_telno);
        this.fix_telno = fix_telno;
    }

    public void setWrk_unit_nm(String wrk_unit_nm) {
        this.put(WRK_UNIT_NM,wrk_unit_nm);
        this.wrk_unit_nm = wrk_unit_nm;
    }

    public void setUnit_adr(String unit_adr) {
        this.put(UNIT_ADR,unit_adr);
        this.unit_adr = unit_adr;
    }

    public void setZipecd(String zipecd) {
        this.put(ZIPECD,zipecd);
        this.zipecd = zipecd;
    }

    public void setUnit_tel(String unit_tel) {
        this.put(UNIT_TEL,unit_tel);
        this.unit_tel = unit_tel;
    }

    public void setMblph_no(String mblph_no) {
        this.put(MBLPH_NO,mblph_no);
        this.mblph_no = mblph_no;
    }

    public void setCrdt_efdt(String crdt_efdt) {
        this.put(CRDT_EFDT,crdt_efdt);
        this.crdt_efdt = crdt_efdt;
    }

    public void setCrdt_exdat(String crdt_exdat) {
        this.put(CRDT_EXDAT,crdt_exdat);
        this.crdt_exdat = crdt_exdat;
    }

    public void setEbnk_lnd_pswd(String ebnk_lnd_pswd) {
        this.put(EBNK_LND_PSWD,ebnk_lnd_pswd);
        this.ebnk_lnd_pswd = ebnk_lnd_pswd;
    }

    public void setPltfrm_nm(String pltfrm_nm) {
        this.put(PLTFRM_NM,pltfrm_nm);
        this.pltfrm_nm = pltfrm_nm;
    }

    public void setPlat_flow_no(String plat_flow_no) {
        this.put(PLAT_FLOW_NO,plat_flow_no);
        this.plat_flow_no = plat_flow_no;
    }

    public void setMbsh_no(String mbsh_no) {
        this.put(MBSH_NO,mbsh_no);
        this.mbsh_no = mbsh_no;
    }

    public void setRsrv_fld_1(String rsrv_fld_1) {
        this.put(RSRV_FLD_1,rsrv_fld_1);
        this.rsrv_fld_1 = rsrv_fld_1;
    }

    public void setRsrv_fld_2(String rsrv_fld_2) {
        this.put(RSRV_FLD_2,rsrv_fld_2);
        this.rsrv_fld_2 = rsrv_fld_2;
    }

    public void setRsrv_fld_3(String rsrv_fld_3) {
        this.put(RSRV_FLD_3,rsrv_fld_3);
        this.rsrv_fld_3 = rsrv_fld_3;
    }

    public void setRsrv_fld_4(String rsrv_fld_4) {
        this.put(RSRV_FLD_4,rsrv_fld_4);
        this.rsrv_fld_4 = rsrv_fld_4;
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.put(BANKTYPE,banktype);
        this.banktype = banktype;
    }

    private void validate()
    {
        if(StringUtils.isBlank(this.getSms())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "sms is empty.");
        }
        if(StringUtils.isBlank(this.getCrdt_tpcd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Crdt_TpCd is empty.");
        }
        if(StringUtils.isBlank(this.getCrdt_no())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Crdt_No is empty.");
        }
        if(StringUtils.isBlank(this.getCst_nm())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Cst_Nm is empty.");
        }
        if(StringUtils.isBlank(this.getRltv_accno())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Rltv_AccNo is empty.");
        }
        if(StringUtils.isBlank(this.getRsrv_tpcd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Rsrv_TpCd is empty.");
        }
        if(StringUtils.isBlank(this.getDpbkbkcd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "DpBkBkCD is empty.");
        }
        if(StringUtils.isBlank(this.getGnd_cd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Gnd_Cd is empty.");
        }
        if(StringUtils.isBlank(this.getCtc_adr())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Ctc_Adr is empty.");
        }
        if(StringUtils.isBlank(this.getOcp_cd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Ocp_Cd is empty.");
        }
        if(StringUtils.isBlank(this.getMblph_no())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "MblPh_No is empty.");
        }
        if(StringUtils.isBlank(this.getCrdt_efdt())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Crdt_EfDt is empty.");
        }
        if(StringUtils.isBlank(this.getCrdt_exdat())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Crdt_ExDat is empty.");
        }
        if(StringUtils.isBlank(this.getPlat_flow_no())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "PLAT_FLOW_No is empty.");
        }
        if(StringUtils.isBlank(this.getMbsh_no())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Mbsh_No is empty.");
        }
    }
}
