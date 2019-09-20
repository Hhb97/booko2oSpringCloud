package com.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.fegin.service"})

public class booko2o_Feigin_9000 {
	public static void main (String args[]) {
		SpringApplication.run(booko2o_Feigin_9000.class, args);
	}
}
