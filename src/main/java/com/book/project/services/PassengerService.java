package com.book.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.book.project.FlightManagementApplication;
import com.book.project.dao.PassengerDao;
import com.book.project.dao.PaymentDao;
import com.book.project.dto.ResponseStrucutre;
import com.book.project.entities.Booking;
import com.book.project.entities.Flight;
import com.book.project.entities.Passenger;
import com.book.project.enums.BookingStatus;
import com.book.project.enums.PassengerGender;
import com.book.project.exceptions.BookingNotFoundException;
import com.book.project.exceptions.IdNotFoundException;
import com.book.project.exceptions.NoRecordException;
import com.book.project.exceptions.PassengerNotFoundException;

@Service
public class PassengerService {

	
	@Autowired
	private PassengerDao passengerDao;


	
	public ResponseEntity<ResponseStrucutre<List<Passenger>>> getAllPassengers() {
		ResponseStrucutre<List<Passenger>> res = new ResponseStrucutre<>();
		res.setData(passengerDao.getAllPassengers());
		res.setMessage("Successful");
		res.setStausCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	
	
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> getByBookingId(int id) {
		
		Optional<Passenger> opt = passengerDao.getPassengerById(id);
		
		if(!opt.isEmpty()) {
			ResponseStrucutre<Optional<Passenger>> res = new ResponseStrucutre<>();
			res.setData(opt);
			res.setStausCode(HttpStatus.OK.value());
			res.setMessage("Success");
			return new ResponseEntity<>(res,HttpStatus.OK);	
		}	
		else {
			throw new PassengerNotFoundException("Invalid ID");
		}
		
	}
	
	
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> getPassengerByPhone(Long contact){
		
		Optional<Passenger> opt = passengerDao.getPassengerByPhone(contact);
		
		if(!opt.isEmpty()) {
			ResponseStrucutre<Optional<Passenger>> res = new ResponseStrucutre<>();
			res.setData(opt);
			res.setStausCode(HttpStatus.OK.value());
			res.setMessage("Success");
			return new ResponseEntity<>(res,HttpStatus.OK);	
		}	
		else {
			throw new PassengerNotFoundException("Invalid Phone");
		}
	}
	
	
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> updataAge(int id, int age){
		
		Optional<Passenger> opt= passengerDao.getPassengerById(id);
		
		if(opt.isPresent()) {
			ResponseStrucutre<Optional<Passenger>> res= new ResponseStrucutre<>();
				opt.get().setAge(age);
				
				res.setData(opt);
				res.setMessage("SUCCESSFUL");
				res.setStausCode(HttpStatus.ACCEPTED.value());
				
				passengerDao.savePassenger(opt.get());
				
				return new ResponseEntity<>(res,HttpStatus.OK);
			
		}
		else {
			throw new PassengerNotFoundException("Invalied ID");
		}
	}
	
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> updataName(int id, String name){
		
		Optional<Passenger> opt= passengerDao.getPassengerById(id);
		
		if(opt.isPresent()) {
			ResponseStrucutre<Optional<Passenger>> res= new ResponseStrucutre<>();
				opt.get().setName(name);
				
				res.setData(opt);
				res.setMessage("SUCCESSFUL");
				res.setStausCode(HttpStatus.ACCEPTED.value());
				
				passengerDao.savePassenger(opt.get());
				
				return new ResponseEntity<>(res,HttpStatus.OK);
			
		}
		else {
			throw new PassengerNotFoundException("Invalied ID");
		}
	}
	
	
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> updataPhone(int id, Long phone){
		
		Optional<Passenger> opt= passengerDao.getPassengerById(id);
		
		if(opt.isPresent()) {
			ResponseStrucutre<Optional<Passenger>> res= new ResponseStrucutre<>();
				try {
					opt.get().setContactNo(phone);
					
					res.setData(opt);
					res.setMessage("SUCCESSFUL");
					res.setStausCode(HttpStatus.ACCEPTED.value());
					
					passengerDao.savePassenger(opt.get());
					
					return new ResponseEntity<>(res,HttpStatus.OK);
					
				}catch (Exception e) {
					throw new NoRecordException("Duplicate Phone");
				}
				
				
			
		}
		else {
			throw new PassengerNotFoundException("Invalied ID");
		}
	}
	
	public ResponseEntity<ResponseStrucutre<Optional<Passenger>>> updateGender(int id,String status) {
		Optional<Passenger> opt= passengerDao.getPassengerById(id);
		
		if(opt.isPresent()) {
			ResponseStrucutre<Optional<Passenger>> res= new ResponseStrucutre<>();
			if(isValidStatus(status)) {
				opt.get().setGender(PassengerGender.valueOf(status));
				res.setData(opt);
				passengerDao.savePassenger(opt.get());
				res.setMessage("SUCCESSFUL");
				res.setStausCode(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<>(res,HttpStatus.OK);
			}
			else {
				throw new PassengerNotFoundException("STATUS NOT FOUND");
			}
			
		}
		else {
			throw new PassengerNotFoundException("Invalied ID");
		}
		
	}
	
	
	static public boolean isValidStatus(String value) {
	    try {
	        PassengerGender.valueOf(value);
	        return true;
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	}
	
	
	
	public ResponseEntity<ResponseStrucutre<Flight>> getPassengersFlight(int id){
		
		Optional<Passenger> opt = passengerDao.getPassengerById(id);
		if(opt.isPresent()) {
			ResponseStrucutre<Flight> res = new ResponseStrucutre<Flight>();
			
			res.setData(opt.get().getBooking().getFlight());
			res.setMessage("SuccessFull");
			res.setStausCode(HttpStatus.OK.value());
			
			return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException("Invalied ID");
		
	}
	
	
	public ResponseEntity<ResponseStrucutre<Passenger>> updatePassengerInfo(Passenger passenger) {

	    Optional<Passenger> opt = passengerDao.getPassengerById(passenger.getId());

	    if (opt.isPresent()) {

	        Passenger checking = opt.get();

	        checking.setName(passenger.getName());
	        checking.setAge(passenger.getAge());
	        checking.setGender(passenger.getGender());
	        checking.setContactNo(passenger.getContactNo());
	        checking.setSeatNumber(passenger.getSeatNumber());

	        if (passenger.getBooking() != null) {
	            checking.setBooking(passenger.getBooking());
	        }

	        passengerDao.savePassenger(checking);

	        ResponseStrucutre<Passenger> res = new ResponseStrucutre<>();
	        res.setData(checking);
	        res.setMessage("Successful");
	        res.setStausCode(HttpStatus.OK.value());

	        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	    }

	    throw new IdNotFoundException("Invalid ID");
	}

	
	
	
}










































