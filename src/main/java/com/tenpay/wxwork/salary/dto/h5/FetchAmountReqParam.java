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
public class FetchAmountReqParam extends FrontEndReqBase {

    @NotBlank(message = "Cst_AccNo不能为空")
    @Length(min = 1, max = 32)
    @JsonProperty(required=true)
    private String Cst_AccNo;//账号

    @NotBlank(message = "ZZ_ass_acct_no不能为空")
    @Length(min = 1, max = 30)
    @JsonProperty(required=true)
    private String ZZ_ass_acct_no;//账号2

    @NotBlank(message = "SMS不能为空")
    @Length(min = 1, max = 6)
    @JsonProperty(value="SMS", required=true)
    private String SMS;//短信验证码

    @Length(max = 14)
    @JsonProperty(value="RPPDBnk_BrNo", required=true)
    private String RPPDBnk_BrNo;//收款人开户行行号

    @NotBlank(message = "CrdHldr_Crdt_No不能为空")
    @Length(min = 1, max = 120)
    @JsonProperty(value="CrdHldr_Crdt_No", required=true)
    private String CrdHldr_Crdt_No;//持卡人证件号码

    @NotBlank(message = "CrdHldr_Nm不能为空")
    @Length(min = 1, max = 180)
    @JsonProperty(value="CrdHldr_Nm", required=true)
    private String CrdHldr_Nm;//持卡人姓名


    @NotBlank(message = "ZZ_amt不能为空")
    @Length(min = 1, max = 16)
    @Digits(integer = 13,fraction = 2)
    @JsonProperty(required=true)
    private Double ZZ_amt;//交易金额


    @NotBlank(message = "card_banks_relation不能为空")
    @JsonProperty(value="card_banks_relation", required=true)
    private String card_banks_relation;//工资卡与绑定卡关系'SAME_BANK','DIFF_BANK'

    @NotBlank(message = "AcBa不能为空")
    //@Length(min = 1, max = 20)
    @Digits(integer = 30,fraction = 2)
    @JsonProperty(value="AcBa", required=true)
    private Double AcBa;//余额


    @Length( max = 9)
    @JsonProperty(value="Open_Branch_id")
    private String  Open_Branch_id;//E帐户开户行


    @JsonIgnore
    public String getCst_AccNo() {
        return Cst_AccNo;
    }

    @JsonIgnore
    public void setCst_AccNo(String Cst_AccNo) {
        this.Cst_AccNo = Cst_AccNo;
    }

    @JsonIgnore
    public String getZZ_ass_acct_no() {
        return ZZ_ass_acct_no;
    }

    @JsonIgnore
    public void setZZ_ass_acct_no(String ZZ_ass_acct_no) {
        this.ZZ_ass_acct_no = ZZ_ass_acct_no;
    }

    @JsonIgnore
    public String getSMS() {
        return SMS;
    }
    @JsonIgnore
    public void setSMS(String SMS) {
        this.SMS = SMS;
    }

    @JsonIgnore
    public String getRPPDBnk_BrNo() {
        return RPPDBnk_BrNo;
    }
    @JsonIgnore
    public void setRPPDBnk_BrNo(String RPPDBnk_BrNo) {
        this.RPPDBnk_BrNo = RPPDBnk_BrNo;
    }

    public String getCrdHldr_Crdt_No() {
        return CrdHldr_Crdt_No;
    }
    @JsonIgnore
    public void setCrdHldr_Crdt_No(String CrdHldr_Crdt_No) {
        this.CrdHldr_Crdt_No = CrdHldr_Crdt_No;
    }
    @JsonIgnore
    public String getCrdHldr_Nm() {
        return CrdHldr_Nm;
    }
    @JsonIgnore
    public void setCrdHldr_Nm(String CrdHldr_Nm) {
        this.CrdHldr_Nm = CrdHldr_Nm;
    }

    @JsonIgnore
    public Double getZZ_amt() {
        return ZZ_amt;
    }
    @JsonIgnore
    public void setZZ_amt(Double ZZ_amt) {
        this.ZZ_amt = ZZ_amt;
    }

    @JsonIgnore
    public String getCard_banks_relation() {
        return card_banks_relation;
    }
    @JsonIgnore
    public void setCard_banks_relation(String card_banks_relation) {
        this.card_banks_relation = card_banks_relation;
    }
    @JsonIgnore
    public Double getAcBa() {
        return AcBa;
    }
    @JsonIgnore
    public void setAcBa(Double acBa) {
        AcBa = acBa;
    }

    @JsonIgnore
    public String getOpen_Branch_id() {
        return Open_Branch_id;
    }
    @JsonIgnore
    public void setOpen_Branch_id(String open_Branch_id) {
        Open_Branch_id = open_Branch_id;
    }



    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("&Cst_AccNo=");
        sb.append(RelayCodeUtils.encode(this.getCst_AccNo()));
        sb.append("&ZZ_ass_acct_no=");
        sb.append(RelayCodeUtils.encode(this.getZZ_ass_acct_no()));
        sb.append("&ZZ_amt=");
        sb.append(RelayCodeUtils.encode(this.getZZ_amt().toString()));
        sb.append("&SMS=");
        sb.append(RelayCodeUtils.encode((this.getSMS())));
        sb.append("&CrdHldr_Crdt_No=");
        sb.append(RelayCodeUtils.encode((this.getCrdHldr_Crdt_No())));
        sb.append("&CrdHldr_Nm=");
        sb.append(RelayCodeUtils.encode((this.getCrdHldr_Nm())));
        sb.append("&card_banks_relation=");
        sb.append(RelayCodeUtils.encode(this.getCard_banks_relation()));
        if (this.getCard_banks_relation().equals("DIFF_BANK")){
            sb.append("&RPPDBnk_BrNo=");
            sb.append(RelayCodeUtils.encode((this.getRPPDBnk_BrNo())));
            sb.append("&Open_Branch_id=");
            sb.append(RelayCodeUtils.encode((this.getOpen_Branch_id())));
        }
        return sb.toString();
    }
}
