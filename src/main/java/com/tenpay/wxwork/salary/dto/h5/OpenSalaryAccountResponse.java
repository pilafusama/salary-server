package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

/**
 * Created by hxyh on 2018/8/21.
 */
public class OpenSalaryAccountResponse extends FrontEndResponse {

    @JsonProperty("DbCrd_CardNo")
    private String dbcrd_cardno;//新开立的E账户账号

    @JsonProperty("AR_Gen_Dt")
    private String ar_gen_dt;//开户日期

    @JsonProperty("Dtbs_Tms")
    private String dtbs_tms;//开户成功时间戳

    @JsonProperty("Cst_Nm")
    private String cst_nm;//持卡人姓名

    @JsonProperty("Rltv_AccNo")
    private String rltv_accno;//开立E账户时绑定的实体账户

    @JsonProperty("DpBkBkCD")
    private String dpbkbkcd;//绑定实体账户的开户行联行号

    @JsonProperty("bank_name")
    private String bank_name;//银行名称

    @JsonProperty("bank_name_two")
    private String bank_name_two;//二类户银行名称

    @JsonProperty("stateFlag")
    private String stateFlag;


    public OpenSalaryAccountResponse(String retCode,String errMsg,String stateFlag,String rltv_accno,String bank_name) {
        super(retCode,errMsg);
        this.stateFlag = stateFlag;
        this.rltv_accno = rltv_accno;
        this.bank_name = bank_name;
    }

    public OpenSalaryAccountResponse(String dbcrd_cardno, String ar_gen_dt, String dtbs_tms, String cst_nm, String rltv_accno, String dpbkbkcd , String bankName, String bankName2){
        this.dbcrd_cardno = dbcrd_cardno;
        this.ar_gen_dt = ar_gen_dt;
        this.dtbs_tms = dtbs_tms;
        this.cst_nm = cst_nm ;
        this.rltv_accno = rltv_accno ;
        this.dpbkbkcd = dpbkbkcd ;
        this.bank_name = bankName;
        this.bank_name_two = bankName2;
    }

    public String getDbcrd_cardno() {
        return dbcrd_cardno;
    }

    public void setDbcrd_cardno(String dbcrd_cardno) {
        this.dbcrd_cardno = dbcrd_cardno;
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

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_name_two() {
        return bank_name_two;
    }

    public void setBank_name_two(String bank_name_two) {
        this.bank_name_two = bank_name_two;
    }

    public String getStateFlag() {
        return stateFlag;
    }

    public void setStateFlag(String stateFlag) {
        this.stateFlag = stateFlag;
    }
}
