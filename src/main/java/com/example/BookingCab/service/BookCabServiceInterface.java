package com.example.BookingCab.service;

import java.util.List;
import java.util.Optional;

import com.example.BookingCab.model.Cab;

public interface BookCabServiceInterface {
	
	Cab bookCab(Cab cab);
	
	Optional<Cab> getById(Integer id);
	
	void deleteById(Integer id);
	
	List<Cab> getAllCab();
	
	void deleteAll();

}
