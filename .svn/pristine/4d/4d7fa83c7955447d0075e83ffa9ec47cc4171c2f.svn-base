package com.tenpay.wxwork.salary;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tenpay.bap.monitor.udp.MonitorUdpLogConfig;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages={"com.tenpay.wxwork.salary", "com.tenpay.wxwork.wxworklib"})
@Import(MonitorUdpLogConfig.class)
@EnableFeignClients
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		String logHome = System.getProperty("log.home");
		String logPath = "";

		if (StringUtils.isNotEmpty(logHome)) {
			logPath = logHome + File.separator;
		}
		System.out.println("logPath: " + logPath);

		System.setProperty("spring.pid.file", logPath + "application.pid");

		// ApplicationPidFileWriter pidFileWriter = new ApplicationPidFileWriter();
		// pidFileWriter.setTriggerEventType(ApplicationReadyEvent.class);
		// application.addListeners(pidFileWriter);
		// application.addListeners((ApplicationListener<ApplicationFailedEvent>) event -> {
		// 	logger.error("spring init error exit process", event.getException());
		// 	System.exit(-2);
		// });

		application.run(args);
	}
}
