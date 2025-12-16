package com.book.project.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.project.entities.Booking;
import com.book.project.entities.Flight;
import com.book.project.enums.BookingStatus;

public interface BookingRepo extends JpaRepository<Booking, Integer>{
	
	
	public List<Booking> findByFlight(Flight flight);

	public List<Booking> findByBookingDate(LocalDateTime bookingDate);
	
	public List<Booking> findByStatus(BookingStatus status);
	
	

}
