package com.book.project.controllers;

import java.util.*;

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
import com.book.project.entities.Flight;
import com.book.project.services.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	 private FlightService flightService;

	@PostMapping
	public ResponseEntity<ResponseStrucutre<Flight>> saveFlight(@RequestBody Flight flight) {
		return flightService.saveFlight(flight);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseStrucutre<List<Flight>>> saveFlight() {
		return flightService.getAllFlight();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStrucutre<Optional<Flight>>> getByFlightId(@PathVariable int id) {
		return flightService.getByFlightId(id);
	}
	
	@GetMapping("/{source}/{destination}")
	public ResponseEntity<ResponseStrucutre<List<Flight>>> getByFlightId(@PathVariable String source,@PathVariable String destination) {
		return flightService.getFlightsBySosurceAndDestination(source, destination);
	}
	
	
	@GetMapping("airline/{airline}")
	public ResponseEntity<ResponseStrucutre<List<Flight>>> getByFlightId(@PathVariable String airline) {
		return flightService.getByFlightAirline(airline);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStrucutre<Flight>> updateFlight(@RequestBody Flight flight) {
		return flightService.updateFlight(flight);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStrucutre<Flight>> updateFlight(@PathVariable int id) {
		return flightService.deleteFlight(id);
	}
	
	
	
}
