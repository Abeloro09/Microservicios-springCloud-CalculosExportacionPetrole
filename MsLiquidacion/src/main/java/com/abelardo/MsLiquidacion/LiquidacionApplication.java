package com.abelardo.MsLiquidacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LiquidacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquidacionApplication.class, args);
	}

}
