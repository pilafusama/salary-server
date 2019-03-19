package com.tenpay.wxwork.salary.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.dto.ApprovalNotify;
import com.tenpay.wxwork.salary.dto.ApprovalResponse;
import com.tenpay.wxwork.salary.dto.CorpAppRelationDTO;
import com.tenpay.wxwork.salary.dto.FlowSubmitRes;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.info.AppId;
import com.tenpay.wxwork.salary.info.ApprovalStatus;
import com.tenpay.wxwork.salary.model.AuthenCkv;
import com.tenpay.wxwork.salary.model.CorpApprovalInfo;
import com.tenpay.wxwork.salary.service.ApprovalInfoService;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.CorpAppRelationService;
import com.tenpay.wxwork.salary.service.SessionService;

@RestController
public class CorpApprovalInfoCallBackController {
	@Autowired
	private CorpAppRelationService corpAppRelationService;	
	@Autowired
	private SessionService sessionService;	
	@Autowired
	private ApprovalInfoService approvalInfoService;
	@Autowired
	private BankProxyRequestService bankProxyRequestService;
		
	@RequestMapping(value = "/corpApprovalInfoCallBack", method = RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public FrontEndResponse corpApprovalInfoCallback(@RequestBody ApprovalNotify approvalNotify) throws JsonProcessingException{
		String corpid = approvalNotify.getToUserName();
		String appid = AppId.ApprovalStream;
		if (corpAppRelationService.checkCorpRelationStatus(corpid, appid))
		{
			String agentId = ConfigUtils.getAgentId();
			AuthenCkv accessToken = sessionService.getNewCorpAccessToken(corpid, agentId);	
		
			//处理企业微信审批通知
		    ApprovalResponse approvalResponse = approvalInfoService.queryApprovalData(corpid, accessToken.getAuthCode());
			if(approvalResponse == null){
				throw new BizException(BizError.CORP_RELATION_AUTHEN_FAILED.errorCode(),"approvalResponse not exist.");
			}
	        CorpAppRelationDTO corpAppRelationDTO = corpAppRelationService.getCacheCorpRelationDTO(corpid);
		    List<CorpApprovalInfo> corpApprovalInfoList = approvalInfoService.buildCorpApprovalInfo(corpid, corpAppRelationDTO.getBankType(), approvalResponse);
		    if(corpApprovalInfoList == null){
				throw new BizException(BizError.BUILD_CORPAPPROVALINFO_FAIL.errorCode(),BizError.BUILD_CORPAPPROVALINFO_FAIL.errorMsg());
			}
			for (int i = 0; i < corpApprovalInfoList.size(); i++)
			{
				approvalInfoService.insertCorpApprovalInfo(corpApprovalInfoList.get(i));
				//发送银行
				FlowSubmitRes flowSubmitRes = bankProxyRequestService.FlowSubmit(corpAppRelationDTO, corpApprovalInfoList.get(i), appid);
				//更新db
				approvalInfoService.updateBankApprovalInfo(ApprovalStatus.SubmitPayment, flowSubmitRes.getBankListid(), corpApprovalInfoList.get(i).getApproval_id());
			}
		}
		else
		{
			throw new BizException(BizError.CORP_RELATION_AUTHEN_FAILED.errorCode(),"Corpinfo not exist.");
		}
				
		return new FrontEndResponse("0","OK");
	}	
}
