package com.book.project.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.project.dto.ResponseStrucutre;
import com.book.project.entities.Booking;
import com.book.project.entities.Passenger;
import com.book.project.entities.Payment;
import com.book.project.enums.BookingStatus;
import com.book.project.services.BookingService;


@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<ResponseStrucutre<Booking>> saveBooking(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking); 
	}
	
	@GetMapping
	public ResponseEntity<ResponseStrucutre<List<Booking>>> getAllBooking(){
		return bookingService.getAllBooking();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStrucutre<Optional<Booking>>> getByBookingId(@PathVariable int id){
		return bookingService.getByBookingId(id);
	}
	
	@GetMapping("/flight/{id}")
	public ResponseEntity<ResponseStrucutre<List<Booking>>> getBookingByFlight(@PathVariable int id){
		return bookingService.getBookingflight(id);
	}
	
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStrucutre<List<Booking>>> getBookingByFlight(@PathVariable LocalDateTime date){
		return bookingService.getByBookingDate(date);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStrucutre<List<Booking>>> getBookingByFlight(@PathVariable BookingStatus status){
		return bookingService.getByBookingStatus(status);
	}
	
	@GetMapping("/{id}/passengers")
	public ResponseEntity<ResponseStrucutre<List<Passenger>>> getPassengerByBooking(@PathVariable int id){
		return bookingService.getByBookingPassenger(id);
	}
	
	@GetMapping("/{id}/payment")
	public ResponseEntity<ResponseStrucutre<Payment>> getPassengerPayment(@PathVariable int id){
		return bookingService.getByBookingPayment(id);
	}
	
	@PutMapping("/{id}/{status}")
	public ResponseEntity<ResponseStrucutre<Booking>> updateBookingStatus(@PathVariable int id,@PathVariable String status){
		return bookingService.updateBookingStatus(id, status);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStrucutre<Booking>> deleteBooking(@PathVariable int id){
		return bookingService.deleteBooking(id);
	}
	
}


