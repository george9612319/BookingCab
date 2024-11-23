package com.example.BookingCab.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BookingCab.config.CabFeignClient;
import com.example.BookingCab.model.Cab;
import com.example.BookingCab.service.BookCabService;

@Controller
public class ViewController {
	

	@Autowired
	private BookCabService bookCabService;

	@Autowired
	private CabFeignClient cabFeignClient;
	
	//For direct to index.html
	@GetMapping("/")
	public String home() {
		return "index"; // return index.html
	}

	//For direct to book-cab.html
	@GetMapping("/book-cab")
	public String bookcab() {
		return "book-cab"; // return book-cab.html
	}

	//For direct to booking-details.html to show the information of booking cab
	@GetMapping("/view-booking")
	public String viewBooking(@RequestParam("cabId") Integer cabId, Model model) {

		Optional<Cab> cabOpt = bookCabService.getById(cabId); // get cab from DB

		// Check if successfully retrieve cab information and return to
		// booking-details.html
		if (cabOpt.isPresent()) {
			Cab cab = cabOpt.get();
			model.addAttribute("cab", cab);
			return "booking-details";
		} else {
			return "error";
		}
	}

	//For direct to calculateFare.html
	@GetMapping("/calculateFare")
	public String showCalculateFareForm() {
		return "calculateFare"; // return  calculateFare.html
	}

	//For direct to fare-detail.html to show the fare calculate result
	@GetMapping("/fare-detail")
	public String calculateFareForm(@RequestParam String fromLocation,
									@RequestParam String toLocation,
									@RequestParam String typeOfCab,
									@RequestParam Integer passenger,
									Model model) {
		Cab cab = new Cab();
	    cab.setFromLocation(fromLocation);
	    cab.setToLocation(toLocation);
	    cab.setTypeOfCab(typeOfCab);
	    cab.setPassenger(passenger);
	    

	    ResponseEntity<Double> calcFareResponse = cabFeignClient.calculateFare(cab.getFromLocation(),
				cab.getToLocation(), cab.getTypeOfCab());
		Double rate = calcFareResponse.getBody();
		cab.setRate(rate);
		
		model.addAttribute("cab", cab);

		return "fare-detail"; // return to fare-detail html
	}
	
	//For direct to view-cab.html to show all cabs
	@GetMapping("/view-cab")
	public String viewAllCabs(Model model) {
		// get cabList from DB
		List<Cab> cabList = bookCabService.getAllCab();
		// add cabList into model
		model.addAttribute("cabList", cabList);
		// return it to "view-cabs"
		return "view-cab";
	}

}
