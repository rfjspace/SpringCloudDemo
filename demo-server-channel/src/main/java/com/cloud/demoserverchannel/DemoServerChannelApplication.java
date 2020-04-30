package com.cloud.demoserverchannel;

import com.cloud.demojarcommon.annotation.EnableCommonAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@EnableCommonAdvice
@SpringBootApplication
public class DemoServerChannelApplication {

	public static void main(String[] args) {
		System.out.println("===============启动开始==============");
		SpringApplication.run(DemoServerChannelApplication.class, args);
		System.out.println("===============启动完成==============");
	}

}
