package com.tenpay.wxwork.salary.dto;

import com.tenpay.bap.common.error.FPError;
import com.tenpay.bap.common.exception.FPException;
import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.bap.relay.protocol.RelayRequestBase;

public class RelayDemoRequestDTO extends RelayRequestBase {

	private static final long serialVersionUID = -2733470978655750032L;
	private static final String OP_STATUS_KEY = "op_status";
	protected Integer bankType;
	protected Integer opStatus;

	public RelayDemoRequestDTO(String msgNo, Integer requestType, int opStatus, String version, String spid) {
		super(msgNo, requestType, version, spid);
		this.setBankType(bankType);
		this.setOpStatus(opStatus);
	}

	public Integer getBankType() {
		this.bankType = this.getInt(BankProxyRelayRequestMsg.BANK_TYPE);
		return bankType;
	}

	public void setBankType(Integer bankType) {
		this.bankType = bankType;
		this.addAndReplace(BankProxyRelayRequestMsg.BANK_TYPE, bankType);
	}

	public Integer getOpStatus() {
		this.opStatus = this.getInt(OP_STATUS_KEY);
		return opStatus;
	}

	public void setOpStatus(Integer opStatus) {
		this.opStatus = opStatus;
		this.addAndReplace(OP_STATUS_KEY, opStatus);
	}

	@Override
	public void validate() {
		super.validate();
		if (bankType == null) {
			throw new FPException(FPError.PARAM_INVALID, "bank_type is null");
		}
		if (this.opStatus == null) {
			throw new FPException(FPError.PARAM_INVALID, "opStatus is null");
		}
	}

}
