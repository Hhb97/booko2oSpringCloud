package com.book;

import javax.swing.Spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.book.dao")
public class cartservice_9002 {
	public static void main(String args[]) {
		SpringApplication.run(cartservice_9002.class, args);
	}
}
