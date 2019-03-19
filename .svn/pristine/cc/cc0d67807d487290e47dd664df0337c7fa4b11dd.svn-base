package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class UploadBankCardPhotoResponse extends FrontEndResponse{
    public UploadBankCardPhotoResponse(String retCode,String errMsg,String startFlag,String cardNumber,String bankName,String bankNum) {
        super(retCode,errMsg);
        this.stateFlag = startFlag;
        this.cardNumber = cardNumber;
        this.bankName = bankName;
        this.bankNum = bankNum;
    }

    @JsonProperty(value="card_number")
    private String cardNumber;
    @JsonProperty(value="bank_name")
    private String bankName;
    @JsonProperty(value="bank_num")
    private String  bankNum;

    @JsonProperty("stateFlag")
    private String stateFlag;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getStateFlag() {
        return stateFlag;
    }

    public void setStateFlag(String stateFlag) {
        this.stateFlag = stateFlag;
    }
}
