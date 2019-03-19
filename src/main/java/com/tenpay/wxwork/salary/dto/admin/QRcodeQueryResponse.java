package com.tenpay.wxwork.salary.dto.admin;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class QRcodeQueryResponse extends FrontEndResponse{
	private QRcodeConfigParam qRcodeConfigParam;
	
	public QRcodeQueryResponse() {
		super();
	}
	
	public QRcodeQueryResponse(QRcodeConfigParam qRcodeConfigParam) {
		this.qRcodeConfigParam = qRcodeConfigParam;
	}

	public QRcodeConfigParam getqRcodeConfigParam() {
		return qRcodeConfigParam;
	}

	public void setqRcodeConfigParam(QRcodeConfigParam qRcodeConfigParam) {
		this.qRcodeConfigParam = qRcodeConfigParam;
	}
	
}
