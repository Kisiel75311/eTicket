package com.electronicTicket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class ElectronicTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicTicketApplication.class, args);
	}

}
