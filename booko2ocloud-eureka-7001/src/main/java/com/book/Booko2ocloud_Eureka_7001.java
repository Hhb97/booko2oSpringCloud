package com.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Booko2ocloud_Eureka_7001 {
	public static void main(String args[]) {
		SpringApplication.run(Booko2ocloud_Eureka_7001.class, args);
	}
}
