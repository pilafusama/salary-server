package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by hxyh on 2018/8/21.
 */
public class OpenSalaryAccountReqParam extends FrontEndReqBase {

    @NotBlank(message = "mobile_number不能为空")
    @Length(min = 1,max = 11)
    @JsonProperty(required = true)
    private String mobileNumber;

    @NotBlank(message = "sms_code不能为空")
    @Length(min = 1,max = 10)
    @JsonProperty(required = true)
    private String smsCode;

    @NotBlank(message = "BankType不能为空")
    @Length(min = 4,max = 4)
    @JsonProperty(required = true)
    private String BankType;

    @NotBlank(message = "Crdt_No不能为空")
    @Length(min = 1,max = 19)
    @JsonProperty(required = true)
    private String crdt_no;

    @NotBlank(message = "v_txcode不能为空")
    @Length(max = 120)
    @JsonProperty(value="V_TXCODE",required=true)
    private String v_txcode;

    @JsonProperty(value="scene", required=false)
    private String scene;

    @NotBlank(message = "SMS不能为空")
    @Length(min = 6,max = 6)
    @JsonProperty(required = true)
    private String sms;

    @NotBlank(message = "Crdt_TpCd不能为空")
    @Length(min = 4,max = 4)
    @JsonProperty(required = true)
    private String crdt_tpcd;

    @NotBlank(message = "Cst_Nm不能为空")
    @Length(min = 1,max = 240)
    @JsonProperty(required = true)
    private String cst_nm;

    @Length(min = 1,max = 9)
    private String hdl_insid;

    @NotBlank(message = "Rltv_AccNo不能为空")
    @Length(min = 1,max = 32)
    @JsonProperty(required = true)
    private String rltv_accno;

    @NotBlank(message = "Rsrv_TpCd不能为空")
    @Length(min = 1,max = 6)
    @JsonProperty(required = true)
    private String rsrv_tpcd;

    @NotBlank(message = "DpBkBkCD不能为空")
    @Length(min = 1,max =14)
    @JsonProperty(required = true)
    private String dpbkbkcd;

    @NotBlank(message = "Gnd_Cd不能为空")
    @Length(min = 1,max =2)
    @JsonProperty(required = true)
    private String gnd_cd;

    @NotBlank(message = "Ctc_Adr不能为空")
    @Length(min = 1,max =240)
    @JsonProperty(required = true)
    private String ctc_adr;

    @Length(min = 1,max =12)
    private String cstmgr_id;

    @Length(min = 1,max =8)
    private String brth_dt;

    @NotBlank(message = "Ocp_Cd不能为空")
    @Length(min = 1,max =20)
    @JsonProperty(required = true)
    private String ocp_cd;

    @Length(min = 1,max =20)
    private String fam_adr;

    @Length(min = 1,max =20)
    private String fam_adr_zipecd;

    @Length(min = 1,max =240)
    private String email_adr;

    @Length(min = 1,max =180)
    private String emgr_ctcpsn;

    @Length(min = 1,max =75)
    private String prsz_inf_1;

    @Length(min = 1,max =50)
    private String emgr_ctc_tel;

    @Length(min = 1,max =240)
    private String fix_telno;

    @Length(min = 1,max =600)
    private String wrk_unit_nm;

    @Length(min = 1,max =240)
    private String unit_adr;

    @Length(min = 1,max =30)
    private String zipecd;

    @Length(min = 1,max =50)
    private String unit_tel;

    @NotBlank(message = "MblPh_No不能为空")
    @Length(min = 11,max =50)
    @JsonProperty(required = true)
    private String mblph_no;

    @NotBlank(message = "Crdt_EfDt不能为空")
    @Length(min = 8,max =8)
    @JsonProperty(required = true)
    private String crdt_efdt;

    @NotBlank(message = "Crdt_ExDat不能为空")
    @Length(min = 8,max =8)
    @JsonProperty(required = true)
    private String crdt_exdat;

    @Length(min = 1,max =132)
    private String ebnk_lnd_pswd;

    @Length(min = 1,max =600)
    private String pltfrm_nm;

    @NotBlank(message = "PLAT_FLOW_NO不能为空")
    @Length(min = 1,max =25)
    @JsonProperty(required = true)
    private String plat_flow_no;

    @NotBlank(message = "Mbsh_No不能为空")
    @Length(min = 1,max =240)
    @JsonProperty(required = true)
    private String mbsh_no;

    @Length(min = 1,max =200)
    private String rsrv_fld_1;

    @Length(min = 1,max =200)
    private String rsrv_fld_2;

    @Length(min = 1,max =200)
    private String rsrv_fld_3;

    @Length(min = 1,max =200)
    private String rsrv_fld_4;

    private String corpid;
    private String userid;

    private String card_banks_relation;  //工资卡与绑定卡关系
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    @JsonIgnore
    public String getMobileNumber() {
        return mobileNumber;
    }

