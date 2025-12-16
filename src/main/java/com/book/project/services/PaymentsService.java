package com.book.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.book.project.dao.PaymentDao;
import com.book.project.dto.ResponseStrucutre;
import com.book.project.entities.Passenger;
import com.book.project.entities.Payment;
import com.book.project.enums.PassengerGender;
import com.book.project.enums.PaymentMode;
import com.book.project.enums.PaymentStatus;
import com.book.project.exceptions.PassengerNotFoundException;
import com.book.project.exceptions.PaymentNotFound;

@Service
public class PaymentsService {

	@Autowired
	private PaymentDao paymentDao;
	
	public ResponseEntity<ResponseStrucutre<List<Payment>>> getAllPassengers() {
		ResponseStrucutre<List<Payment>> res = new ResponseStrucutre<>();
		res.setData(paymentDao.getAllPayment());
		res.setMessage("Successful");
		res.setStausCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	
	public ResponseEntity<ResponseStrucutre<Optional<Payment>>> getByPaymantId(int id) {
		
		Optional<Payment> opt = paymentDao.getPaymentById(id);
		
		if(!opt.isEmpty()) {
			ResponseStrucutre<Optional<Payment>> res = new ResponseStrucutre<>();
			res.setData(opt);
			res.setStausCode(HttpStatus.OK.value());
			res.setMessage("Success");
			return new ResponseEntity<>(res,HttpStatus.OK);	
		}	
		else {
			throw new PassengerNotFoundException("Invalid ID");
		}
		
	}
	
	
	public ResponseEntity<ResponseStrucutre<List<Payment>>> getByPaymantStatus(PaymentStatus status) {
	    
	    List<Payment> opt = paymentDao.getPaymentByStatus(status);

	    if (opt.size()>0) {
	        ResponseStrucutre<List<Payment>> res = new ResponseStrucutre<>();
	        res.setData(opt);
	        res.setStausCode(HttpStatus.OK.value());
	        res.setMessage("Success");

	        return new ResponseEntity<>(res, HttpStatus.OK);
	    } else {
	        throw new PaymentNotFound("Payment status not found");
	    }
	}

	public ResponseEntity<ResponseStrucutre<List<Payment>>> getByPaymantMode(PaymentMode status) {
	    
	    List<Payment> opt = paymentDao.getPaymentByMode(status);

	    if (opt.size()>0) {
	        ResponseStrucutre<List<Payment>> res = new ResponseStrucutre<>();
	        res.setData(opt);
	        res.setStausCode(HttpStatus.OK.value());
	        res.setMessage("Success");

	        return new ResponseEntity<>(res, HttpStatus.OK);
	    } else {
	        throw new PaymentNotFound("Payment status not found");
	    }
	}
	
	
	public ResponseEntity<ResponseStrucutre<Optional<Payment>>> updateStatus(int id,String status) {
		Optional<Payment> opt= paymentDao.getPaymentById(id);
		
		if(opt.isPresent()) {
			ResponseStrucutre<Optional<Payment>> res= new ResponseStrucutre<>();
			if(isValidStatus(status)) {
				opt.get().setStatus(PaymentStatus.valueOf(status));
				res.setData(opt);
				paymentDao.savePayment(opt.get());
				res.setMessage("SUCCESSFUL");
				res.setStausCode(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<>(res,HttpStatus.OK);
			}
			else {
				throw new PaymentNotFound("STATUS NOT FOUND");
			}
			
		}
		else {
			throw new PaymentNotFound("Invalied ID");
		}
		
	}
	
	
	static public boolean isValidStatus(String value) {
	    try {
	        PaymentStatus.valueOf(value);
	        return true;
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	}
	
	
	public ResponseEntity<ResponseStrucutre<List<Payment>>> getByPaymantMoreThenPrice(double amount) {
	    
	    List<Payment> opt = paymentDao.getPaymentMoreThenPrice(amount);

	    if (opt.size()>0) {
	        ResponseStrucutre<List<Payment>> res = new ResponseStrucutre<>();
	        res.setData(opt);
	        res.setStausCode(HttpStatus.OK.value());
	        res.setMessage("Success");

	        return new ResponseEntity<>(res, HttpStatus.OK);
	    } else {
	        throw new PaymentNotFound("Payment Amount Not Found");
	    }
	}
	
	
	
}
































