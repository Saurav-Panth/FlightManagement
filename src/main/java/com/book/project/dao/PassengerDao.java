package com.book.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.project.entities.Passenger;
import com.book.project.repositories.PassengerRepo;

@Repository
public class PassengerDao {

	@Autowired
	private PassengerRepo passengerRepo;

	public List<Passenger> getAllPassengers() {
		return passengerRepo.findAll();
	}
	
	public Optional<Passenger> getPassengerById(int id) {
		return passengerRepo.findById(id);
	}
	
	public Optional<Passenger> getPassengerByPhone(Long contact) {
		return passengerRepo.findByContactNo(contact);
	}
	
	public void savePassenger(Passenger passenger) {
		passengerRepo.save(passenger);
	}
	
	
	

}
