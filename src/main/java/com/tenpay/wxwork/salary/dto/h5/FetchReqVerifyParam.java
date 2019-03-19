package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;


/**
 * @author : wg
 * @description :
 * @date : 2018/8/19
 */
public class FetchReqVerifyParam extends FrontEndReqBase {


    

    @NotBlank(message = "Rsrv_TpCd不能为空")
    @Length(min = 1, max = 1)
    @JsonProperty(value="Rsrv_TpCd", required=true)
    private String Rsrv_TpCd;//绑定本他行标志：0-建行；1-他行

    @NotBlank(message = "CoPlf_ID不能为空")
    @Length(min = 1, max = 40)
    @JsonProperty(value="CoPlf_ID", required=true)
    private String CoPlf_ID;//平台编号


    //银行标识
    @NotBlank(message = "BankType不能为空")
    @Length(min = 4, max = 4)
    @JsonProperty(required=true)
    private String BankType;

    @NotBlank(message = "TXCODE不能为空")
    @Length(min = 6, max = 6)
    @JsonProperty(value="TXCODE", required=true)
    private String TXCODE;//

    @NotBlank(message = "Crdt_No不能为空")
    @Length(min = 1, max = 120)
    @JsonProperty(value="Crdt_No", required=true)
    private String Crdt_No;//证件号码

    @NotBlank(message = "MblPh_No不能为空")
    @Length(min = 1, max = 15)
    @JsonProperty(value="MblPh_No", required=true)
    private String MblPh_No;//手机号

    @NotBlank(message = "Ebnk_Sign_Accno不能为空")
    @Length(min = 1)
    @JsonProperty(value="Ebnk_Sign_Accno", required=true)
    private String Ebnk_Sign_Accno;//E(客户)帐号

    @JsonIgnore
    public String getBankType() {
        return BankType;
    }

    @JsonIgnore
    public void setBankType(String bankType) {
        BankType = bankType;
    }
    @JsonIgnore
    public String getTXCODE() {
        return TXCODE;
    }
    @JsonIgnore
    public void setTXCODE(String TXCODE) {
        this.TXCODE = TXCODE;
    }

    @JsonIgnore
    public String getCoPlf_ID() {
        return CoPlf_ID;
    }
    @JsonIgnore
    public void setCoPlf_ID(String CoPlf_ID) {
        this.CoPlf_ID = CoPlf_ID;
    }
    @JsonIgnore
    public String getCrdt_No() {
        return Crdt_No;
    }
    @JsonIgnore
    public void setCrdt_No(String Crdt_No) {
        this.Crdt_No = Crdt_No;
    }
    @JsonIgnore
    public String getRsrv_TpCd() {
        return Rsrv_TpCd;
    }
    @JsonIgnore
    public void setRsrv_TpCd(String Rsrv_TpCd) {
        this.Rsrv_TpCd = Rsrv_TpCd;
    }
    @JsonIgnore
    public String getMblPh_No() {
        return MblPh_No;
    }
    @JsonIgnore
    public void setMblPh_No(String MblPh_No) {
        this.MblPh_No = MblPh_No;
    }

    @JsonIgnore
    public String getEbnk_Sign_Accno() {
        return Ebnk_Sign_Accno;
    }
    @JsonIgnore
    public void setEbnk_Sign_Accno(String Ebnk_Sign_Accno) {
        this.Ebnk_Sign_Accno = Ebnk_Sign_Accno;
    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("TXCODE=");
        sb.append(RelayCodeUtils.encode(this.getTXCODE()));
        sb.append("&Rsrv_TpCd=");
        sb.append(RelayCodeUtils.encode(this.getRsrv_TpCd()));
        sb.append("&bankType=");
        sb.append(RelayCodeUtils.encode(this.getBankType()));
        sb.append("&CoPlf_ID=");
        sb.append(RelayCodeUtils.encode(this.getCoPlf_ID()));
        sb.append("&Crdt_No=");
        sb.append(RelayCodeUtils.encode(this.getCrdt_No()));
        sb.append("&MblPh_No=");
        sb.append(RelayCodeUtils.encode(this.getMblPh_No()));
        sb.append("&Ebnk_Sign_Accno=");
        sb.append(RelayCodeUtils.encode(this.getEbnk_Sign_Accno()));

        return sb.toString();
    }
}
