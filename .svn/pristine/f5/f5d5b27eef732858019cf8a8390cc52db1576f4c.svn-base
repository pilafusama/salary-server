package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class VerifyUserReqParam extends FrontEndReqBase {

    @NotBlank(message = "cst_nm不能为空")
    @Length(max = 240)
    @JsonProperty(required=true)
    private String cst_nm;

    @NotBlank(message = "crdt_tpcd不能为空")
    @Length(min = 4, max = 4)
    @JsonProperty(required=true)
    private String crdt_tpcd;

    @NotBlank(message = "crdt_no不能为空")
    @Length(max = 120)
    @JsonProperty(required=true)
    private String crdt_no;

    @Length(max = 10)
    @JsonProperty(required=true)
    private String gnd_cd;

    @Length(max = 10)
    @JsonProperty(required=true)
    private String ethnct_cd;

    @Length(max = 8)
    @JsonProperty(required=true)
    private String brth_dt;

    @Length(max = 8)
    @JsonProperty(required=true)
    private String avldt_dt;

    @Length(max = 8)
    @JsonProperty(required=true)
    private String avldt_eddt;

    @Length(max = 600)
    @JsonProperty(required=true)
    private String inst_chn_fullnm;

    @Length(max = 240)
    @JsonProperty(required=true)
    private String dtl_adr;

    @Length(max = 9)
    @JsonProperty(required=true)
    private String sign_orcd;

    @Length(max = 600)
    @JsonProperty(required=true)
    private String file_nm;

    @Length(max = 240)
    @JsonProperty(required=true)
    private String upload_flnm;

    @JsonProperty(required=false)
    private String base64_ecrp_txn_inf;

    @JsonProperty(required=true)
    private String corpid;
    @JsonProperty(required=true)
    private String userid;

    @JsonProperty(required=true)
    private String password;

    @JsonProperty(required=true)
    private String vectorKey;

    @JsonIgnore
    public String getVectorKey() {
        return vectorKey;
    }

    @JsonIgnore
    public void setVectorKey(String vectorKey) {
        this.vectorKey = vectorKey;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
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
    public String getGnd_cd() {
        return gnd_cd;
    }

    @JsonIgnore
    public void setGnd_cd(String gnd_cd) {
        this.gnd_cd = gnd_cd;
    }

    @JsonIgnore
    public String getEthnct_cd() {
        return ethnct_cd;
    }

    @JsonIgnore
    public void setEthnct_cd(String ethnct_cd) {
        this.ethnct_cd = ethnct_cd;
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
    public String getAvldt_dt() {
        return avldt_dt;
    }

    @JsonIgnore
    public void setAvldt_dt(String avldt_dt) {
        this.avldt_dt = avldt_dt;
    }

    @JsonIgnore
    public String getAvldt_eddt() {
        return avldt_eddt;
    }

    @JsonIgnore
    public void setAvldt_eddt(String avldt_eddt) {
        this.avldt_eddt = avldt_eddt;
    }

    @JsonIgnore
    public String getInst_chn_fullnm() {
        return inst_chn_fullnm;
    }

    @JsonIgnore
    public void setInst_chn_fullnm(String inst_chn_fullnm) {
        this.inst_chn_fullnm = inst_chn_fullnm;
    }

    @JsonIgnore
    public String getDtl_adr() {
        return dtl_adr;
    }

    @JsonIgnore
    public void setDtl_adr(String dtl_adr) {
        this.dtl_adr = dtl_adr;
    }

    @JsonIgnore
    public String getSign_orcd() {
        return sign_orcd;
    }

    @JsonIgnore
    public void setSign_orcd(String sign_orcd) {
        this.sign_orcd = sign_orcd;
    }

    @JsonIgnore
    public String getFile_nm() {
        return file_nm;
    }

    @JsonIgnore
    public void setFile_nm(String file_nm) {
        this.file_nm = file_nm;
    }

    @JsonIgnore
    public String getUpload_flnm() {
        return upload_flnm;
    }

    @JsonIgnore
    public void setUpload_flnm(String upload_flnm) {
        this.upload_flnm = upload_flnm;
    }

    @JsonIgnore
    public String getBase64_ecrp_txn_inf() {
        return base64_ecrp_txn_inf;
    }

    @JsonIgnore
    public void setBase64_ecrp_txn_inf(String base64_ecrp_txn_inf) {
        this.base64_ecrp_txn_inf = base64_ecrp_txn_inf;
    }

    @JsonIgnore
    public String getCorpid() {
        return corpid;
    }
    @JsonIgnore
    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Cst_Nm=");
        sb.append(RelayCodeUtils.encode(this.getCst_nm()));
        sb.append("&Crdt_TpCd=");
        sb.append(RelayCodeUtils.encode(this.getCrdt_tpcd()));
        sb.append("&Crdt_No=");
        sb.append(RelayCodeUtils.encode(this.getCrdt_no()));
        sb.append("Gnd_Cd=");
        sb.append(RelayCodeUtils.encode(this.getGnd_cd()));
        sb.append("&Ethnct_Cd=");
        sb.append(RelayCodeUtils.encode(this.getEthnct_cd()));
        sb.append("&Brth_Dt=");
        sb.append(RelayCodeUtils.encode(this.getBrth_dt()));
        sb.append("AvlDt_Dt=");
        sb.append(RelayCodeUtils.encode(this.getAvldt_dt()));
        sb.append("&AvlDt_EdDt=");
        sb.append(RelayCodeUtils.encode(this.getAvldt_eddt()));
        sb.append("&Inst_Chn_FullNm=");
        sb.append(RelayCodeUtils.encode(this.getInst_chn_fullnm()));
        sb.append("Dtl_Adr=");
        sb.append(RelayCodeUtils.encode(this.getDtl_adr()));
        sb.append("&Sign_OrCd=");
        sb.append(RelayCodeUtils.encode(this.getSign_orcd()));
        sb.append("&File_Nm=");
        sb.append(RelayCodeUtils.encode(this.getFile_nm()));
        sb.append("&Upload_FlNm=");
        sb.append(RelayCodeUtils.encode(this.getUpload_flnm()));
        sb.append("&base64_Ecrp_Txn_Inf=");
        sb.append(RelayCodeUtils.encode(this.getBase64_ecrp_txn_inf()));
        sb.append("&corpid=");
        sb.append(RelayCodeUtils.encode(this.getCorpid()));
        sb.append("&userid=");
        sb.append(RelayCodeUtils.encode(this.getUserid()));
        sb.append("&bankType=");
        sb.append(RelayCodeUtils.encode(this.getBankType()));
        sb.append("&sign=");
        sb.append(RelayCodeUtils.encode(this.getSign()));
        sb.append("&nonce=");
        sb.append(RelayCodeUtils.encode(this.getNonce()));
        return sb.toString();
    }
}
