package com.abelardo.MsAnalisis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class MsAnalisisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAnalisisApplication.class, args);
	}

}
