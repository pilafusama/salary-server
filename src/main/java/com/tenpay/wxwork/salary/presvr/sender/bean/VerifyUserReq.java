package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.VerifyUserReqParam;
import org.apache.commons.lang3.StringUtils;

public class VerifyUserReq  extends BankProxyRelayRequestMsg {

    private static final String CST_NM = "cst_nm";
    private static final String CRDT_TPCD = "crdt_tpcd";
    private static final String CRDT_NO = "crdt_no";
    private static final String GND_CD = "gnd_cd";
    private static final String ETHNCT_CD = "ethnct_cd";
    private static final String BRTH_DT = "brth_dt";
    private static final String AVLDT_DT = "avldt_dt";
    private static final String AVLDT_EDDT = "avldt_eddt";
    private static final String INST_CHN_FULLNM = "inst_chn_fullnm";
    private static final String DTL_ADR = "dtl_adr";
    private static final String SIGN_ORCD = "sign_orcd";
    private static final String FILE_NM = "file_nm";
    private static final String UPLOAD_FLNM = "upload_flnm";
    private static final String BASE64_ECRP_TXN_INF = "base64_ecrp_txn_inf";
    private static final String CORPID = "corpid";
    private static final String USERID = "userid";
    private static final String PASSWORD = "password";
    private static final String VECTOR_KEY = "vector_key";

    private String corpid;
    private String userid;
    private String cst_nm;
    private String crdt_tpcd;
    private String crdt_no;
    private String gnd_cd;
    private String ethnct_cd;
    private String brth_dt;
    private String avldt_dt;
    private String avldt_eddt;
    private String inst_chn_fullnm;
    private String dtl_adr;
    private String sign_orcd;
    private String file_nm;
    private String upload_flnm;
    private String base64_ecrp_txn_inf;
    private String txcode;
    private String password;
    private String vector_key;

    public VerifyUserReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }

    public void setRequest(VerifyUserReqParam request, String bankType){
        this.setCst_nm(request.getCst_nm());
        this.setCrdt_tpcd(request.getCrdt_tpcd());
        this.setCrdt_no(request.getCrdt_no());
        this.setGnd_cd(request.getGnd_cd());
        this.setEthnct_cd(request.getEthnct_cd());
        this.setBrth_dt(request.getBrth_dt());
        this.setAvldt_dt(request.getAvldt_dt());
        this.setAvldt_eddt(request.getAvldt_eddt());
        this.setInst_chn_fullnm(request.getInst_chn_fullnm());
        this.setDtl_adr(request.getDtl_adr());
        this.setSign_orcd(request.getSign_orcd());
        this.setFile_nm(request.getFile_nm());
        this.setUpload_flnm(request.getUpload_flnm());
        this.setBase64_ecrp_txn_inf(request.getBase64_ecrp_txn_inf());
        this.setCorpid(request.getCorpid());
        this.setUserid(request.getUserid());
        this.setPassword(request.getPassword());
        this.setVector_key(request.getVectorKey());
        this.validate();
    }

    public String getVector_key() {
        return vector_key;
    }

    public void setVector_key(String vector_key) {
        this.put(VECTOR_KEY, vector_key);
        this.vector_key = vector_key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.put(PASSWORD, password);
        this.password = password;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.put(CORPID, corpid);
        this.corpid = corpid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.put(USERID, userid);
        this.userid = userid;
    }

    public String getCst_nm() {
        return cst_nm;
    }

    public void setCst_nm(String cst_nm) {
        this.put(CST_NM,cst_nm);
        this.cst_nm = cst_nm;
    }

    public String getCrdt_tpcd() {
        return crdt_tpcd;
    }

    public void setCrdt_tpcd(String crdt_tpcd) {
        this.put(CRDT_TPCD,crdt_tpcd);
        this.crdt_tpcd = crdt_tpcd;
    }

    public String getCrdt_no() {
        return crdt_no;
    }

    public void setCrdt_no(String crdt_no) {
        this.put(CRDT_NO,crdt_no);
        this.crdt_no = crdt_no;
    }

    public String getGnd_cd() {
        return gnd_cd;
    }

    public void setGnd_cd(String gnd_cd) {
        this.put(GND_CD,gnd_cd);
        this.gnd_cd = gnd_cd;
    }

    public String getEthnct_cd() {
        return ethnct_cd;
    }

    public void setEthnct_cd(String ethnct_cd) {
        this.put(ETHNCT_CD,ethnct_cd);
        this.ethnct_cd = ethnct_cd;
    }

    public String getBrth_dt() {
        return brth_dt;
    }

    public void setBrth_dt(String brth_dt) {
        this.put(BRTH_DT,brth_dt);
        this.brth_dt = brth_dt;
    }

    public String getAvldt_dt() {
        return avldt_dt;
    }

    public void setAvldt_dt(String avldt_dt) {
        this.put(AVLDT_DT,avldt_dt);
        this.avldt_dt = avldt_dt;
    }

    public String getAvldt_eddt() {
        return avldt_eddt;
    }

    public void setAvldt_eddt(String avldt_eddt) {
        this.put(AVLDT_EDDT,avldt_eddt);
        this.avldt_eddt = avldt_eddt;
    }

    public String getInst_chn_fullnm() {
        return inst_chn_fullnm;
    }

    public void setInst_chn_fullnm(String inst_chn_fullnm) {
        this.put(INST_CHN_FULLNM,inst_chn_fullnm);
        this.inst_chn_fullnm = inst_chn_fullnm;
    }

    public String getDtl_adr() {
        return dtl_adr;
    }

    public void setDtl_adr(String dtl_adr) {
        this.put(DTL_ADR,dtl_adr);
        this.dtl_adr = dtl_adr;
    }

    public String getSign_orcd() {
        return sign_orcd;
    }

    public void setSign_orcd(String sign_orcd) {
        this.put(SIGN_ORCD,sign_orcd);
        this.sign_orcd = sign_orcd;
    }

    public String getFile_nm() {
        return file_nm;
    }

    public void setFile_nm(String file_nm) {
        this.put(FILE_NM,file_nm);
        this.file_nm = file_nm;
    }

    public String getUpload_flnm() {
        return upload_flnm;
    }

    public void setUpload_flnm(String upload_flnm) {
        this.put(UPLOAD_FLNM,upload_flnm);
        this.upload_flnm = upload_flnm;
    }

    public String getBase64_ecrp_txn_inf() {
        return base64_ecrp_txn_inf;
    }

    public void setBase64_ecrp_txn_inf(String base64_ecrp_txn_inf) {
        this.put(BASE64_ECRP_TXN_INF,base64_ecrp_txn_inf);
        this.base64_ecrp_txn_inf = base64_ecrp_txn_inf;
    }


    private void validate()
    {
        if(StringUtils.isBlank(this.getCst_nm())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Cst_Nm is empty.");
        }
        if(StringUtils.isBlank(this.getCrdt_tpcd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Crdt_TpCd is empty.");
        }
        if(StringUtils.isBlank(this.getCrdt_no())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Crdt_No is empty.");
        }
//        if(StringUtils.isBlank(this.getBase64_ecrp_txn_inf())){
//            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "base64_Ecrp_Txn_Inf is empty.");
//        }
    }
}
