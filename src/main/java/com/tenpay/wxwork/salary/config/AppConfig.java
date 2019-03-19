package com.tenpay.wxwork.salary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.tenpay.bap.client.config.BapRelayConfig;

@Configuration
@Import(value = { BapRelayConfig.class })
public class AppConfig {

}
