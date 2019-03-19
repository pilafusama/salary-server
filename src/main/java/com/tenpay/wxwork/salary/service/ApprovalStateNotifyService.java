package com.tenpay.wxwork.salary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenpay.wxwork.salary.dao.CorpApprovalInfoDAO;
import com.tenpay.wxwork.salary.model.CorpApprovalInfo; 

@Service
public class ApprovalStateNotifyService {
	
	@Autowired
	private CorpApprovalInfoDAO corpApprovalInfoDAO;

	public CorpApprovalInfo queryInfoByApprovalId(String approvalId) {
		return corpApprovalInfoDAO.queryCorpApprovalInfo(approvalId);
	}
	
	public void updatePaymentState(String paymentState,String payMemo, String payTime, String approvalId)
	{
		corpApprovalInfoDAO.updatePaymentState(paymentState, payMemo, payTime, approvalId);
	}
}
