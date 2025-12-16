package com.book.project.controllers;
import com.book.project.services.PaymentsService;

import java.util.List;
import java.util.Optional;
import java.util.jar.Attributes.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.project.services.PassengerService;
import com.book.project.dto.ResponseStrucutre;
import com.book.project.entities.Flight;
import com.book.project.entities.Passenger;

@RestController
@RequestMapping("/passenger")
public class PassengerController {


	@Autowired
	private PassengerService passengerService;
	
	@GetMapping
	public ResponseEntity<ResponseStrucutre<List<Passenger>>> getAllPassengers(){
		return passengerService.getAllPassengers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> getPassenger(@PathVariable int id){
		return passengerService.getByBookingId(id);
	}
	
	@GetMapping("/phone/{contact}")
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> getPassengerById(@PathVariable Long contact){
		return passengerService.getPassengerByPhone(contact);
	}
	
	
	@PutMapping("/{id}/age/{age}")
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> updataAge(@PathVariable int id,@PathVariable int age){
		return passengerService.updataAge(id, age);
	}
	
	
	@PutMapping("/{id}/name/{name}")
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> updataAge(@PathVariable int id,@PathVariable String name){
		return passengerService.updataName(id, name);
	}
	
	
	@PutMapping("/{id}/phone/{pone}")
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> updataPhone(@PathVariable int id,@PathVariable Long pone){
		return passengerService.updataPhone(id, pone);
	}
	
	@PutMapping("/{id}/gender/{status}")
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> updateGender(@PathVariable int id,@PathVariable String status){
		return passengerService.updateGender(id, status.toUpperCase());
	}
	
	@GetMapping("/flight/{id}")
	public ResponseEntity<ResponseStrucutre<Flight>> getPassengersFlight(@PathVariable int id){
		return passengerService.getPassengersFlight(id);
	}
	
	@PutMapping("/updateInfo")
	public ResponseEntity<ResponseStrucutre<Passenger>> updatePassengerInfo(@RequestBody Passenger passenger){
		return passengerService.updatePassengerInfo(passenger);
	}
	
}


















