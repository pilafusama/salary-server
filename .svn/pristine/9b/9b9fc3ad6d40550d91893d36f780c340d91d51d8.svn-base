package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;

/**
 * Created by hxyh on 2018/8/21.
 */
public class OpenSalaryAccountRes extends BankProxyRelayResponseMsg {

    private static final String DBCRD_CARDNO = "dbcrd_cardno";
    private static final String AR_GEN_DT = "ar_gen_dt";
    private static final String DTBS_TMS = "dtbs_tms";
    private static final String CST_NM = "cst_nm";
    private static final String RLTV_ACCNO = "rltv_accno";
    private static final String DPBKBKCD = "dpbkbkcd";

    private String dbcrd_cardno;
    private String ar_gen_dt;
    private String dtbs_tms;
    private String cst_nm;
    private String rltv_accno;
    private String dpbkbkcd;

    public OpenSalaryAccountRes(int result, String resInfo, String bankResult, String bankResInfo) {
        super(result, resInfo, bankResult, bankResInfo);
    }

    public void setResponse(String dbcrd_cardno,String ar_gen_dt,String dtbs_tms,String cst_nm,String rltv_accno,String dpbkbkcd){
        setDbcrd_cardno(dbcrd_cardno);
        setAr_gen_dt(ar_gen_dt);
        setDtbs_tms(dtbs_tms);
        setCst_nm(cst_nm);
        setRltv_accno(rltv_accno);
        setDpbkbkcd(dpbkbkcd);
    }

    public String getDbcrd_cardno() {
        this.dbcrd_cardno = this.get(DBCRD_CARDNO);
        return dbcrd_cardno;
    }

    public void setDbcrd_cardno(String dbcrd_cardno) {
        this.put(DBCRD_CARDNO,dbcrd_cardno);
        this.dbcrd_cardno = dbcrd_cardno;
    }

    public String getAr_gen_dt() {
        this.ar_gen_dt = this.get(AR_GEN_DT);
        return ar_gen_dt;
    }

    public void setAr_gen_dt(String ar_gen_dt) {
        this.put(AR_GEN_DT,ar_gen_dt);
        this.ar_gen_dt = ar_gen_dt;
    }

    public String getDtbs_tms() {
        this.dtbs_tms = this.get(DTBS_TMS);
        return dtbs_tms;
    }

    public void setDtbs_tms(String dtbs_tms) {
        this.put(DTBS_TMS,dtbs_tms);
        this.dtbs_tms = dtbs_tms;
    }

    public String getCst_nm() {
        this.cst_nm = this.get(CST_NM);
        return cst_nm;
    }

    public void setCst_nm(String cst_nm) {
        this.put(CST_NM,cst_nm);
        this.cst_nm = cst_nm;
    }

    public String getRltv_accno() {
        this.rltv_accno = this.get(RLTV_ACCNO);
        return rltv_accno;
    }

    public void setRltv_accno(String rltv_accno) {
        this.put(RLTV_ACCNO,rltv_accno);
        this.rltv_accno = rltv_accno;
    }

    public String getDpbkbkcd() {
        this.dpbkbkcd = this.get(DPBKBKCD);
        return dpbkbkcd;
    }

    public void setDpbkbkcd(String dpbkbkcd) {
        this.put(DPBKBKCD,dpbkbkcd);
        this.dpbkbkcd = dpbkbkcd;
    }
}
