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

import com.cognizant.cca.model.Trip;
import com.cognizant.cca.response.ReturnResponse;
import com.cognizant.cca.service.TripService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
@RequestMapping("/api/tripmanager")
public class TripController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TripController.class);
	
	@Autowired
	private TripService tripService;
	
	//Returns only planned trips to Ride Seeker
	@GetMapping("/trips")
	public List<Trip> getAllRides() {
		LOGGER.info("START");
		LOGGER.info("Get All Planned Rides");
		LOGGER.info("END");
		return tripService.getAllRides("planned");
	}
	
	//Adds new trip provided by Ride Provider
	@PostMapping("/new")
	public ResponseEntity<String> addRide(@RequestBody Trip trip) {
		LOGGER.info("START");
		LOGGER.info("Add Trip");
		String tripId = String.format("%02d%02d", trip.getRideTime().getHour(), trip.getRideTime().getMinute()) + (trip.getFromLoc().substring(0, 2) + trip.getToLoc().substring(0, 2)).toUpperCase();
		trip.setTripId(tripId);
		tripService.addRide(trip);
		ResponseEntity<String> result= new ResponseEntity<>(tripId, HttpStatus.CREATED);
		LOGGER.debug(tripId);
		LOGGER.info("END");
		return result;
	}
	
	
//	@GetMapping("/search")
//	public List<Trip> getFromLocAndToLocAndRideStatus(String fromLoc, String toLoc, String rideStatus) {
//		return tripService.getFromLocAndToLocAndRideStatus(fromLoc, toLoc, rideStatus);
//	}
	
	//Returns trip by tripId
	@GetMapping("/{tripId}")
	public Trip getRideById(@PathVariable String tripId) {
		LOGGER.info("START");
		LOGGER.info("Get Trip by tripId");
		return tripService.getRide(tripId);
	}
	
	//Returns trip by rpId
	@GetMapping("rideprovider/{rpId}")
	public List<Trip> getRideByRpId(@PathVariable String rpId) {
		LOGGER.info("START");
		LOGGER.info("Get Trip by rpId");
		return tripService.getRideByRpId(rpId);
	}
	
	//Updates trip 
	@PutMapping("/{tripId}/update")
	public String updateRide(@PathVariable String tripId, @RequestBody Trip trip) {
		LOGGER.info("START");
		LOGGER.info("Update Trip");
		return tripService.updateRide(tripId, trip);
	}
	
	
	//Change rideStatus to started
	@PutMapping("startride/{tripId}")
	public ReturnResponse startRide(@PathVariable String tripId, @RequestBody Trip trip) {
		LOGGER.info("START");
		LOGGER.info("Start trip");
		return tripService.startRide(tripId, trip);
	}
	
	//Change rideStatus to completed
	@PutMapping("endride/{tripId}")
	public ReturnResponse endRide(@PathVariable String tripId, @RequestBody Trip trip) {
		LOGGER.info("START");
		LOGGER.info("End Trip");
		return tripService.endRide(tripId, trip);
	}
	
	//Deletes trip based on tripId provided by Ride Provider
	@DeleteMapping("/deletetrip/{tripId}")
	public Boolean deleteRide(@PathVariable String tripId) {
		return tripService.deleteById(tripId);
	}
}
