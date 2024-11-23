package com.example.BookingCab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookingCabApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingCabApplication.class, args);
	}

}
