package com.example.BookingCab;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.BookingCab.controller.BookController;
import com.example.BookingCab.controller.ViewController;

@SpringBootTest
class BookingCabApplicationTests {

	@Autowired
	BookController bookController;
	
	@Autowired
	ViewController viewController;
	
	@Test
	public void contextLoad() throws Exception{
		
		assertThat(bookController).isNotNull();
	}
	
	@Test
public void viewLoad() throws Exception{
		
		assertThat(viewController).isNotNull();
	}

}
