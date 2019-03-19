package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;

/**
 * @author : wg
 * @description :
 * @date : 2018/8/19
 */
public class FetchAmountRes extends BankProxyRelayResponseMsg {


    private static final String RETCODE_KEY = "retcode";
    private static final String ERRMSG_KEY = "errmsg";

    private static final String SYS_EVT_TRA_CE_ID = "SYS_EVT_TRACE_ID" ;

    public FetchAmountRes(int result, String resInfo, String bankResult, String bankResInfo) {
        super(result, resInfo, bankResult, bankResInfo);
    }


    private String SYS_EVT_TRACE_ID;//全局事件流水号
    //业务结果状态码
    private String retcode;
    //结果说明
    private String errmsg;

    public void setResponse(String SYS_EVT_TRACE_ID,String retcode,String errMsg){
        setSYS_EVT_TRACE_ID(SYS_EVT_TRACE_ID);
        setRetcode(retcode);
        setErrmsg(errMsg);
    }

    public String getSYS_EVT_TRACE_ID() {
        this.SYS_EVT_TRACE_ID = this.get(SYS_EVT_TRA_CE_ID);
        return SYS_EVT_TRACE_ID;
    }

    public void setSYS_EVT_TRACE_ID(String SYS_EVT_TRACE_ID) {
        this.put(SYS_EVT_TRA_CE_ID, SYS_EVT_TRACE_ID);
        this.SYS_EVT_TRACE_ID = SYS_EVT_TRACE_ID;
    }


    public String getRetcode() {
        this.retcode = this.get(RETCODE_KEY);
        return retcode;
    }
    public void setRetcode(String retcode) {
        this.put(RETCODE_KEY,retcode);
        this.retcode = retcode;
    }
    public String getErrmsg() {
        this.errmsg = this.get(ERRMSG_KEY);
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.put(ERRMSG_KEY, errmsg);
        this.errmsg = errmsg;
    }




}
