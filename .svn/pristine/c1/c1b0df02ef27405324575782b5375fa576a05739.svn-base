package com.tenpay.wxwork.salary.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tenpay.wxwork.salary.dto.CorpBindAuthenReqParam;
import com.tenpay.wxwork.salary.dto.CorpBindConfirmReqParam;
import com.tenpay.wxwork.salary.dto.admin.SalaryApprovalResponse;
import com.tenpay.wxwork.salary.dto.admin.SalaryFlowSubmitReqParam;
import com.tenpay.wxwork.salary.dto.admin.QueryAuthStatusReqParam;

import feign.Logger;
import feign.codec.Encoder;
import feign.jackson.JacksonEncoder;

@FeignClient(name = "ApprovalClient",
			url = "${approval.baseurl}",
			configuration = ApprovalClient.MultipartEncoderConfig.class
)
public interface ApprovalClient {
	/**
	 * 查询认证状态
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,
            value = "/approval/queryAuthStatus",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SalaryApprovalResponse queryAuthStatus(@RequestBody QueryAuthStatusReqParam request);
	
	/**
	 * 发送验证码
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,
            value = "/approval/corpBindAuthen",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SalaryApprovalResponse corpBindAuthen(@RequestBody CorpBindAuthenReqParam request);
	
	/**
	 * 提交认证信息
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,
            value = "/approval/corpBindConfirm",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SalaryApprovalResponse corpBindConfirm(@RequestBody CorpBindConfirmReqParam request);
	
	/**
	 * 工资明细提交银行
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,
            value = "/approval/salaryFlowSubmit",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SalaryApprovalResponse salaryFlowSubmit(@RequestBody SalaryFlowSubmitReqParam request);
	
	public class MultipartEncoderConfig {

        // // 代理设置，方便本地开发
        // @Bean
        // public Client feignClient() {
        //     System.out.println("--- create feign client using proxy");
        //     Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("web-proxy.tencent.com", 8080));
        //     OkHttpClient okHttpClient = new OkHttpClient.Builder().proxy(proxy).build();
        //     return new feign.okhttp.OkHttpClient(okHttpClient);
        // }

        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }

        @Bean
        public Encoder feignEncoder() {
            return new JacksonEncoder();
        }       
    }
}
