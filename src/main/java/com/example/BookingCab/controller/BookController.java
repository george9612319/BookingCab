package com.example.BookingCab.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.BookingCab.config.CabFeignClient;
import com.example.BookingCab.model.Cab;
import com.example.BookingCab.service.BookCabService;

@RestController
@RequestMapping(path = "bookcab")
public class BookController {

	@Autowired
	BookCabService bookCabService;

	@Autowired
	CabFeignClient cabFeignClient;

	@PostMapping("/addcab")
	public RedirectView addCab(@RequestBody Cab cab) {

		// get rate from CalculateFare microserver
		ResponseEntity<Double> calcFareResponse = cabFeignClient.calculateFare(cab.getFromLocation(),
				cab.getToLocation(), cab.getTypeOfCab());
		// check if the CalculateFare is ok or not
		if (calcFareResponse.getStatusCode() != HttpStatus.OK) {
			System.err.println("Error fetching fare: " + calcFareResponse.getStatusCode());
			return new RedirectView("/error");
		}
		Double rate = calcFareResponse.getBody();

		cab.setRate(rate);

		// save cab information to DB
		Cab book = bookCabService.bookCab(cab);

		// send cabId to Viewcontroller and redirect to /view-booking?cabId=
		return new RedirectView("/view-booking?cabId=" + book.getCabId());
	}

	@GetMapping("/getallcab")
	public ResponseEntity<List<Cab>> getAllCabs() {

		List<Cab> cabList = bookCabService.getAllCab();

		return new ResponseEntity<>(cabList, HttpStatus.OK);
	}

	@GetMapping("/getcab")
	public ResponseEntity<Cab> getCabById(@RequestParam Integer id) {

		Optional<Cab> bookCab = bookCabService.getById(id);

		if (bookCab.isPresent()) {
			return new ResponseEntity<>(bookCab.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deletecab")
	public ResponseEntity<List<Cab>> deleteCabById(@RequestParam Integer id) {

		Optional<Cab> existingCab = bookCabService.getById(id); // Check the cab is existing or not
		if (!existingCab.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		bookCabService.deleteById(id); // delete cab by id

		List<Cab> cabList = bookCabService.getAllCab(); // get all cab after delete

		return new ResponseEntity<>(cabList, HttpStatus.OK); // return all cab
	}
	@DeleteMapping("/deleteallcabs")
	public ResponseEntity<Void> deleteAllCabs() {
	        bookCabService.deleteAll();
	        return new ResponseEntity<>(HttpStatus.OK);
	}
}
