package com.book.project.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.book.project.dao.BookingDao;
import com.book.project.dao.FlightDao;
import com.book.project.dao.PaymentDao;
import com.book.project.dto.ResponseStrucutre;
import com.book.project.entities.Booking;
import com.book.project.entities.Flight;
import com.book.project.entities.Passenger;
import com.book.project.entities.Payment;
import com.book.project.enums.BookingStatus;
import com.book.project.exceptions.BookingNotFoundException;
import com.book.project.exceptions.FlightNotFoundException;
import com.book.project.exceptions.PassengerNotFoundException;
import com.book.project.exceptions.PaymentNotFound;

@Service
public class BookingService {
	
	@Autowired
	private BookingDao bookingDao;	
	
	@Autowired
	private FlightDao flightDao;
	
	
	public ResponseEntity<ResponseStrucutre<Booking>> saveBooking(Booking booking) {

    ResponseStrucutre<Booking> res = new ResponseStrucutre<>();

    Optional<Flight> opt = flightDao.getByFlightId(booking.getFlight().getId());

    if (opt.isPresent()) {

        
        booking.setFlight(opt.get());

        if (booking.getPayment() == null) {
            throw new PaymentNotFound("Payment Not Found");
        } else {
            int passengerNo = booking.getPassengers().size();
            Double price = opt.get().getPrice();

            booking.getPayment().setAmount(passengerNo * price);
            booking.getPayment().setBooking(booking);
        }

        if (booking.getPassengers().size() > 0) {
            for (Passenger p : booking.getPassengers()) {
                p.setBooking(booking);
            }
        } else {
            throw new PassengerNotFoundException("Passenger Not found");
        }

        res.setData(bookingDao.saveBooking(booking));

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    throw new RuntimeException("Flight not found");
}

	
	
	public ResponseEntity<ResponseStrucutre<List<Booking>>> getAllBooking(){
		ResponseStrucutre<List<Booking>> res = new ResponseStrucutre<>();
		res.setData(bookingDao.getALLBookings());
		res.setMessage("Success");
		res.setStausCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(res,HttpStatus.OK);
		
			
	}
	
	
	public ResponseEntity<ResponseStrucutre<Optional<Booking>>> getByBookingId(int id) {
		
		Optional<Booking> opt = bookingDao.getBookingById(id);
		
		if(!opt.isEmpty()) {
			ResponseStrucutre<Optional<Booking>> res = new ResponseStrucutre<>();
			res.setData(opt);
			res.setStausCode(HttpStatus.OK.value());
			res.setMessage("Success");
			return new ResponseEntity<>(res,HttpStatus.OK);	
		}	
		else {
			throw new BookingNotFoundException("Invalid ID");
		}
		
	}
	
	public ResponseEntity<ResponseStrucutre<List<Booking>>> getBookingflight(int id) {
		
		Optional<Flight> opt = flightDao.getByFlightId(id);
		if(!opt.isEmpty()) {
			ResponseStrucutre<List<Booking>> res = new ResponseStrucutre<>();
			List<Booking> opt1 = bookingDao.getBookingByFlight(opt.get());
			res.setData(opt1);
			res.setStausCode(HttpStatus.OK.value());
			res.setMessage("Success");
			return new ResponseEntity<>(res,HttpStatus.OK);	
		}	
		else {
			throw new BookingNotFoundException("Invalid Flight ID");
		}
		
	}
	
	
	public ResponseEntity<ResponseStrucutre<List<Booking>>> getByBookingDate(LocalDateTime bookingDate) {
		
		List<Booking> opt = bookingDao.getBookingByDate(bookingDate);
		
		if(opt.size()>0) {
			ResponseStrucutre<List<Booking>> res = new ResponseStrucutre<>();
			res.setData(opt);
			res.setStausCode(HttpStatus.OK.value());
			res.setMessage("Success");
			return new ResponseEntity<>(res,HttpStatus.OK);	
		}	
		else {
			throw new BookingNotFoundException("Invalid Date");
		}
		
	}
	
	
	public ResponseEntity<ResponseStrucutre<List<Booking>>> getByBookingStatus(BookingStatus status) {
		
		List<Booking> opt = bookingDao.getBookingByStatus(status);
		
		if(opt.size()>0) {
			ResponseStrucutre<List<Booking>> res = new ResponseStrucutre<>();
			res.setData(opt);
			res.setStausCode(HttpStatus.OK.value());
			res.setMessage("Success");
			return new ResponseEntity<>(res,HttpStatus.OK);	
		}	
		else {
			throw new BookingNotFoundException("Invalid Status");
		}
		
	}


	public ResponseEntity<ResponseStrucutre<List<Passenger>>> getByBookingPassenger(int id) {
		Optional<Booking> opt= bookingDao.getBookingById(id);
		
		if(opt.isPresent()) {
			ResponseStrucutre<List<Passenger>> res= new ResponseStrucutre<>();
			res.setData(opt.get().getPassengers());
			res.setMessage("Succesful");
			res.setStausCode(HttpStatus.OK.value());
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		else {
			throw new BookingNotFoundException("Invalied ID");
		}
		
	}
	
	
	public ResponseEntity<ResponseStrucutre<Payment>> getByBookingPayment(int id) {
		Optional<Booking> opt= bookingDao.getBookingById(id);
		
		if(opt.isPresent()) {
			ResponseStrucutre<Payment> res= new ResponseStrucutre<>();
			res.setData(opt.get().getPayment());
			res.setMessage("Succesful");
			res.setStausCode(HttpStatus.OK.value());
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		else {
			throw new BookingNotFoundException("Invalied ID");
		}
		
	}
	
	
	public ResponseEntity<ResponseStrucutre<Booking>> updateBookingStatus(int id,String status) {
		Optional<Booking> opt= bookingDao.getBookingById(id);
		
		if(opt.isPresent()) {
			ResponseStrucutre<Booking> res= new ResponseStrucutre<>();
			if(isValidStatus(status)) {
				opt.get().setStatus(BookingStatus.valueOf(status));
				res.setData(opt.get());
				bookingDao.saveBooking(opt.get());
				res.setMessage("SUCCESSFUL");
				res.setStausCode(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<>(res,HttpStatus.OK);
			}
			else {
				throw new BookingNotFoundException("STATUS NOT FOUND");
			}
			
		}
		else {
			throw new BookingNotFoundException("Invalied ID");
		}
		
	}
	
	static public boolean isValidStatus(String value) {
	    try {
	        BookingStatus.valueOf(value);
	        return true;
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	}


	public ResponseEntity<ResponseStrucutre<Booking>> deleteBooking(int id) {
		
		ResponseStrucutre<Booking> res = new ResponseStrucutre<Booking>();
		
		Optional<Booking> opt = bookingDao.getBookingById(id);
		
		if(opt.isPresent()) {
			
			bookingDao.deleteBooking(opt.get());
			res.setData(opt.get());
			res.setMessage("Booking Deleted");
			res.setStausCode(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		}
		
		else {
			throw new BookingNotFoundException("Invalid Flight");
		}
		
		
	}	

	

	

	
}

































