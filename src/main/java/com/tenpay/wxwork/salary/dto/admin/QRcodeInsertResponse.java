package com.tenpay.wxwork.salary.dto.admin;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class QRcodeInsertResponse extends FrontEndResponse {

	private int promotion_qrcode_id;
	
	public QRcodeInsertResponse() {
		super();
	}

	public QRcodeInsertResponse(int promotion_qrcode_id) {
		super();
		this.promotion_qrcode_id = promotion_qrcode_id;
	}
	
	public int getPromotion_qrcode_id() {
		return promotion_qrcode_id;
	}

	public void setPromotion_qrcode_id(int promotion_qrcode_id) {
		this.promotion_qrcode_id = promotion_qrcode_id;
	}	


	
}
