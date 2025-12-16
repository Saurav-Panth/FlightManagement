package com.book.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.book.project.entities.Payment;
import com.book.project.enums.PaymentMode;
import com.book.project.enums.PaymentStatus;

public interface PaymentRepo extends JpaRepository<Payment, Integer>{

	List<Payment> findByStatus(PaymentStatus status);

	List<Payment> findByMode(PaymentMode status);
	
	@Query("SELECT p FROM Payment p WHERE p.amount > :amount")
	List<Payment> findPaymentsWhereAmountGreaterThan(double amount);


}
