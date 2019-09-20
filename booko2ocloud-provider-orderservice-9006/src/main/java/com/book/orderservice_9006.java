package com.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.book.dao")
public class orderservice_9006 {
	public static void main(String args[]) {
		SpringApplication.run(orderservice_9006.class, args);
	}
}
