package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.model.CardBin;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpay.wxwork.salary.model.OpenAccountInfo;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.GetUserInfoResponse;
import com.tenpay.wxwork.salary.model.GetUserInfo;
import com.tenpay.wxwork.salary.service.h5.GetUserInfoService;

/**
 * @author : CZK
 * @description :
 * @date : 2018/8/22
 */
@RestController
public class GetUserInfoController
{
	@Autowired
	private GetUserInfoService getUserInfoService;

	@Autowired
    private SessionService sessionService;

	@PostMapping(value = "/h5/get_user_info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object getUserInfoResponse(@CookieValue("ssid") String ssid)
	{
		GetUserInfo userInfo = getUserInfoService.getUserInfo(ssid);

        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }

		if (userInfo != null)
		{
			return new GetUserInfoResponse(userInfo,sessionInfo.getBankChNameOne(),sessionInfo.getBankChNameTwo());
		} else
		{
			throw new BizException(BizError.NOT_GET_ACCONT_INFO.errorCode(), BizError.NOT_GET_ACCONT_INFO.errorMsg());
		}

	}
}
