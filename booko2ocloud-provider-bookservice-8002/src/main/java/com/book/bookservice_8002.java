package com.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan(basePackages = "com.book.dao")
@EnableEurekaClient
public class bookservice_8002 {
	public static void main(String args[]) {
		SpringApplication.run(bookservice_8002.class, args);
	}
}
