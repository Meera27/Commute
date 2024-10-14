package com.cognizant.cca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cca.model.Booking;
import com.cognizant.cca.response.ReturnResponse;
import com.cognizant.cca.service.BookingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
@RequestMapping("/api/tripmanager")
public class BookingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	int i=1;
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/bookings")
	public List<Booking> getAll() {
		LOGGER.info("START");
		LOGGER.info("Get All Bookings");
		LOGGER.info("END");
		return bookingService.getAll();
	}
	
	@GetMapping("/{tripId}/bookings")
	public List<Booking> getBookingsByTripId(@PathVariable String tripId) {
		LOGGER.info("START");
		LOGGER.info("Get All Bookings By tripId");
		LOGGER.info("END");
		return bookingService.getBookingsByTripId(tripId);
	}
	
	@GetMapping("/{seekerId}/bookedrides")
	public List<Booking> getBookingsBySeekerId(@PathVariable String seekerId) {
		LOGGER.info("START");
		LOGGER.info("Get All Bookings By seekerId");
		LOGGER.info("END");
		return bookingService.getBookingsBySeekerId(seekerId);
	}
	
	@PostMapping("/bookRide")
    public ResponseEntity<ReturnResponse> addBookRide(@RequestBody Booking booking) {
		LOGGER.info("START");
		LOGGER.info("Add new booking");
		String bookingId = "BK"+i++;
		booking.setBookingId(bookingId);
		booking.setStatus("booked");
		ReturnResponse result = bookingService.addBookRide(booking);
		LOGGER.info("END");
		return new ResponseEntity<ReturnResponse>(result, HttpStatus.CREATED);	
    }
	
//	@PutMapping("/{bookingId}/updatebooking")
//	public String updateBooking(@PathVariable String bookingId, @RequestBody Booking booking) {
//		LOGGER.info("START");
//		LOGGER.info("Get All Bookings By seekerId");
//		LOGGER.info("END");
//		return bookingService.updateBookRide(bookingId, booking);
//	}
	
	@DeleteMapping("/{bookingId}/delete")
	public ResponseEntity<ReturnResponse> deleteBooking(@PathVariable String bookingId) {
		LOGGER.info("START");
		LOGGER.info("Delete Booking By bookinId");
		ReturnResponse result =  bookingService.deleteBookRide(bookingId);
		LOGGER.info("END");
		return new ResponseEntity<ReturnResponse>(result, HttpStatus.OK);
	}
}
