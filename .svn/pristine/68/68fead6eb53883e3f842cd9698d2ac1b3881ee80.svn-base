package com.tenpay.wxwork.salary.provider.wxauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tenpay.wxwork.salary.dto.UserDTO;
import com.tenpay.wxwork.salary.dto.wxauth.LoginRequest;
import com.tenpay.wxwork.salary.dto.wxauth.LoginResponse;
import com.tenpay.wxwork.salary.service.UserService;
import com.tenpay.wxwork.salary.service.wxauth.WxAuthService;

@RestController
public class WxCallbackController {
	@Autowired
	private  WxAuthService  wxAuthService;
	
	@RequestMapping(value = "/wxwork/authcallback", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse login(@RequestBody LoginRequest req) {
		return wxAuthService.login(req);
	}
	
	@RequestMapping(value = "/wxwork/datacallback", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse getSession(@RequestBody LoginRequest req) {
		return wxAuthService.login(req);
	}	

}
