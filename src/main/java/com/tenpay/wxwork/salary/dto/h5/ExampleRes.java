package com.tenpay.wxwork.salary.dto.h5;

import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/19
 */
public class ExampleRes extends BankProxyRelayResponseMsg {

    private static final String MY_MONEY = "my_moneny";

    public ExampleRes(int result, String resInfo, String bankResult, String bankResInfo) {
        super(result, resInfo, bankResult, bankResInfo);
    }

    private String myMoney;

    public void setResponse(String myMoney){
        this.myMoney = this.get(MY_MONEY);
    }

    public String getMyMoney() {
        return myMoney;
    }

    public void setMyMoney(String myMoney) {
        this.myMoney = myMoney;
    }
}
