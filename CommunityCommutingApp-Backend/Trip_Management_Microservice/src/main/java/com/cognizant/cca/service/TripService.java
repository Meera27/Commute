package com.cognizant.cca.service;

import java.util.List;
import com.cognizant.cca.model.Trip;
import com.cognizant.cca.response.ReturnResponse;

public interface TripService {
	public List<Trip> getAllRides(String rideStatus);
	public String addRide(Trip trip);
	public String updateRide(String tripId, Trip trip);
	public Trip getRide(String tripId);
//	public List<Trip> getFromLocAndToLocAndRideStatus(String fromLoc, String toLoc, String rideStatus);
	public List<Trip> getRideByRpId(String rpId);
	public ReturnResponse startRide(String tripId, Trip trip);
	public ReturnResponse endRide(String tripId, Trip trip);
	public Boolean deleteById(String tripId);
}
