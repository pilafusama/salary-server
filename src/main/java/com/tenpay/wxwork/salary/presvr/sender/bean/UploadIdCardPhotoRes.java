package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;

public class UploadIdCardPhotoRes extends BankProxyRelayResponseMsg {

    private static final long serialVersionUID = 1215933782793607099L;

    public UploadIdCardPhotoRes(int result, String resInfo,String bankResult, String bankResInfo) {
        super(result, resInfo,bankResult,bankResInfo);
    }

    public void setResponse() {
    }
}
