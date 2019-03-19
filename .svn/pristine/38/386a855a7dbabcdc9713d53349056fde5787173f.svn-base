package com.tenpay.wxwork.salary.service.wxauth;



import java.util.Calendar;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.JsonObject;



@FeignClient(name = "feignConsumer", url = "127.0.0.1:8081")
public interface FeignConsumer {
	@RequestMapping(method = RequestMethod.POST, value = "/service/get_suite_token", consumes = "application/json")
	public JsonObject get_suite_token(@RequestBody JsonObject req);	
}
