package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;

public class VerifyUserRes extends BankProxyRelayResponseMsg {
    private static final String PLAT_FLOW_NO = "plat_flow_no";

    public VerifyUserRes(int result, String resInfo, String bankResult, String bankResInfo) {
        super(result, resInfo, bankResult, bankResInfo);
    }

    private String plat_flow_no;

    public void setResponse(String plat_flow_no){
        setPlat_flow_no(plat_flow_no);
    }

    public String getPlat_flow_no() {
        this.plat_flow_no = this.get(PLAT_FLOW_NO);
        return plat_flow_no;
    }

    public void setPlat_flow_no(String plat_flow_no) {
        this.put(PLAT_FLOW_NO, plat_flow_no);
        this.plat_flow_no = plat_flow_no;
    }
}
