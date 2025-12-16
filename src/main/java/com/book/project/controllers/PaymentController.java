package com.book.project.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.project.dto.ResponseStrucutre;
import com.book.project.entities.Payment;
import com.book.project.enums.PaymentStatus;
import com.book.project.services.PassengerService;
import com.book.project.services.PaymentsService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PassengerService passengerService;

	@Autowired
	private PaymentsService paymentsService;

    PaymentController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
	
	@GetMapping
	public ResponseEntity<ResponseStrucutre<List<Payment>>> getAllPassengers(){
		return paymentsService.getAllPassengers(); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStrucutre<Optional<Payment>>> getByPaymantId(@PathVariable int id){
		return paymentsService.getByPaymantId(id);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStrucutre<List<Payment>>> getByPaymantStatus(@PathVariable PaymentStatus status){
		return paymentsService.getByPaymantStatus(status);
	}
	
	
	@PutMapping("/{id}/status/{status}")
	public ResponseEntity<ResponseStrucutre<Optional<Payment>>> updateStatus(@PathVariable int id,@PathVariable String status){
		return paymentsService.updateStatus(id, status.toUpperCase());
	}
	
	@GetMapping("/morethen/{amount}")
	public ResponseEntity<ResponseStrucutre<List<Payment>>> updateStatus(@PathVariable double amount){
		return paymentsService.getByPaymantMoreThenPrice(amount);
	}
	
	
}


















