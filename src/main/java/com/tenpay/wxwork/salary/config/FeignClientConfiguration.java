package com.tenpay.wxwork.salary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Request;
import feign.Logger;

// @Configuration // XXX not need?
public class FeignClientConfiguration {
	@Value("${feign.connectTimeoutMillis:5000}")
	private int connectTimeoutMillis;

	@Value("${feign.readTimeoutMillis:5000}")
	private int	readTimeoutMillis;

	@Bean
	public Request.Options options() {
        return new Request.Options(6000,6000);
    }

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
