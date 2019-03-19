package com.tenpay.wxwork.salary.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.FinancalUtils;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.dto.CorpAppRelationDTO;
import com.tenpay.wxwork.salary.dto.CorpBindAuthenReqParam;
import com.tenpay.wxwork.salary.dto.CorpInfoDTO;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.GetAdminListReq;
import com.tenpay.wxwork.salary.dto.GetAdminListRes;
import com.tenpay.wxwork.salary.info.AppId;
import com.tenpay.wxwork.salary.info.CorpAppRelationAuthenStatus;
import com.tenpay.wxwork.salary.info.CorpAppRelationStatus;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.CorpAppRelationService;
import com.tenpay.wxwork.salary.service.CorpInfoService;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.wxauth.WxworkService;
import com.tenpay.wxwork.salary.util.JsonUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CorpBindAuthenController {
	@Autowired
	private CorpInfoService corpInfoService;
	@Autowired
	private CorpAppRelationService corpAppRelationService;
	@Autowired
	private BankProxyRequestService bankProxyRequestService;
	@Autowired
	private WxworkService wxworkService;
	@Autowired
	private SessionService sessionService;
	
	private static final Logger logger = LoggerFactory.getLogger(CorpBindAuthenController.class);
	
	@RequestMapping(value = "/approval/corpBindAuthen", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FrontEndResponse corpBindAuthen(@Validated @RequestBody CorpBindAuthenReqParam request){
		
		//TODO:待fish完善登陆态功能后更新
		
		sessionService.verifySign(request.getLogSsid(),request.getTimeStamp(),request.getNonce(),request.getSign());
		FinancalUtils.checkCorpBindRequestParam(request.getStatus(),request.getBankType(),request.getAppid());
		
        // 2、登录态检查（在session复用时有效)
        SessionInfo sessionInfo = sessionService.getSession(request.getLogSsid());
        if (sessionInfo == null)
        {
        	throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
//		String suiteAccessToken = sessionService.getSuiteAccessToken(ConfigUtils.getSuiteId());
		
		//3、检查用户身份是企业管理员权限，否则报错。	
		/*GetAdminListReq getAdminListReq = new GetAdminListReq(request.getCorpId(),request.getAppid());
		GetAdminListRes getAdminListRes = wxworkService.getAdminList(suiteAccessToken, getAdminListReq);
		if(!FinancalUtils.isOpUser(getAdminListRes,request.getOpUserId()))
		{
			throw new BizException(BizError.OP_USER_ID_NOT_ADMIN.errorCode(),"opUserId is not admin.");
		}
		String adminList = JsonUtils.toJson(getAdminListRes.getAdmin());
		logger.debug("adminList:"+adminList);*/
		String adminList = "";//由于前端不想传入appid，后端无法验证管理员及获取管理员列表，暂时赋值为空，长远看还是需要验证管理员，后续再考虑。
		
		//调用金融机构绑定鉴权服务
		CorpInfoDTO corpInfoDTO = corpInfoService.queryCorpByCorpid(request.getCorpId());
		if(null == corpInfoDTO)
		{
			throw new BizException(BizError.CORP_INFO_NOT_EXIST.errorCode(),"Corpinfo not exist.");
		}
		boolean isCorpInfoNeedUpdate = corpInfoService.isNeedUpdate(corpInfoDTO,request.getCorpName(),request.getCertId());

		//TODO:后续支持多应用时，需根据请求获取平台对应的appid
		String appid = AppId.ApprovalStream;
		CorpAppRelationDTO corpAppRelationDTO = corpAppRelationService.queryCorpByCorpid(request.getCorpId(), appid);
		boolean isCorpAppRelationNeedUpdate = false;
		if(null != corpAppRelationDTO)
		{
			isCorpAppRelationNeedUpdate = corpAppRelationService.isNeedUpdate(corpAppRelationDTO, request.getBankType(), request.getBankUin(), request.getOpUserPhone(),adminList);
		}

		if(request.getStatus().equals(CorpAppRelationStatus.Open))
		{
			return handleOpen(request,corpInfoDTO,corpAppRelationDTO,appid,adminList,isCorpInfoNeedUpdate,isCorpAppRelationNeedUpdate);
		}
		else if(request.getStatus().equals(CorpAppRelationStatus.Close))
		{
			return handleClose(request,corpInfoDTO,corpAppRelationDTO,appid,isCorpInfoNeedUpdate,isCorpAppRelationNeedUpdate);
		}
		else
		{
			return handleStop(request,corpInfoDTO,corpAppRelationDTO,appid,isCorpInfoNeedUpdate,isCorpAppRelationNeedUpdate);
		}
    }
	
	private FrontEndResponse handleOpen(CorpBindAuthenReqParam request,CorpInfoDTO corpInfoDTO,CorpAppRelationDTO corpAppRelationDTO,String appid, String adminList, boolean isCorpInfoNeedUpdate,boolean isCorpAppRelationNeedUpdate)
	{
		boolean isNeedSendtoBank = false;
		if(null == corpAppRelationDTO)
		{
			corpAppRelationService.insertCorpAppRelation(request.getCorpId(), appid,request.getBankType(), request.getBankUin(), adminList, request.getOpUserPhone(),request.getStatus());
			isNeedSendtoBank = true;
		}
		else if(corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Open))
		{
			//重入
			if(CorpAppRelationAuthenStatus.Normal == corpAppRelationDTO.getAuthenStatus())
			{
				if(!corpAppRelationDTO.getBankType().equals(request.getBankType()))
				{
					throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"bankType inconsistence."); 
				}

				if(isCorpInfoNeedUpdate )
				{
					throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"Corpinfo is inconsistence."); 
				}
				
				if(isCorpAppRelationNeedUpdate)
				{
					throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"CorpAppRelation is inconsistence."); 
				}
				return new FrontEndResponse("0","OK");	
			}
			else
			{
				if(isCorpInfoNeedUpdate )
				{
					corpInfoService.updateCorpInfo(request.getCorpId(), request.getCorpName(), request.getCertId());
				}
				if(isCorpAppRelationNeedUpdate)
				{
					corpAppRelationService.updateCorpAppRelationFromOpen(request.getCorpId(), appid, request.getBankType(), request.getBankUin(), adminList, request.getOpUserPhone());
				}
				isNeedSendtoBank = true;
			}
		}
		else if(corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Stop))
		{
			if(!corpAppRelationDTO.getBankType().equals(request.getBankType()))
			{
				throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"bankType inconsistence."); 
			}
			if(isCorpInfoNeedUpdate )
			{
				throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"Corpinfo is inconsistence."); 
			}
			
			if(isCorpAppRelationNeedUpdate)
			{
				throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"CorpAppRelation is inconsistence."); 
			}
			corpAppRelationService.updateStatusOpenFromStop(request.getCorpId(), appid);
		}
		else if(corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Close))
		{
			if(corpAppRelationDTO.getAuthenStatus() == CorpAppRelationAuthenStatus.Normal)
			{
				isNeedSendtoBank = false;
			}
			else
			{
				isNeedSendtoBank = true;
			}
			if(isCorpInfoNeedUpdate )
			{
				corpInfoService.updateCorpInfo(request.getCorpId(), request.getCorpName(), request.getCertId());
			}
			
			corpAppRelationService.updateCorpAppRelationFromClose(request.getCorpId(), appid, request.getBankType(), request.getBankUin(), adminList, request.getOpUserPhone());
		}
		
		if(isNeedSendtoBank)
		{
			bankProxyRequestService.CorpBindAuthen(request,appid);
		}

		return new FrontEndResponse("0","OK");
	}	
	
	private FrontEndResponse handleClose(CorpBindAuthenReqParam request,CorpInfoDTO corpInfoDTO,CorpAppRelationDTO corpAppRelationDTO,String appid, boolean isCorpInfoNeedUpdate,boolean isCorpAppRelationNeedUpdate)
	{		
		if(null == corpAppRelationDTO)
		{
			throw new BizException(BizError.CORP_APP_RELATION_NOT_EXIST.errorCode(),"CorpAppRelation not exist.");	
		}
		if(!corpAppRelationDTO.getBankType().equals(request.getBankType()))
		{
			throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"bankType inconsistence."); 
		}
		if(isCorpInfoNeedUpdate )
		{
			throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"Corpinfo is inconsistence."); 
		}
		
		if(isCorpAppRelationNeedUpdate)
		{
			throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"CorpAppRelation is inconsistence."); 
		}
		
		if(corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Open))
		{
			corpAppRelationService.updateStatusCloseFromOpen(request.getCorpId(), appid);
		}
		else if(corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Stop))
		{
			corpAppRelationService.updateStatusCloseFromStop(request.getCorpId(), appid);
		}
		
		if(corpAppRelationDTO.getAuthenStatus() == CorpAppRelationAuthenStatus.Normal)
		{
			bankProxyRequestService.CorpBindAuthen(request,appid);
		}
		
		return new FrontEndResponse("0","OK");
	}
	
	private FrontEndResponse handleStop(CorpBindAuthenReqParam request,CorpInfoDTO corpInfoDTO,CorpAppRelationDTO corpAppRelationDTO, String appid, boolean isCorpInfoNeedUpdate,boolean isCorpAppRelationNeedUpdate)
	{
		if(null == corpAppRelationDTO)
		{
			throw new BizException(BizError.CORP_APP_RELATION_NOT_EXIST.errorCode(),"CorpAppRelation not exist.");	
		}
		
		if(!corpAppRelationDTO.getBankType().equals(request.getBankType()))
		{
			throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"bankType inconsistence."); 
		}
		if(corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Close))
		{
			throw new BizException(BizError.UNSUPPORTED_RELATION_STATUS_ERROR.errorCode(),"UNSUPPORTED_RELATION_STATUS_ERROR.");	
		}
		if(isCorpInfoNeedUpdate )
		{
			throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"Corpinfo is inconsistence."); 
		}
		
		if(isCorpAppRelationNeedUpdate)
		{
			throw new BizException(BizError.PARAM_INCONSISTENCE.errorCode(),"CorpAppRelation is inconsistence."); 
		}

		else if(corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Open))
		{
			corpAppRelationService.updateStatusStopFromOpen(request.getCorpId(), appid);
		}
		
		return new FrontEndResponse("0","OK");
	}
}
