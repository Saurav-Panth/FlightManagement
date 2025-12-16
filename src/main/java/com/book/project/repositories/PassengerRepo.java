package com.book.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.project.entities.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Integer>{

	Optional<Passenger> findByContactNo(Long contact);

}
