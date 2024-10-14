package com.cognizant.cca.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cognizant.cca.model.Booking;
import com.cognizant.cca.response.ReturnResponse;

public interface BookingService {
	public List<Booking> getAll();
	public ReturnResponse addBookRide(Booking booking);
	//public String updateBookRide(String bookingId, Booking booking);
	public List<Booking> getBookingsByTripId(String tripId);
	public List<Booking> getBookingsBySeekerId(String seekerId);
	public ReturnResponse deleteBookRide(String bookingId);
}
