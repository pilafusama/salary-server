package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;
import com.tenpay.bap.relay.protocol.RelayMessage;

public class BalanceRes extends BankProxyRelayResponseMsg {

    private static final String TOTALAMT = "totalAmt";
    private static final String CURRENTAMT = "currentAmt";
    private static final String FIXEDAMT = "fixedAmt";

    public BalanceRes(int result, String resInfo, String bankResult, String bankResInfo) {
        super(result, resInfo, bankResult, bankResInfo);
    }

    private String totalAmt; //资产总额

    private String currentAmt; //活期

    private String fixedAmt; //定期


    public void setResponse(RelayMessage resMap){
        this.totalAmt = resMap.getValueByKey(TOTALAMT);
        this.currentAmt = resMap.getValueByKey(CURRENTAMT);
        this.fixedAmt = resMap.getValueByKey(FIXEDAMT);
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getCurrentAmt() {
        return currentAmt;
    }

    public void setCurrentAmt(String currentAmt) {
        this.currentAmt = currentAmt;
    }

    public String getFixedAmt() {
        return fixedAmt;
    }

    public void setFixedAmt(String fixedAmt) {
        this.fixedAmt = fixedAmt;
    }

}
