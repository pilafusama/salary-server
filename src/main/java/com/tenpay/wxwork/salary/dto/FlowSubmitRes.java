package com.tenpay.wxwork.salary.dto;

import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;

public class FlowSubmitRes extends BankProxyRelayResponseMsg {

	private static final long serialVersionUID = 1215933782793607099L;
	private static final String BANK_LISTID_ID_KEY = "bank_listid";
	private static final String RETCODE_KEY = "retcode";
	private static final String ERRMSG_KEY = "errmsg";
	
	//银行受理标识
    private String bank_listid; 
    //业务结果状态码
	private String retcode; 
	//结果说明
	private String errmsg;

    public FlowSubmitRes(int result, String resInfo,String bankResult, String bankResInfo) {
        super(result, resInfo,bankResult,bankResInfo);
    } 
    public void setResponse(String bankListid,String retcode,String errMsg) {
    	setBankListid(bankListid);
    	setRetcode(retcode);
    	setErrmsg(errMsg);
    }
	public String getBankListid() {
		this.bank_listid = this.get(BANK_LISTID_ID_KEY);
		return bank_listid;
	}
	public void setBankListid(String bankListid) {
		this.put(BANK_LISTID_ID_KEY, bankListid);
		this.bank_listid = bankListid;
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
