package com.cloud.demoservertwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoServerOneApplication {

	public static void main(String[] args) {
		System.out.println("===============启动开始==============");
		SpringApplication.run(DemoServerOneApplication.class, args);
		System.out.println("===============启动完成==============");
	}

}
