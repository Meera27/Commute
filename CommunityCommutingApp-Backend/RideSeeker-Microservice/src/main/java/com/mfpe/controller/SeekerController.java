package com.mfpe.controller;

import java.util.Calendar;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mfpe.feign.BookingClient;

import com.mfpe.feign.TripClient;
import com.mfpe.model.AddResponse;
import com.mfpe.model.Booking;
import com.mfpe.model.ReturnResponse;

import com.mfpe.model.Seeker;
import com.mfpe.model.Trip;
import com.mfpe.service.SeekerService;

@RestController
@RequestMapping("/api/rideseeker")
@CrossOrigin
public class SeekerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SeekerController.class);
	
    @Autowired
    private SeekerService seekerService;
    
    @Autowired
    private TripClient tripclient;
    
    @Autowired
    private BookingClient bookingclient;
    
    @GetMapping
	public List<Seeker>getAll(){
		return seekerService.getAll();
		
	}
    
    
    
    @GetMapping("/{rsid}")
    public Seeker getById(@PathVariable String rsid) {
    	return seekerService.getById(rsid);
    }
    

//     Registering a New RideSeeker (-----ENDPONIT -1-----)
    
     @PostMapping("/new")
     public ResponseEntity<AddResponse> add(@RequestBody Seeker seeker) {
    	 String rsId = ("RE"+seeker.getFirstName().substring(0,2) + seeker.getLastName().substring(0,2)).toUpperCase();
    	 seeker.setRsId(rsId);
    	 AddResponse response =seekerService.addSeeker(seeker);
    	 
         return ResponseEntity.ok(response);    	 
    	
    }
     
// Updating RideSeeker             (-----ENDPONIT -2-----)

    
    @PutMapping("/{rsid}/update")
//    public String update(@PathVariable String rsid, @RequestBody Seeker seeker) {
//		return seekerService.updateSeeker(rsid, seeker);
    
      public ResponseEntity<AddResponse> update(@PathVariable String rsid, @RequestBody Seeker seeker){
      AddResponse response =seekerService.updateSeeker(rsid, seeker);
      return ResponseEntity.ok(response);
    	
    }
    
//Unregister RideSeeker       (-----ENDPONIT -2-----)
    
    @DeleteMapping("/{rsid}")
//    public String delete(@PathVariable String rsid) {
//		return seekerService.deleteSeeker(rsid);
//    	
//    }
    public ResponseEntity<AddResponse>delete(@PathVariable String rsid){
    AddResponse response = seekerService.deleteSeeker(rsid);
    return ResponseEntity.ok(response);
    
    }
    
//Viewing All Rides available    
    
     @GetMapping("/viewrides/list")
     public List<Trip> getRides(){
    	return tripclient.getAllRides();
    }
    
     
//Viewing Booked Rides by individual Ride Seeker 
     
    @GetMapping("/{rsid}/bookedrides")
    public List<Booking> getBookingsBySeekerId(@PathVariable String rsid){
    	return bookingclient.getBookingsBySeekerId(rsid);
    }
  
    
    
//Booking a Ride      (----ENDPONIT -3----)
    
    @PostMapping("/bookRide")
    public ResponseEntity<ReturnResponse> addBookRide(@RequestBody Booking booking){

    	return bookingclient.addBookRide(booking);
    }
    
    
// Cancel the Ride    (-----ENDPONIT -4----)
    
    @DeleteMapping("/{bookingId}/delete")
    public ResponseEntity<ReturnResponse> deleteBooking(@PathVariable String bookingId){
    	return bookingclient.deleteBooking(bookingId);
    }
    
    
    
    
    
}
	