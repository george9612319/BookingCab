package com.example.BookingCab.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CACULATEFARE", url= "http://localhost:8091")// server name in Eureka
public interface CabFeignClient {
	
	@GetMapping("/calculate")
	ResponseEntity<Double> calculateFare(@RequestParam String fromLocation, 
										@RequestParam String toLocation, 
										@RequestParam String typeOfCab);
}
