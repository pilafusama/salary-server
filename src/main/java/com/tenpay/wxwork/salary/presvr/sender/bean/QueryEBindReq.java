package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.QueryEBindReqParam;
import org.apache.commons.lang3.StringUtils;

public class QueryEBindReq extends BankProxyRelayRequestMsg {

    private static final String FUNC_CODE = "Func_code";
    private static final String CRDT_NO = "Crdt_No";
    private static final String MBSH_NO = "Mbsh_No";


    public QueryEBindReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }


    @JsonProperty(value="Func_code", required=true)
    private String Func_code;//
    @JsonProperty(value="Crdt_No")
    private String Crdt_No;//
    @JsonProperty( value="Mbsh_No")
    private String Mbsh_No;//



    public void setRequest(QueryEBindReqParam request, String bankType)
    {
        this.setFunc_code(request.getFunc_code());
        this.setMbsh_No(request.getMbsh_No());
        this.setCrdt_No(request.getCrdt_No());
        this.validate();
    }


    private void validate()
    {
        if(StringUtils.isBlank(this.getFunc_code())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Func_code is empty.");
        }
        if(this.getFunc_code().equals("1") && StringUtils.isBlank(this.getCrdt_No())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Crdt_No is empty.");
        }
        if(this.getFunc_code().equals("2") && StringUtils.isBlank(this.getMbsh_No())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Mbsh_No is empty.");
        }

    }


    public String getFunc_code() {
        return Func_code;
    }

    public void setFunc_code(String Func_code) {
        this.put(FUNC_CODE,Func_code);
        this.Func_code = Func_code;
    }


    public String getCrdt_No() {
        return Crdt_No;
    }

    public void setCrdt_No(String Crdt_No) {
        this.put(CRDT_NO,Crdt_No);
        this.Crdt_No = Crdt_No;
    }


    public String getMbsh_No() {
        return Mbsh_No;
    }

    public void setMbsh_No(String Mbsh_No)
    {   this.put(MBSH_NO,Mbsh_No);
        this.Mbsh_No = Mbsh_No;
    }


}