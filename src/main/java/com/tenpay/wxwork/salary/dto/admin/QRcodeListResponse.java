package com.tenpay.wxwork.salary.dto.admin;

import java.util.List;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class QRcodeListResponse extends FrontEndResponse {
	private List<QRcodeConfigParam> qRcodeList;

	public QRcodeListResponse(List<QRcodeConfigParam> qRcodeList) {
		super();
		this.qRcodeList = qRcodeList;
	}

	public List<QRcodeConfigParam> getqRcodeList() {
		return qRcodeList;
	}

	public void setqRcodeList(List<QRcodeConfigParam> qRcodeList) {
		this.qRcodeList = qRcodeList;
	}

}
