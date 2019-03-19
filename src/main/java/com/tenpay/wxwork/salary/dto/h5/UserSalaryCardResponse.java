package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class UserSalaryCardResponse  extends FrontEndResponse {
    @JsonProperty(value="bind_card_number")
    private String bindCardNumber;
    @JsonProperty(value="bind_card_bank_sname")
    private String bindCardBankSname;
    @JsonProperty(value="bind_card_bank_chname")
    private String bindCardBankChname;
    @JsonProperty(value="cre_name")
    private String creName;

    public UserSalaryCardResponse(String bindCardNumber, String bindCardBankSname, String bindCardBankChname, String creName) {
        this.bindCardNumber = bindCardNumber;
        this.bindCardBankSname = bindCardBankSname;
        this.bindCardBankChname = bindCardBankChname;
        this.creName = creName;
    }

    public String getBindCardNumber() {
        return bindCardNumber;
    }

    public void setBindCardNumber(String bindCardNumber) {
        this.bindCardNumber = bindCardNumber;
    }

    public String getBindCardBankSname() {
        return bindCardBankSname;
    }

    public void setBindCardBankSname(String bindCardBankSname) {
        this.bindCardBankSname = bindCardBankSname;
    }

    public String getBindCardBankChname() {
        return bindCardBankChname;
    }

    public void setBindCardBankChname(String bindCardBankChname) {
        this.bindCardBankChname = bindCardBankChname;
    }

    public String getCreName() {
        return creName;
    }

    public void setCreName(String creName) {
        this.creName = creName;
    }
}
