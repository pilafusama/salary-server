package com.tenpay.wxwork.salary.provider.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.CorpBindAuthenReqParam;
import com.tenpay.wxwork.salary.dto.CorpBindConfirmReqParam;
import com.tenpay.wxwork.salary.dto.admin.SalaryApprovalResponse;
import com.tenpay.wxwork.salary.dto.admin.SalaryApprovalStateResponse;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.IdGenerator;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.admin.SalaryApprovalService;

@RestController
@RequestMapping("/admin")
public class SalaryApprovalController {
	@Autowired
    private SessionService sessionService;
	
	@Autowired
	private SalaryApprovalService salaryApprovalService;
	
	@Autowired
    private IdGenerator idGenerator;
	
	/**
	 * 查询认证状态（本期用于招行）
	 * @param ssid
	 * @return
	 */
	@PostMapping(value = "/query_auth_status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object queryAuthStatus(@CookieValue("ssid") String ssid) {
		if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
		SessionInfo sessionInfo = sessionService.getSession(ssid);
		if (sessionInfo == null || !"CMB".equals(sessionInfo.getBankSname()))
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }

		String corpid = sessionInfo.getCorpId();
		String bankType = sessionInfo.getBankType();
		SalaryApprovalResponse response = salaryApprovalService.queryAuthStatus(corpid, bankType);
		if ("0".equals(response.getRetCode()))
		{
			return new SalaryApprovalStateResponse("0", "OK", "0");
		}
		else
		{
			return new SalaryApprovalStateResponse("0", response.getErrMsg(), response.getRetCode());
		}

	}
	
	/**
	 * 点击“发送验证码”
	 * @param ssid
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/corp_bind_authen", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object corpBindAuthen(@CookieValue("ssid") String ssid, @RequestBody CorpBindAuthenReqParam request) {
		SessionInfo sessionInfo = sessionService.getSession(ssid);
		if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
		String corpId = sessionInfo.getCorpId();
		String userId = sessionInfo.getUserId();
		String bankType = sessionInfo.getBankType();
		request.setCorpId(corpId);
		request.setOpUserId(userId);
		request.setBankType(bankType);
		SalaryApprovalResponse response = salaryApprovalService.corpBindAuthen(request);
		if ("0".equals(response.getRetCode()))
		{
			return new SalaryApprovalStateResponse("0", "OK", "0");
		}
		else
		{
			return new SalaryApprovalStateResponse("0", response.getErrMsg(), response.getRetCode());
		}
		
	}
	
	/**
	 * 提交认证资料
	 * @param ssid
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/corp_bind_confirm", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object corpBindConfirm(@CookieValue("ssid") String ssid, @RequestBody CorpBindConfirmReqParam request) {
		SessionInfo sessionInfo = sessionService.getSession(ssid);
		if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
		String corpId = sessionInfo.getCorpId();
		String userId = sessionInfo.getUserId();
		String bankType = sessionInfo.getBankType();
		request.setCorpId(corpId);
		request.setOpUserId(userId);
		request.setBankType(bankType);
		SalaryApprovalResponse response = salaryApprovalService.corpBindConfirm(request);
		if ("0".equals(response.getRetCode()))
		{
			return new SalaryApprovalStateResponse("0", "OK", "0");
		}
		else
		{
			return new SalaryApprovalStateResponse("0", response.getErrMsg(), response.getRetCode());
		}
	}
	
}
