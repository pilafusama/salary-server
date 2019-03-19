package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.GetOpenAccountModeResponse;
import com.tenpay.wxwork.salary.dto.h5.GetUserInfoResponse;
import com.tenpay.wxwork.salary.model.GetUserInfo;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.h5.GetOpenAccountModeService;
import com.tenpay.wxwork.salary.service.h5.GetUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : v_lifangguo
 * @description :
 * @date : 2018/11/2
 */
@RestController
public class GetOpenAccountModeController
{
	@Autowired
	private GetOpenAccountModeService getOpenAccountModeService;

	@Autowired
    private SessionService sessionService;

	@PostMapping(value = "/h5/get_open_account_mode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object getOpenAccountModeResponse(@CookieValue("ssid") String ssid)
	{

        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }

		GetOpenAccountModeResponse response = getOpenAccountModeService.getOpenAccountMode(sessionInfo);

		return response;
	}
}
