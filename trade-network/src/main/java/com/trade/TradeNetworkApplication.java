package com.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TradeNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeNetworkApplication.class, args);

		System.out.println("Hello World!");
	}

}
