package com.book.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.project.entities.Flight;

public interface FlightRepo extends JpaRepository<Flight,Integer>{
	
	public List<Flight> findBySourceAndDestination(String source, String destination);
	
	public List<Flight> findByAirline(String airline);

}
