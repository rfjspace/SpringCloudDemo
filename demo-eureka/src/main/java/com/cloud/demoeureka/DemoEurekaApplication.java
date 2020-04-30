package com.cloud.demoeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DemoEurekaApplication {

	public static void main(String[] args) {
		System.out.println("===============启动开始==============");
		SpringApplication.run(DemoEurekaApplication.class, args);
		System.out.println("===============启动完成==============");
	}

}
