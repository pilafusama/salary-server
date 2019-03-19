package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/19
 */
public class QueryEBindRes extends BankProxyRelayResponseMsg {

    private static final String RETCODE_KEY = "retcode";
    private static final String ERRMSG_KEY = "errmsg";

    private static final String EBNK_SIGN_ACCNO = "Ebnk_Sign_Accno";
    private static final String COPLF_ID = "CoPlf_ID";

    private static final String PLTFRM_NM = "Pltfrm_Nm" ;
    private static final String HDL_INSID = "Hdl_InsID" ;
    private static final String SIGN_DT = "Sign_Dt" ;
    private static final String MBLPH_NO = "MblPh_No" ;
    private static final String MBSH_NO = "Mbsh_No" ;
    private static final String CRDT_TPCD = "Crdt_TpCd" ;
    private static final String CRDT_NO = "Crdt_No" ;
    private static final String CST_NM = "Cst_Nm" ;
    private static final String RLTV_ACCNO = "Rltv_AccNo" ;
    private static final String RSRV_TPCD = "Rsrv_TpCd" ;
    private static final String DPBKBKCD = "DpBkBkCD" ;
    private static final String LV1_INSID = "Lv1_InsID" ;
    private static final String LVl2_INSID = "Lvl2_InsID" ;

    public QueryEBindRes(int result, String resInfo, String bankResult, String bankResInfo) {
        super(result, resInfo, bankResult, bankResInfo);
    }

    private String Ebnk_Sign_Accno;//E账号
    private String CoPlf_ID; // 平台编号
    private String Pltfrm_Nm; //平台名称
    private String Hdl_InsID;  // 所属分行
    private String Sign_Dt; //绑定日期
    private String MblPh_No; //绑定手机号
    private String Mbsh_No; //第三方APP用户ID
    private String Crdt_TpCd;    //证件类型代码
    private String Crdt_No; //证件号码
    private String Cst_Nm; // 客户名称
    private String Rltv_AccNo;//关联账号
    private String Rsrv_TpCd;//备用类型代码

    private String DpBkBkCD;//开户行联行号
    private String Lvl2_InsID;//二级机构编号
    private String Lv1_InsID;//一级机构编号
    //业务结果状态码
    private String retcode;
    //结果说明
    private String errmsg;


    public void setResponse(String Ebnk_Sign_Accno,String CoPlf_ID,String Pltfrm_Nm,String Hdl_InsID,String Sign_Dt,String MblPh_No,
                            String Mbsh_No,String Crdt_TpCd,String Crdt_No,String Cst_Nm,String Rltv_AccNo,String Rsrv_TpCd,
                            String DpBkBkCD,String Lv1_InsID,String Lvl2_InsID,String retcode,String errMsg){
        setEbnk_Sign_Accno(Ebnk_Sign_Accno);
        setCoPlf_ID(CoPlf_ID);
        setPltfrm_Nm(Pltfrm_Nm);
        setHdl_InsID(Hdl_InsID);
        setSign_Dt(Sign_Dt);
        setMblPh_No(MblPh_No);
        setMbsh_No(Mbsh_No);
        setCrdt_TpCd(Crdt_TpCd);
        setCrdt_No(Crdt_No);
        setCst_Nm(Cst_Nm);
        setRltv_AccNo(Rltv_AccNo);
        setRsrv_TpCd(Rsrv_TpCd);
        setDpBkBkCD(DpBkBkCD);
        setLv1_InsID(Lv1_InsID);
        setLvl2_InsID(Lvl2_InsID);
        setRetcode(retcode);
        setErrmsg(errMsg);
    }



    public String getEbnk_Sign_Accno() {
        this.Ebnk_Sign_Accno = this.get(EBNK_SIGN_ACCNO);
        return Ebnk_Sign_Accno;
    }

    public void setEbnk_Sign_Accno(String Ebnk_Sign_Accno) {
        this.put(EBNK_SIGN_ACCNO, Ebnk_Sign_Accno);
        this.Ebnk_Sign_Accno = Ebnk_Sign_Accno;
    }

    public String getCoPlf_ID() {
        this.CoPlf_ID = this.get(COPLF_ID);
        return CoPlf_ID;
    }

    public void setCoPlf_ID(String CoPlf_ID) {
        this.put(COPLF_ID, CoPlf_ID);
        this.CoPlf_ID = CoPlf_ID;
    }
    public String getRetcode() {
        this.retcode = this.get(RETCODE_KEY);
        return retcode;
    }
    public void setRetcode(String retcode) {
        this.put(RETCODE_KEY,retcode);
        this.retcode = retcode;
    }
    public String getErrmsg() {
        this.errmsg = this.get(ERRMSG_KEY);
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.put(ERRMSG_KEY, errmsg);
        this.errmsg = errmsg;
    }

