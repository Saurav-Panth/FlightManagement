package com.book.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.project.entities.Payment;
import com.book.project.enums.PaymentMode;
import com.book.project.enums.PaymentStatus;
import com.book.project.repositories.PaymentRepo;

@Repository
public class PaymentDao {
	
	@Autowired
	private PaymentRepo paymentRepo;

	public Payment savePayment(Payment payment) {
		return paymentRepo.save(payment);
	}
	
	
	public List<Payment> getAllPayment() {
		return paymentRepo.findAll();
	}
	
	public Optional<Payment> getPaymentById(int id) {
		return paymentRepo.findById(id);
	}
	
	public List<Payment> getPaymentByStatus(PaymentStatus status) {
	    return paymentRepo.findByStatus(status);
	}

	
	public List<Payment> getPaymentByMode(PaymentMode status) {
	    return paymentRepo.findByMode(status);
	}

	public List<Payment> getPaymentMoreThenPrice(Double amount) {
	    return paymentRepo.findPaymentsWhereAmountGreaterThan(amount);
	}
	
}
