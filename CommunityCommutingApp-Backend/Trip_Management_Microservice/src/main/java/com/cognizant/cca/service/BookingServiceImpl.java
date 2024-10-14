package com.cognizant.cca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.cca.exception.BookingNotFoundException;
import com.cognizant.cca.exception.TripNotFoundException;
import com.cognizant.cca.model.Booking;
import com.cognizant.cca.model.Trip;
import com.cognizant.cca.repository.BookingRepository;
import com.cognizant.cca.repository.TripRepository;
import com.cognizant.cca.response.ReturnResponse;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private TripRepository tripRepository;
	
	@Override
	public List<Booking> getAll() {
		return bookingRepository.findAll();
	}

	@Override
	public ReturnResponse addBookRide(Booking booking) {
		String tripId = booking.getTripId();
//		System.out.println(tripId);
		bookingRepository.save(booking);
		Trip trip = tripRepository.findById(tripId).get();
//		System.out.println(trip);
		trip.setSeatsFilled(trip.getSeatsFilled()+booking.getNumberOfSeat());
		tripRepository.save(trip);
		ReturnResponse msg = new ReturnResponse("Booking with id "+ booking.getBookingId() + " added successfully");
		return msg;
	}

//	@Override
//	public String updateBookRide(String bookingId, Booking booking) {
//		var optBooking = bookingRepository.findById(bookingId);
//		if(!optBooking.isPresent()) {
//			throw new BookingNotFoundException("Booking with id "+ bookingId + " not found");
//		}
//		System.out.println(bookingId);
//		Booking bookingObj = optBooking.get();
//		
//		if(booking.getNumberOfSeat() != 0) {
//			bookingObj.setNumberOfSeat(booking.getNumberOfSeat());
//		}
//		
//		bookingRepository.save(bookingObj);		
//		return "Booking with id " + bookingId + " updated successfully";
//	}

	@Override
	public List<Booking> getBookingsByTripId(String tripId) {
		return bookingRepository.findByTripId(tripId);
	}
	
	@Override
	public List<Booking> getBookingsBySeekerId(String seekerId) {
		return bookingRepository.findBySeekerId(seekerId);
	}

	@Override
	public ReturnResponse deleteBookRide(String bookingId) {
		var optBooking = bookingRepository.findById(bookingId);
		if(!optBooking.isPresent()) {
			throw new BookingNotFoundException("Booking with id "+ bookingId + " not found");
		}
		Booking bookingObj = optBooking.get();
		String tripId = bookingObj.getTripId();
		Trip trip = tripRepository.findById(tripId).get();
//		System.out.println(trip);
		trip.setSeatsFilled(trip.getSeatsFilled()-bookingObj.getNumberOfSeat());
		trip.getBookings().remove(bookingObj);
//		tripRepository.save(trip);
		bookingRepository.deleteById(bookingId);
		ReturnResponse msg = new ReturnResponse("Booking with id " + bookingId + " deleted successfully");
		return msg;
	}

}