    public String getPltfrm_Nm() {
        this.Pltfrm_Nm = this.get(PLTFRM_NM);
        return Pltfrm_Nm;
    }

    public void setPltfrm_Nm(String Pltfrm_Nm) {
        this.put(PLTFRM_NM, Pltfrm_Nm);
        this.Pltfrm_Nm = Pltfrm_Nm;
    }

    public String getHdl_InsID() {
        this.Hdl_InsID = this.get(HDL_INSID);
        return Hdl_InsID;
    }

    public void setHdl_InsID(String Hdl_InsID) {
        this.put(HDL_INSID, Hdl_InsID);
        this.Hdl_InsID = Hdl_InsID;
    }

    public String getSign_Dt() {
        this.Sign_Dt = this.get(SIGN_DT);
        return Sign_Dt;
    }

    public void setSign_Dt(String Sign_Dt) {
        this.put(SIGN_DT, Sign_Dt);
        this.Sign_Dt = Sign_Dt;
    }

    public String getMblPh_No() {
        this.MblPh_No = this.get(MBLPH_NO);
        return MblPh_No;
    }

    public void setMblPh_No(String MblPh_No) {
        this.put(MBLPH_NO, MblPh_No);
        this.MblPh_No = MblPh_No;
    }

    public String getMbsh_No() {
        this.Mbsh_No = this.get(MBSH_NO);
        return Mbsh_No;
    }

    public void setMbsh_No(String Mbsh_No) {
        this.put(MBSH_NO, Mbsh_No);
        this.Mbsh_No = Mbsh_No;
    }

    public String getCrdt_TpCd() {
        this.Crdt_TpCd = this.get(CRDT_TPCD);
        return Crdt_TpCd;
    }

    public void setCrdt_TpCd(String Crdt_TpCd) {
        this.put(CRDT_TPCD, Crdt_TpCd);
        this.Crdt_TpCd = Crdt_TpCd;
    }

    public String getCrdt_No() {
        this.Crdt_No = this.get(CRDT_NO);
        return Crdt_No;
    }

    public void setCrdt_No(String Crdt_No) {
        this.put(CRDT_NO, Crdt_No);
        this.Crdt_No = Crdt_No;
    }

    public String getCst_Nm() {
        this.Cst_Nm = this.get(CST_NM);
        return Cst_Nm;
    }

    public void setCst_Nm(String Cst_Nm) {
        this.put(CST_NM, Cst_Nm);
        this.Cst_Nm = Cst_Nm;
    }

    public String getRltv_AccNo() {
        this.Rltv_AccNo = this.get(RLTV_ACCNO);
        return Rltv_AccNo;
    }

    public void setRltv_AccNo(String Rltv_AccNo) {
        this.put(RLTV_ACCNO, Rltv_AccNo);
        this.Rltv_AccNo = Rltv_AccNo;
    }

    public String getRsrv_TpCd() {
        this.Rsrv_TpCd = this.get(RSRV_TPCD);
        return Rsrv_TpCd;
    }

    public void setRsrv_TpCd(String Rsrv_TpCd) {
        this.put(RSRV_TPCD, Rsrv_TpCd);
        this.Rsrv_TpCd = Rsrv_TpCd;
    }

    public String getDpBkBkCD() {
        this.DpBkBkCD = this.get(DPBKBKCD);
        return DpBkBkCD;
    }

    public void setDpBkBkCD(String DpBkBkCD) {
        this.put(DPBKBKCD, DpBkBkCD);
        this.DpBkBkCD = DpBkBkCD;
    }
    public String getLvl2_InsID() {
        this.Lvl2_InsID = this.get(LVl2_INSID);
        return Lvl2_InsID;
    }

    public void setLvl2_InsID(String Lvl2_InsID) {
        this.put(LVl2_INSID, Lvl2_InsID);
        this.Lvl2_InsID = Lvl2_InsID;
    }

    public String getLv1_InsID() {
        this.Lv1_InsID = this.get(LV1_INSID);
        return Lv1_InsID;
    }

    public void setLv1_InsID(String Lv1_InsID) {
        this.put(LV1_INSID, Lv1_InsID);
        this.Lv1_InsID = Lv1_InsID;
    }
}
