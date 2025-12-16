package com.book.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.book.project.dto.ResponseStrucutre;
import com.book.project.entities.Flight;
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ResponseStrucutre<String>> handleFNFE (FlightNotFoundException exception){
		ResponseStrucutre<String> response= new ResponseStrucutre<>();
		response.setData(exception.getMessage());
		response.setStausCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Invalid");
		
		ResponseEntity<ResponseStrucutre<String>> responseEntity = new ResponseEntity<ResponseStrucutre<String>>(response,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStrucutre<String>> handleIDNFE (IdNotFoundException exception){
		ResponseStrucutre<String> response= new ResponseStrucutre<>();
		response.setData(exception.getMessage());
		response.setStausCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Invalid");
		
		ResponseEntity<ResponseStrucutre<String>> responseEntity = new ResponseEntity<ResponseStrucutre<String>>(response,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@ExceptionHandler(PassengerNotFoundException.class)
	public ResponseEntity<ResponseStrucutre<String>> handlePNFE(PassengerNotFoundException exception){
		ResponseStrucutre<String> response= new ResponseStrucutre<>();
		response.setData(exception.getMessage());
		response.setStausCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Invalid");
		
		ResponseEntity<ResponseStrucutre<String>> responseEntity = new ResponseEntity<ResponseStrucutre<String>>(response,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ResponseStrucutre<String>> handleBNFE(BookingNotFoundException exception){
		ResponseStrucutre<String> response= new ResponseStrucutre<>();
		response.setData(exception.getMessage());
		response.setStausCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Invalid");
		
		ResponseEntity<ResponseStrucutre<String>> responseEntity = new ResponseEntity<ResponseStrucutre<String>>(response,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@ExceptionHandler(NoRecordException.class)
	public ResponseEntity<ResponseStrucutre<String>> handleNRE(NoRecordException exception){
		ResponseStrucutre<String> response= new ResponseStrucutre<>();
		response.setData(exception.getMessage());
		response.setStausCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Invalid");
		
		ResponseEntity<ResponseStrucutre<String>> responseEntity = new ResponseEntity<ResponseStrucutre<String>>(response,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@ExceptionHandler(PaymentNotFound.class)
	public ResponseEntity<ResponseStrucutre<String>> handlePNE(PaymentNotFound exception){
		ResponseStrucutre<String> response= new ResponseStrucutre<>();
		response.setData(exception.getMessage());
		response.setStausCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Invalid");
		
		ResponseEntity<ResponseStrucutre<String>> responseEntity = new ResponseEntity<ResponseStrucutre<String>>(response,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@ExceptionHandler(SeatNotAvailable.class)
	public ResponseEntity<ResponseStrucutre<String>> handleNRE(SeatNotAvailable exception){
		ResponseStrucutre<String> response= new ResponseStrucutre<>();
		response.setData(exception.getMessage());
		response.setStausCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Invalid");
		
		ResponseEntity<ResponseStrucutre<String>> responseEntity = new ResponseEntity<ResponseStrucutre<String>>(response,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
}
