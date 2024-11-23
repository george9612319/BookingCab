package com.example.BookingCab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.BookingCab.dao.CabRepository;
import com.example.BookingCab.model.Cab;

@Component
public class BookCabService implements BookCabServiceInterface {
	
		@Autowired
		CabRepository cabRepository;
	

	@Override
	public Cab bookCab(Cab cab) {
		
		return cabRepository.save(cab);
	}

	@Override
	public Optional<Cab> getById(Integer id) {
		
		return cabRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		cabRepository.deleteById(id);
		
	}

	@Override
	public List<Cab> getAllCab() {
		
		List<Cab> AllCab = new ArrayList<>();
		
		cabRepository.findAll().forEach(AllCab::add);
		
		return AllCab;
	}

	@Override
	public void deleteAll() {
		cabRepository.deleteAll();
		// TODO Auto-generated method stub
		
	}

}
