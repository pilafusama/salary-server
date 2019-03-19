package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;

public class UpdateFinancialReqParam extends FrontEndReqBase {
    @Length(max = 10)
    @JsonProperty
    private String financial_plan_id;

    @NotBlank(message = "tran_cycle不能为空")
    @Length( max = 2)
    private String tran_cycle; //交易周期(01月 02季 03年)

    @NotBlank(message = "tran_date不能为空")
    @Length( max = 8)
    private String tran_date; //交易日期

    @NotBlank(message = "tran_prod不能为空")
    @Length( max = 100)
    @JsonProperty()
    private String tran_prod; //交易产品

    @NotBlank(message = "tran_amt不能为空")
    @Length(min = 1, max = 18)
    @Digits(integer = 15,fraction = 2)
    @JsonProperty(value="tran_amt", required=true)
    private String tran_amt;//转账金额

    public String getFinancial_plan_id() {
        return financial_plan_id;
    }

    public void setFinancial_plan_id(String financial_plan_id) {
        this.financial_plan_id = financial_plan_id;
    }

    public String getTran_cycle() {
        return tran_cycle;
    }

    public void setTran_cycle(String tran_cycle) {
        this.tran_cycle = tran_cycle;
    }

    public String getTran_date() {
        return tran_date;
    }

    public void setTran_date(String tran_date) {
        this.tran_date = tran_date;
    }

    public String getTran_prod() {
        return tran_prod;
    }

    public void setTran_prod(String tran_prod) {
        this.tran_prod = tran_prod;
    }

    public String getTran_amt() {
        return tran_amt;
    }

    public void setTran_amt(String tran_amt) {
        this.tran_amt = tran_amt;
    }
}