    @JsonIgnore
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @JsonIgnore
    public String getSmsCode() {
        return smsCode;
    }

    @JsonIgnore
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
    @JsonIgnore
    public String getBankType() {
        return BankType;
    }
    @JsonIgnore
    public void setBankType(String bankType) {
        BankType = bankType;
    }
    @JsonIgnore
    public String getSms() {
        return sms;
    }
    @JsonIgnore
    public void setSms(String sms) {
        this.sms = sms;
    }
    @JsonIgnore
    public String getCrdt_tpcd() {
        return crdt_tpcd;
    }
    @JsonIgnore
    public void setCrdt_tpcd(String crdt_tpcd) {
        this.crdt_tpcd = crdt_tpcd;
    }
    @JsonIgnore
    public String getCrdt_no() {
        return crdt_no;
    }
    @JsonIgnore
    public void setCrdt_no(String crdt_no) {
        this.crdt_no = crdt_no;
    }
    @JsonIgnore
    public String getCst_nm() {
        return cst_nm;
    }
    @JsonIgnore
    public void setCst_nm(String cst_nm) {
        this.cst_nm = cst_nm;
    }
    @JsonIgnore
    public String getHdl_insid() {
        return hdl_insid;
    }
    @JsonIgnore
    public void setHdl_insid(String hdl_insid) {
        this.hdl_insid = hdl_insid;
    }
    @JsonIgnore
    public String getRltv_accno() {
        return rltv_accno;
    }
    @JsonIgnore
    public void setRltv_accno(String rltv_accno) {
        this.rltv_accno = rltv_accno;
    }
    @JsonIgnore
    public String getRsrv_tpcd() {
        return rsrv_tpcd;
    }
    @JsonIgnore
    public void setRsrv_tpcd(String rsrv_tpcd) {
        this.rsrv_tpcd = rsrv_tpcd;
    }
    @JsonIgnore
    public String getDpbkbkcd() {
        return dpbkbkcd;
    }
    @JsonIgnore
    public void setDpbkbkcd(String dpbkbkcd) {
        this.dpbkbkcd = dpbkbkcd;
    }
    @JsonIgnore
    public String getGnd_cd() {
        return gnd_cd;
    }
    @JsonIgnore
    public void setGnd_cd(String gnd_cd) {
        this.gnd_cd = gnd_cd;
    }
    @JsonIgnore
    public String getCtc_adr() {
        return ctc_adr;
    }
    @JsonIgnore
    public void setCtc_adr(String ctc_adr) {
        this.ctc_adr = ctc_adr;
    }
    @JsonIgnore
    public String getCstmgr_id() {
        return cstmgr_id;
    }
    @JsonIgnore
    public void setCstmgr_id(String cstmgr_id) {
        this.cstmgr_id = cstmgr_id;
    }
    @JsonIgnore
    public String getBrth_dt() {
        return brth_dt;
    }
    @JsonIgnore
    public void setBrth_dt(String brth_dt) {
        this.brth_dt = brth_dt;
    }
    @JsonIgnore
    public String getOcp_cd() {
        return ocp_cd;
    }
    @JsonIgnore
    public void setOcp_cd(String ocp_cd) {
        this.ocp_cd = ocp_cd;
    }
    @JsonIgnore
    public String getFam_adr() {
        return fam_adr;
    }
    @JsonIgnore
    public void setFam_adr(String fam_adr) {
        this.fam_adr = fam_adr;
    }
    @JsonIgnore
    public String getFam_adr_zipecd() {
        return fam_adr_zipecd;
    }
    @JsonIgnore
    public void setFam_adr_zipecd(String fam_adr_zipecd) {
        this.fam_adr_zipecd = fam_adr_zipecd;
    }
    @JsonIgnore
    public String getEmail_adr() {
        return email_adr;
    }
    @JsonIgnore
    public void setEmail_adr(String email_adr) {
        this.email_adr = email_adr;
    }
    @JsonIgnore
    public String getEmgr_ctcpsn() {
        return emgr_ctcpsn;
    }
    @JsonIgnore
    public void setEmgr_ctcpsn(String emgr_ctcpsn) {
        this.emgr_ctcpsn = emgr_ctcpsn;
    }
    @JsonIgnore
    public String getPrsz_inf_1() {
        return prsz_inf_1;
    }
    @JsonIgnore
    public void setPrsz_inf_1(String prsz_inf_1) {
        this.prsz_inf_1 = prsz_inf_1;
    }
    @JsonIgnore
    public String getEmgr_ctc_tel() {
        return emgr_ctc_tel;
    }
    @JsonIgnore
    public void setEmgr_ctc_tel(String emgr_ctc_tel) {
        this.emgr_ctc_tel = emgr_ctc_tel;
    }
    @JsonIgnore
    public String getFix_telno() {
        return fix_telno;
    }
    @JsonIgnore
    public void setFix_telno(String fix_telno) {
        this.fix_telno = fix_telno;
    }
    @JsonIgnore
    public String getWrk_unit_nm() {
        return wrk_unit_nm;
    }
    @JsonIgnore
    public void setWrk_unit_nm(String wrk_unit_nm) {
        this.wrk_unit_nm = wrk_unit_nm;
    }
    @JsonIgnore
    public String getUnit_adr() {
        return unit_adr;
    }
    @JsonIgnore
    public void setUnit_adr(String unit_adr) {
        this.unit_adr = unit_adr;
    }
    @JsonIgnore
    public String getZipecd() {
        return zipecd;
    }
    @JsonIgnore
    public void setZipecd(String zipecd) {
        this.zipecd = zipecd;
    }
    @JsonIgnore
    public String getUnit_tel() {
        return unit_tel;
    }
    @JsonIgnore
    public void setUnit_tel(String unit_tel) {
        this.unit_tel = unit_tel;
    }
    @JsonIgnore
    public String getMblph_no() {
        return mblph_no;
    }
    @JsonIgnore
    public void setMblph_no(String mblph_no) {
        this.mblph_no = mblph_no;
    }
    @JsonIgnore
    public String getCrdt_efdt() {
        return crdt_efdt;
    }
    @JsonIgnore
    public void setCrdt_efdt(String crdt_efdt) {
        this.crdt_efdt = crdt_efdt;
    }
    @JsonIgnore
    public String getCrdt_exdat() {
        return crdt_exdat;
    }
    @JsonIgnore
    public void setCrdt_exdat(String crdt_exdat) {
        this.crdt_exdat = crdt_exdat;
    }
    @JsonIgnore
    public String getEbnk_lnd_pswd() {
        return ebnk_lnd_pswd;
    }
    @JsonIgnore
    public void setEbnk_lnd_pswd(String ebnk_lnd_pswd) {
        this.ebnk_lnd_pswd = ebnk_lnd_pswd;
    }
    @JsonIgnore
    public String getPltfrm_nm() {
        return pltfrm_nm;
    }
    @JsonIgnore
    public void setPltfrm_nm(String pltfrm_nm) {
        this.pltfrm_nm = pltfrm_nm;
    }
    @JsonIgnore
    public String getPlat_flow_no() {
        return plat_flow_no;
    }
    @JsonIgnore
    public void setPlat_flow_no(String plat_flow_no) {
        this.plat_flow_no = plat_flow_no;
    }
    @JsonIgnore
    public String getMbsh_no() {
        return mbsh_no;
    }
    @JsonIgnore
    public void setMbsh_no(String mbsh_no) {
        this.mbsh_no = mbsh_no;
    }
    @JsonIgnore
    public String getRsrv_fld_1() {
        return rsrv_fld_1;
    }
    @JsonIgnore
    public void setRsrv_fld_1(String rsrv_fld_1) {
        this.rsrv_fld_1 = rsrv_fld_1;
    }
    @JsonIgnore
    public String getRsrv_fld_2() {
        return rsrv_fld_2;
    }
    @JsonIgnore
    public void setRsrv_fld_2(String rsrv_fld_2) {
        this.rsrv_fld_2 = rsrv_fld_2;
    }
    @JsonIgnore
    public String getRsrv_fld_3() {
        return rsrv_fld_3;
    }
    @JsonIgnore
    public void setRsrv_fld_3(String rsrv_fld_3) {
        this.rsrv_fld_3 = rsrv_fld_3;
    }
    @JsonIgnore
    public String getRsrv_fld_4() {
        return rsrv_fld_4;
    }
    @JsonIgnore
    public void setRsrv_fld_4(String rsrv_fld_4) {
        this.rsrv_fld_4 = rsrv_fld_4;
    }
    @JsonIgnore
    public String getV_txcode() {
        return v_txcode;
    }
    @JsonIgnore
    public void setV_txcode(String v_txcode) {
        this.v_txcode = v_txcode;
    }
    @JsonIgnore
    public String getScene() {
        return scene;
    }
    @JsonIgnore
    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getCard_banks_relation() {
        return card_banks_relation;
    }

