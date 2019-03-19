package com.tenpay.wxwork.salary.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenpay.wxwork.salary.client.ApprovalClient;
import com.tenpay.wxwork.salary.dto.CorpBindAuthenReqParam;
import com.tenpay.wxwork.salary.dto.CorpBindConfirmReqParam;
import com.tenpay.wxwork.salary.dto.admin.QueryAuthStatusReqParam;
import com.tenpay.wxwork.salary.dto.admin.SalaryApprovalResponse;

@Service
public class SalaryApprovalService {
	@Autowired
	private ApprovalClient approvalClient;

	public SalaryApprovalResponse queryAuthStatus(String corpid, String bankType) {
		QueryAuthStatusReqParam request = new QueryAuthStatusReqParam(corpid, bankType);
		return approvalClient.queryAuthStatus(request);
	}

	public SalaryApprovalResponse corpBindAuthen(CorpBindAuthenReqParam request) {
		return approvalClient.corpBindAuthen(request);
	}

	public SalaryApprovalResponse corpBindConfirm(CorpBindConfirmReqParam request) {
		return approvalClient.corpBindConfirm(request);
	}

}
