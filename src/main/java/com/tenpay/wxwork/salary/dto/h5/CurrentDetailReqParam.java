package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class CurrentDetailReqParam extends FrontEndReqBase {

    @JsonProperty
    private String beg_Enqr_Dt; //起始查询日期

    @JsonProperty
    private String ctOf_Enqr_Dt; //截止查询日期

    @Length(max = 4)
    @JsonProperty
    private String smy_Cd; //摘要代码

    @Length(max = 4)
    @JsonProperty
    private String selYear; //查询年份

    @NotBlank(message = "nbPageStart不能为空")
    @Length(max = 4)
    @JsonProperty(required=true)
    private String nbPageStart; //当前页次

    @NotBlank(message = "hstCrd_AfCrd_Ind不能为空")
    @Length(max = 1)
    @JsonProperty(required=true)
    private String hstCrd_AfCrd_Ind; //主卡附属卡标志 0-主卡和副卡所有明细 1-只查询上送卡号明细

    @NotBlank(message = "seq_Fld_Ind不能为空")
    @Length(max = 1)
    @JsonProperty(required=true)
    private String seq_Fld_Ind; //排序字段标志 0-查询结果按日期正序返回 1-查询结果按日期倒序返回

    @NotBlank(message = "ccyCd不能为空")
    @Length(max = 3)
    @JsonProperty(required=true)
    private String ccyCd; //币种代码

    @NotBlank(message = "cshEx_Cd不能为空")
    @Length(max = 1)
    @JsonProperty(required=true)
    private String cshEx_Cd; //钞汇代码

    @NotBlank(message = "crd_Innr_Acc_CgyCd不能为空")
    @Length(max = 4)
    @JsonProperty(required=true)
    private String crd_Innr_Acc_CgyCd; //卡内账户类别代码

    public CurrentDetailReqParam(){}

    @JsonIgnore
    public String getHstCrd_AfCrd_Ind() {
        return hstCrd_AfCrd_Ind;
    }

    @JsonIgnore
    public void setHstCrd_AfCrd_Ind(String hstCrd_AfCrd_Ind) {
        this.hstCrd_AfCrd_Ind = hstCrd_AfCrd_Ind;
    }

    @JsonIgnore
    public String getSeq_Fld_Ind() {
        return seq_Fld_Ind;
    }

    @JsonIgnore
    public void setSeq_Fld_Ind(String seq_Fld_Ind) {
        this.seq_Fld_Ind = seq_Fld_Ind;
    }


    @JsonIgnore
    public String getBeg_Enqr_Dt() {
        return beg_Enqr_Dt;
    }

    @JsonIgnore
    public void setBeg_Enqr_Dt(String beg_Enqr_Dt) {
        this.beg_Enqr_Dt = beg_Enqr_Dt;
    }

    @JsonIgnore
    public String getCtOf_Enqr_Dt() {
        return ctOf_Enqr_Dt;
    }

    @JsonIgnore
    public void setCtOf_Enqr_Dt(String ctOf_Enqr_Dt) {
        this.ctOf_Enqr_Dt = ctOf_Enqr_Dt;
    }

    @JsonIgnore
    public String getSmy_Cd() {
        return smy_Cd;
    }

    @JsonIgnore
    public void setSmy_Cd(String smy_Cd) {
        this.smy_Cd = smy_Cd;
    }

    @JsonIgnore
    public String getSelYear() {
        return selYear;
    }

    @JsonIgnore
    public void setSelYear(String selYear) {
        this.selYear = selYear;
    }

    @JsonIgnore
    public String getNbPageStart() {
        return nbPageStart;
    }

    @JsonIgnore
    public void setNbPageStart(String nbPageStart) {
        this.nbPageStart = nbPageStart;
    }

    @JsonIgnore
    public String getCcyCd() {
        return ccyCd;
    }

    @JsonIgnore
    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    @JsonIgnore
    public String getCshEx_Cd() {
        return cshEx_Cd;
    }

    @JsonIgnore
    public void setCshEx_Cd(String cshEx_Cd) {
        this.cshEx_Cd = cshEx_Cd;
    }

    @JsonIgnore
    public String getCrd_Innr_Acc_CgyCd() {
        return crd_Innr_Acc_CgyCd;
    }

    @JsonIgnore
    public void setCrd_Innr_Acc_CgyCd(String crd_Innr_Acc_CgyCd) {
        this.crd_Innr_Acc_CgyCd = crd_Innr_Acc_CgyCd;
    }
}
