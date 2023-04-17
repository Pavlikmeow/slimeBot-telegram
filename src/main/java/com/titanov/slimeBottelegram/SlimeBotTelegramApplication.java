package com.titanov.slimeBottelegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SlimeBotTelegramApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlimeBotTelegramApplication.class, args);
	}

}
