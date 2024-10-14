package com.cognizant.cca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.cca.model.RideInfo;
import com.cognizant.cca.model.RideProvider;
import com.cognizant.cca.response.ReturnResponse;

@Service
public interface RideProviderService {

	List<RideProvider> getAll();
	RideProvider getById(String rpId);
	ReturnResponse addRideProvider(RideProvider rideProvider);
	ReturnResponse updateRideProvider(String rpId, RideProvider rideProvider);
	String deleteRideProvider(String rpId);
	
	List<RideInfo> getAllRides();
	List<RideInfo> getRidesById(String rpId);
	ReturnResponse addBooking(String rpId, RideInfo rideInfo);
	RideInfo getByTripId(String tripId);
	//RideInfo getRideByTripId(String tripId);
	
	ReturnResponse updateRideInfo(String tripId,RideInfo rideInfo);
	
	String deleteRideByTripId(String rpId);
	
	
}