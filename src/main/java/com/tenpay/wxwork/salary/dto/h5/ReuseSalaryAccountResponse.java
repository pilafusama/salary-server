package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.presvr.sender.bean.ReuseSalaryAccountRes;

public class ReuseSalaryAccountResponse  extends FrontEndResponse {
    @JsonProperty(value="AR_Gen_Dt")
    private String ar_gen_dt;
    @JsonProperty(value="Dtbs_Tms")
    private String dtbs_tms;
    @JsonProperty("Cst_Nm")
    private String cst_nm;
    @JsonProperty("Rltv_AccNo")
    private String rltv_accno;
    @JsonProperty("DpBkBkCD")
    private String dpbkbkcd;
    @JsonProperty("DbCrd_CardNo")
    private String dbcrd_cardno;//绑定的E账户账号

    @JsonProperty("bank_name")
    private String bank_name;//银行名称

    @JsonProperty("bank_name_two")
    private String bank_name_two;//二类户银行名称

    public ReuseSalaryAccountResponse(ReuseSalaryAccountRes res,String dbcrd_cardno,String bankName,String bankName2) {
        super();

        this.ar_gen_dt = res.getAr_gen_dt();
        this.dtbs_tms = res.getDtbs_tms();
        this.cst_nm = res.getCst_nm();
        this.rltv_accno = res.getRltv_accno();
        this.dpbkbkcd = res.getDpbkbkcd();
        this.dbcrd_cardno = dbcrd_cardno;
        this.bank_name = bankName;
        this.bank_name_two = bankName2;
    }

    public String getAr_gen_dt() {
        return ar_gen_dt;
    }

    public void setAr_gen_dt(String ar_gen_dt) {
        this.ar_gen_dt = ar_gen_dt;
    }

    public String getDtbs_tms() {
        return dtbs_tms;
    }

    public void setDtbs_tms(String dtbs_tms) {
        this.dtbs_tms = dtbs_tms;
    }

    public String getCst_nm() {
        return cst_nm;
    }

    public void setCst_nm(String cst_nm) {
        this.cst_nm = cst_nm;
    }

    public String getRltv_accno() {
        return rltv_accno;
    }

    public void setRltv_accno(String rltv_accno) {
        this.rltv_accno = rltv_accno;
    }

    public String getDpbkbkcd() {
        return dpbkbkcd;
    }

    public void setDpbkbkcd(String dpbkbkcd) {
        this.dpbkbkcd = dpbkbkcd;
    }

    public String getBank_name_two() {
        return bank_name_two;
    }

    public void setBank_name_two(String bank_name_two) {
        this.bank_name_two = bank_name_two;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getDbcrd_cardno() {
        return dbcrd_cardno;
    }

    public void setDbcrd_cardno(String dbcrd_cardno) {
        this.dbcrd_cardno = dbcrd_cardno;
    }
}
