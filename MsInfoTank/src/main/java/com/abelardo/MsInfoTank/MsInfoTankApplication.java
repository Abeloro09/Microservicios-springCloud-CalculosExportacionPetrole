package com.abelardo.MsInfoTank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsInfoTankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsInfoTankApplication.class, args);
	}

}
