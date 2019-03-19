package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.QueryExistCategory2AccountsReqParam;
import org.apache.commons.lang3.StringUtils;

public class QueryExistCategory2AccountsReq extends BankProxyRelayRequestMsg {

    private static final String CRDT_NO = "crdt_no";
    private static final String DBCRD_CARDNO = "dbcrd_cardno";


    private String Crdt_No;
    private String DbCrd_CardNo;

    public QueryExistCategory2AccountsReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }

    public void setRequest(QueryExistCategory2AccountsReqParam request, String bankType)
    {

        this.setCrdt_No(request.getCrdt_No());
        this.setDbCrd_CardNo(request.getDbCrd_CardNo());
        this.validate();
    }

    public String getCrdt_No() {
        return Crdt_No;
    }

    public void setCrdt_No(String crdt_No) {
        this.put(CRDT_NO,crdt_No);
        Crdt_No = crdt_No;
    }

    public String getDbCrd_CardNo() {
        return DbCrd_CardNo;
    }

    public void setDbCrd_CardNo(String dbCrd_CardNo) {
        this.put(DBCRD_CARDNO,dbCrd_CardNo);
        DbCrd_CardNo = dbCrd_CardNo;
    }


    private void validate()
    {
        if(StringUtils.isBlank(this.getCrdt_No())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Crdt_No is empty.");
        }
    }

}
