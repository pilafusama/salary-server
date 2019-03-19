package com.tenpay.wxwork.salary.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenpay.wxwork.salary.service.ApprovalStateNotifyService;
import com.tenpay.wxwork.salary.dto.ApprovalNotifyReqParam;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.model.CorpApprovalInfo;
import com.tenpay.wxwork.salary.info.ApprovalStatus;
import com.tenpay.wxwork.salary.info.PaymentState;


@RestController
public class ApprovalStateNotifyController {
	@Autowired
	private ApprovalStateNotifyService approvalStateNotifyService;
	
	private static final Logger logger = LoggerFactory.getLogger(ApprovalStateNotifyController.class);
	
	@RequestMapping(value = "/approvalStateNotify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FrontEndResponse ApprovalStateNotify(@RequestBody ApprovalNotifyReqParam request){
		
		//todo: 校验参数		
		//todo: 验证签名
		
		// 根据审批流id查询审批信息
		logger.debug("approval id:" + request.getId() + ", pay status:" + request.getPayStatus() + ", pay time:" + request.getPayTime() + ", memo:" + request.getMemo());
		CorpApprovalInfo corpApprovalInfo = approvalStateNotifyService.queryInfoByApprovalId(request.getId());
		if(null == corpApprovalInfo)
		{
			logger.error("Appraval id:" + request.getId() + " CorpAppraval Info not exist.");
			throw new BizException(BizError.CORP_APPRAVAL_INFO_NOT_EXIST.errorCode(),"CorpAppraval Info not exist.");
		}
		if(! corpApprovalInfo.getPayment_state().equals(PaymentState.Unpaid))
		{
			logger.warn("Appraval id:" + request.getId() + " repeat notice.");
			return new FrontEndResponse("0","OK");
		}
		if(! (corpApprovalInfo.getStatus() == ApprovalStatus.SubmitPayment))
		{
			logger.error("Appraval id:" + request.getId() + " Approval status error.");
			throw new BizException(BizError.ERROR_APPRAVAL_STATUS.errorCode(),"Approval status error.");
		}
		     
		//支付成功更新审批信息
		if(request.getPayStatus().equals(PaymentState.PaySucc))
		{
			approvalStateNotifyService.updatePaymentState(request.getPayStatus(), "", request.getPayTime(), request.getId());
		}
		//支付失败更新审批信息
		else if(request.getPayStatus().equals(PaymentState.PayFailed))
		{
			approvalStateNotifyService.updatePaymentState(request.getPayStatus(), request.getMemo(), request.getPayTime(), request.getId());
		}
		else
		{
			throw new BizException(BizError.UNSUPPORTED_PAY_STATUS.errorCode(),"Unsupported pay status.");
		}
		
		//设置返回
		return new FrontEndResponse("0","OK");
    }
}