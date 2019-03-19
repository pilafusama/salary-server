package com.tenpay.wxwork.salary.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.FinancalUtils;
import com.tenpay.wxwork.salary.dto.CorpAppRelationDTO;
import com.tenpay.wxwork.salary.dto.CorpBindConfirmReqParam;
import com.tenpay.wxwork.salary.dto.CorpBindConfirmRes;
import com.tenpay.wxwork.salary.dto.CorpInfoDTO;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.info.AppId;
import com.tenpay.wxwork.salary.info.CorpAppRelationAuthenStatus;
import com.tenpay.wxwork.salary.info.CorpAppRelationStatus;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.CorpAppRelationService;
import com.tenpay.wxwork.salary.service.CorpInfoService;
import com.tenpay.wxwork.salary.service.SessionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CorpBindConfirmController {
	@Autowired
	private CorpInfoService corpInfoService;
	@Autowired
	private CorpAppRelationService corpAppRelationService;
	@Autowired
	private BankProxyRequestService bankProxyRequestService;
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping(value = "/approval/corpBindConfirm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FrontEndResponse CorpBindConfirm(@Validated @RequestBody CorpBindConfirmReqParam request){
		
		//TODO:待fish完善登陆态功能后更新
		
		sessionService.verifySign(request.getLogSsid(),request.getTimeStamp(),request.getNonce(),request.getSign());
		FinancalUtils.checkCorpBindRequestParam(request.getStatus(),request.getBankType(),request.getAppid());
	    
		// 2、登录态检查（在session复用时有效)
        SessionInfo sessionInfo = sessionService.getSession(request.getLogSsid());
        if (sessionInfo == null)
        {
        	throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        
		//调用金融机构绑定确认服务
		CorpInfoDTO corpInfoDTO = corpInfoService.queryCorpByCorpid(request.getCorpId());
		if(null == corpInfoDTO)
		{
			throw new BizException(BizError.CORP_INFO_NOT_EXIST.errorCode(),"Corpinfo not exist.");
		}
		boolean isCorpInfoNeedUpdate = corpInfoService.isNeedUpdate(corpInfoDTO,request.getCorpName(),request.getCertId());

		String appid = AppId.ApprovalStream;
		CorpAppRelationDTO corpAppRelationDTO = corpAppRelationService.queryCorpByCorpid(request.getCorpId(), appid);
		if(null == corpAppRelationDTO)
		{
			throw new BizException(BizError.CORP_APP_RELATION_NOT_EXIST.errorCode(),"CorpAppRelation not exist.");
		}
		
		boolean isCorpAppRelationNeedUpdate = corpAppRelationService.isNeedUpdate(corpAppRelationDTO, request.getBankType(), request.getBankUin(), request.getOpUserPhone(),"");

		if(isCorpInfoNeedUpdate )
		{
			throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"Corpinfo is inconsistence."); 
		}
		if(isCorpAppRelationNeedUpdate)
		{
			throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"CorpAppRelation is inconsistence."); 
		}
		
		//去银行
		if(request.getStatus().equals(CorpAppRelationStatus.Stop))
		{
			if(!corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Stop))
			{
				throw new BizException(BizError.UNSUPPORTED_RELATION_STATUS_ERROR.errorCode(),"UNSUPPORTED_RELATION_STATUS_ERROR.");	
			}
			else
			{
				return new FrontEndResponse("0","OK");
			}
		}	
		else if(request.getStatus().equals(CorpAppRelationStatus.Close))
		{
			if(!corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Close))
			{
				throw new BizException(BizError.UNSUPPORTED_RELATION_STATUS_ERROR.errorCode(),"UNSUPPORTED_RELATION_STATUS_ERROR.");	
			}
			if(corpAppRelationDTO.getAuthenStatus() == CorpAppRelationAuthenStatus.Normal)
			{
				bankProxyRequestService.CorpBindConfirm(request,appid);
				corpAppRelationService.updateAuthenStatusCloseFromNormal(request.getCorpId(), appid);
			}
		}
		else if(request.getStatus().equals(CorpAppRelationStatus.Open))
		{
			if(!corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Open))
			{
				throw new BizException(BizError.UNSUPPORTED_RELATION_STATUS_ERROR.errorCode(),"UNSUPPORTED_RELATION_STATUS_ERROR.");	
			}
			if(corpAppRelationDTO.getAuthenStatus() == CorpAppRelationAuthenStatus.Initial)
			{
				CorpBindConfirmRes corpBindConfirmRes= bankProxyRequestService.CorpBindConfirm(request,appid);
				corpAppRelationService.updateAuthenStatusNormalFromInitial(request.getCorpId(), appid,corpBindConfirmRes.getCorpAuthId());		
			}
			else if(corpAppRelationDTO.getAuthenStatus() == CorpAppRelationAuthenStatus.Close)
			{
				CorpBindConfirmRes corpBindConfirmRes= bankProxyRequestService.CorpBindConfirm(request,appid);
				corpAppRelationService.updateAuthenStatusNormalFromClose(request.getCorpId(), appid,corpBindConfirmRes.getCorpAuthId());		
			}
	}

		return new FrontEndResponse("0","OK");
	}
}
