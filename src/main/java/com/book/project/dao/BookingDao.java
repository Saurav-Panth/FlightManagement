package com.book.project.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.book.project.entities.Booking;
import com.book.project.entities.Flight;
import com.book.project.enums.BookingStatus;
import com.book.project.repositories.BookingRepo;

@Repository
public class BookingDao {
	
	@Autowired
	private BookingRepo bookingRepo;
	
	public Booking saveBooking(Booking booking) {
		return bookingRepo.save(booking);
	}
	
	public List<Booking> getALLBookings(){
		return bookingRepo.findAll();
	}
	
	public Optional<Booking> getBookingById(int id){
		return bookingRepo.findById(id);
	}
	
	public List<Booking> getBookingByFlight(Flight flight){
		return bookingRepo.findByFlight(flight);
	}
	
	
	public List<Booking> getBookingByDate(LocalDateTime bookingDate){
		return bookingRepo.findByBookingDate(bookingDate);
	}
	
	public List<Booking> getBookingByStatus(BookingStatus status){
		return bookingRepo.findByStatus(status);
	}
	
	
	public void deleteBooking(Booking booking){
		bookingRepo.delete(booking);
	}
	
}
	