    public void setCard_banks_relation(String card_banks_relation) {
        this.card_banks_relation = card_banks_relation;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
//        sb.append("mobileNumber=");
//        sb.append(RelayCodeUtils.encode(this.getMobileNumber()));
//        sb.append("&smsCode=")
//        sb.append(RelayCodeUtils.encode(this.getSmsCode()));
        sb.append("SMS=");
        sb.append(RelayCodeUtils.encode(this.getSms()));
        sb.append("&Crdt_TpCd=");
        sb.append(RelayCodeUtils.encode(this.getCrdt_tpcd()));
        sb.append("&Crdt_No=");
        sb.append(RelayCodeUtils.encode(this.getCrdt_no()));
        sb.append("&Cst_Nm=");
        sb.append(RelayCodeUtils.encode(this.getCst_nm()));
        sb.append("&Hdl_InsID=");
        sb.append(RelayCodeUtils.encode(this.getHdl_insid()));
        sb.append("&Rltv_AccNo=");
        sb.append(RelayCodeUtils.encode(this.getRltv_accno()));
        sb.append("&Rsrv_TpCd=");
        sb.append(RelayCodeUtils.encode(this.getRsrv_tpcd()));
        sb.append("&DpBkBkCD=");
        sb.append(RelayCodeUtils.encode(this.getDpbkbkcd()));
        sb.append("&Gnd_Cd=");
        sb.append(RelayCodeUtils.encode(this.getGnd_cd()));
        sb.append("&Ctc_Adr=");
        sb.append(RelayCodeUtils.encode(this.getCtc_adr()));
        sb.append("&CstMgr_ID=");
        sb.append(RelayCodeUtils.encode(this.getCst_nm()));
        sb.append("&Brth_Dt=");
        sb.append(RelayCodeUtils.encode(this.getBrth_dt()));
        sb.append("&Ocp_Cd=");
        sb.append(RelayCodeUtils.encode(this.getOcp_cd()));
        sb.append("&Fam_Adr=");
        sb.append(RelayCodeUtils.encode(this.getFam_adr()));
        sb.append("&Fam_Adr_ZipECD=");
        sb.append(RelayCodeUtils.encode(this.getFam_adr_zipecd()));
        sb.append("&Email_Adr=");
        sb.append(RelayCodeUtils.encode(this.getEmail_adr()));
        sb.append("&Emgr_CtcPsn=");
        sb.append(RelayCodeUtils.encode(this.getEmgr_ctcpsn()));
        sb.append("&Prsz_Inf_1=");
        sb.append(RelayCodeUtils.encode(this.getPrsz_inf_1()));
        sb.append("&Emgr_Ctc_Tel=");
        sb.append(RelayCodeUtils.encode(this.getEmgr_ctc_tel()));
        sb.append("&Fix_TelNo=");
        sb.append(RelayCodeUtils.encode(this.getFix_telno()));
        sb.append("&Wrk_Unit_Nm=");
        sb.append(RelayCodeUtils.encode(this.getWrk_unit_nm()));
        sb.append("&Unit_Adr=");
        sb.append(RelayCodeUtils.encode(this.getUnit_adr()));
        sb.append("&ZipECD=");
        sb.append(RelayCodeUtils.encode(this.getZipecd()));
        sb.append("&Unit_Tel=");
        sb.append(RelayCodeUtils.encode(this.getUnit_tel()));
        sb.append("&MblPh_No=");
        sb.append(RelayCodeUtils.encode(this.getMblph_no()));
        sb.append("&Crdt_EfDt=");
        sb.append(RelayCodeUtils.encode(this.getCrdt_efdt()));
        sb.append("&Crdt_ExDat=");
        sb.append(RelayCodeUtils.encode(this.getCrdt_exdat()));
        sb.append("&EBNK_LND_PSWD=");
        sb.append(RelayCodeUtils.encode(this.getEbnk_lnd_pswd()));
        sb.append("&Pltfrm_Nm=");
        sb.append(RelayCodeUtils.encode(this.getPltfrm_nm()));
        sb.append("&PLAT_FLOW_NO=");
        sb.append(RelayCodeUtils.encode(this.getPlat_flow_no()));
        sb.append("&Mbsh_No=");
        sb.append(RelayCodeUtils.encode(this.getMbsh_no()));
        sb.append("&Rsrv_Fld_1=");
        sb.append(RelayCodeUtils.encode(this.getRsrv_fld_1()));
        sb.append("&Rsrv_Fld_2=");
        sb.append(RelayCodeUtils.encode(this.getRsrv_fld_2()));
        sb.append("&Rsrv_Fld_3=");
        sb.append(RelayCodeUtils.encode(this.getRsrv_fld_3()));
        sb.append("&Rsrv_Fld_4=");
        sb.append(RelayCodeUtils.encode(this.getRsrv_fld_4()));
        sb.append("&bankType=");
        sb.append(RelayCodeUtils.encode(this.getBankType()));
//        sb.append("&v_txcode=");
//        sb.append(RelayCodeUtils.encode(this.getV_txcode()));
//        sb.append("&scene=");
//        sb.append(RelayCodeUtils.encode(this.getScene()));
//        sb.append("&sign=");
//        sb.append(RelayCodeUtils.encode(this.getSign()));
//        sb.append("&nonce=");
//        sb.append(RelayCodeUtils.encode(this.getNonce()));
        return sb.toString();
    }
}
