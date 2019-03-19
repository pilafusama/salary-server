package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ReuseSalaryAccountReqParam extends FrontEndReqBase {

    @NotBlank(message = "SMS不能为空")
    @Length(min = 6,max = 6)
    @JsonProperty(value="SMS", required=true)
    private String sms;

    @NotBlank(message = "func_code不能为空")
    @Length(max = 1)
    @JsonProperty(value="func_code", required=true)
    private String func_code;

    @NotBlank(message = "chnl_cust_no不能为空")
    @Length(max = 20)
    @JsonProperty(value="chnl_cust_no", required=true)
    private String chnl_cust_no;

    @NotBlank(message = "Ebnk_Sign_Accno不能为空")
    @Length(max = 32)
    @JsonProperty(value="Ebnk_Sign_Accno", required=true)
    private String ebnk_sign_accno;


    @NotBlank(message = "Pltfrm_Nm不能为空")
    @Length(max = 600)
    @JsonProperty(value="Pltfrm_Nm", required=true)
    private String pltfrm_nm;

    @NotBlank(message = "Hdl_InsID不能为空")
    @Length(max = 9)
    @JsonProperty(value="Hdl_InsID", required=true)
    private String hdl_insid;

    @NotBlank(message = "Sign_Dt不能为空")
    @Length(max = 8)
    @JsonProperty(value="Sign_Dt", required=true)
    private String sign_dt;

    @NotBlank(message = "MblPh_No不能为空")
    @Length(max = 20)
    @JsonProperty(value="MblPh_No", required=true)
    private String mblph_no;

    @Length(max = 600)
    @JsonProperty(value="Mbsh_No", required=false)
    private String mbsh_no;

    @Length(max = 1)
    @JsonProperty(value="Rsrv_TpCd", required=false)
    private String rsrv_tpcd;

    @JsonIgnore
    public String getSms() {
        return sms;
    }
    @JsonIgnore
    public void setSms(String sms) {
        this.sms = sms;
    }
    @JsonIgnore
    public String getFunc_code() {
        return func_code;
    }
    @JsonIgnore
    public void setFunc_code(String func_code) {
        this.func_code = func_code;
    }
    @JsonIgnore
    public String getChnl_cust_no() {
        return chnl_cust_no;
    }
    @JsonIgnore
    public void setChnl_cust_no(String chnl_cust_no) {
        this.chnl_cust_no = chnl_cust_no;
    }
    @JsonIgnore
    public String getEbnk_sign_accno() {
        return ebnk_sign_accno;
    }
    @JsonIgnore
    public void setEbnk_sign_accno(String ebnk_sign_accno) {
        this.ebnk_sign_accno = ebnk_sign_accno;
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
    public String getHdl_insid() {
        return hdl_insid;
    }
    @JsonIgnore
    public void setHdl_insid(String hdl_insid) {
        this.hdl_insid = hdl_insid;
    }
    @JsonIgnore
    public String getSign_dt() {
        return sign_dt;
    }
    @JsonIgnore
    public void setSign_dt(String sign_dt) {
        this.sign_dt = sign_dt;
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
    public String getMbsh_no() {
        return mbsh_no;
    }
    @JsonIgnore
    public void setMbsh_no(String mbsh_no) {
        this.mbsh_no = mbsh_no;
    }
    @JsonIgnore
    public String getRsrv_tpcd() {
        return rsrv_tpcd;
    }
    @JsonIgnore
    public void setRsrv_tpcd(String rsrv_tpcd) {
        this.rsrv_tpcd = rsrv_tpcd;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("SMS=");
        sb.append(RelayCodeUtils.encode(this.getSms()));
        sb.append("&func_code=");
        sb.append(RelayCodeUtils.encode(this.getFunc_code()));
        sb.append("&chnl_cust_no=");
        sb.append(RelayCodeUtils.encode(this.getChnl_cust_no()));
        sb.append("Ebnk_Sign_Accno=");
        sb.append(RelayCodeUtils.encode(this.getEbnk_sign_accno()));
        sb.append("Pltfrm_Nm=");
        sb.append(RelayCodeUtils.encode(this.getPltfrm_nm()));
        sb.append("&Hdl_InsID=");
        sb.append(RelayCodeUtils.encode(this.getHdl_insid()));
        sb.append("&Sign_Dt=");
        sb.append(RelayCodeUtils.encode(this.getSign_dt()));
        sb.append("&MblPh_No=");
        sb.append(RelayCodeUtils.encode(this.getMblph_no()));
        sb.append("&Mbsh_No=");
        sb.append(RelayCodeUtils.encode(this.getMbsh_no()));
        sb.append("&bankType=");
        sb.append(RelayCodeUtils.encode(this.getBankType()));
        sb.append("&sign=");
        sb.append(RelayCodeUtils.encode(this.getSign()));
        sb.append("&nonce=");
        sb.append(RelayCodeUtils.encode(this.getNonce()));
        return sb.toString();
    }
}
