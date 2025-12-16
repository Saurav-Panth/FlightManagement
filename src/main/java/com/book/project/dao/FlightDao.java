package com.book.project.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.project.entities.Flight;
import com.book.project.repositories.FlightRepo;

@Repository
public class FlightDao {

	@Autowired
	private FlightRepo flightRepo;
	
	public Flight saveFlight(Flight flight) {
		if(flight.getAirline().isBlank()) {
			new Flight(); 
		}
		return flightRepo.save(flight);
	}
	
	public List<Flight> getAllFlights(){
		return flightRepo.findAll();
	}
	
	public Optional<Flight> getByFlightId(int id) {
		return flightRepo.findById(id);
	}
	
	public List<Flight> getFlightsBySosurceAndDestination(String source, String destination){
		return flightRepo.findBySourceAndDestination(source, destination);
	}
	
	public List<Flight> getByFlightAirline(String airline) {
		return flightRepo.findByAirline(airline);
	}
	
	public void deleteFlight(int id) {
		 flightRepo.deleteById(id);
	}
	
	
	
}
