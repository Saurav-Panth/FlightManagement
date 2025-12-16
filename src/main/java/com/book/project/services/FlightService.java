package com.book.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.book.project.controllers.FlightController;
import com.book.project.dao.FlightDao;
import com.book.project.dto.ResponseStrucutre;
import com.book.project.entities.Flight;
import com.book.project.exceptions.FlightNotFoundException;
import com.book.project.exceptions.IdNotFoundException;
import com.book.project.exceptions.NoRecordException;

@Service
public class FlightService {

	

	@Autowired
	private FlightDao flightDao;

	public ResponseEntity<ResponseStrucutre<Flight>> saveFlight(Flight flight) {
			ResponseStrucutre<Flight> res = new ResponseStrucutre<>();
			res.setData(flightDao.saveFlight(flight));
			res.setStausCode(HttpStatus.ACCEPTED.value());
			res.setMessage("Success");
			return new ResponseEntity<ResponseStrucutre<Flight>>(res,HttpStatus.ACCEPTED);		
	
	}
	
	public ResponseEntity<ResponseStrucutre<List<Flight>>> getAllFlight(){
		ResponseStrucutre<List<Flight>> res = new ResponseStrucutre<>();
		res.setMessage("successful");
		res.setStausCode(HttpStatus.ACCEPTED.value());
		res.setData(flightDao.getAllFlights());
		
		return new ResponseEntity<ResponseStrucutre<List<Flight>>>(res,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStrucutre<Optional<Flight>>> getByFlightId(int id) {
		
		Optional<Flight> opt = flightDao.getByFlightId(id);
		
		if(!opt.isEmpty()) {
			ResponseStrucutre<Optional<Flight>> res = new ResponseStrucutre<>();
			res.setData(opt);
			res.setStausCode(HttpStatus.OK.value());
			res.setMessage("Success");
			return new ResponseEntity<ResponseStrucutre<Optional<Flight>>>(res,HttpStatus.OK);	
		}	
		else {
			throw new FlightNotFoundException("Invalid ID");
		}
		
		

	}
	
	
	public ResponseEntity<ResponseStrucutre<List<Flight>>> getFlightsBySosurceAndDestination(String source, String destination){
		
		List<Flight> opt = flightDao.getFlightsBySosurceAndDestination(source, destination);
		
		if(!opt.isEmpty()) {
			ResponseStrucutre<List<Flight>> res = new ResponseStrucutre<>();
			res.setData(opt);
			res.setStausCode(HttpStatus.OK.value());
			res.setMessage("Success");
			return new ResponseEntity<>(res,HttpStatus.OK);	
		}	
		else {
			throw new FlightNotFoundException("Invalid Source or Destination ");
		}
	}
	
	
	public ResponseEntity<ResponseStrucutre<List<Flight>>> getByFlightAirline(String airline){
		List<Flight> opt = flightDao.getByFlightAirline(airline);
		
		if(!opt.isEmpty()) {
			ResponseStrucutre<List<Flight>> res = new ResponseStrucutre<>();
			res.setData(opt);
			res.setStausCode(HttpStatus.OK.value());
			res.setMessage("Success");
			return new ResponseEntity<>(res,HttpStatus.OK);	
		}	
		else {
			throw new FlightNotFoundException("Invalid Airline Name");
		}
	}
	
	public ResponseEntity<ResponseStrucutre<Flight>> updateFlight(Flight flight) {
		
		if(flight.getId()==null) {
			 throw new IdNotFoundException("NO ID");
		}
		
		Optional<Flight> opt = flightDao.getByFlightId(flight.getId());
		
		if(opt.isPresent()) {
		
			ResponseStrucutre<Flight> res = new ResponseStrucutre<>();
			res.setData(flightDao.saveFlight(flight));
			res.setStausCode(HttpStatus.ACCEPTED.value());
			res.setMessage("Success");
			return new ResponseEntity<ResponseStrucutre<Flight>>(res,HttpStatus.ACCEPTED);		
		}
		else {
			throw new NoRecordException("No Record Found");
		}
	}
	
	
	public ResponseEntity<ResponseStrucutre<Flight>> deleteFlight(int id) {
	
		Optional<Flight> opt = flightDao.getByFlightId(id);
		
		if(opt.isPresent()) {
		
			ResponseStrucutre<Flight> res = new ResponseStrucutre<>();
			res.setData(opt.get());
			res.setStausCode(HttpStatus.ACCEPTED.value());
			res.setMessage("Success");
			flightDao.deleteFlight(id);
			return new ResponseEntity<ResponseStrucutre<Flight>>(res,HttpStatus.ACCEPTED);		
		}
		else {
			throw new NoRecordException("No Record Found");
		}
	}
	
	
}























