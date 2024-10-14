package com.cognizant.cca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.cca.exception.BookingNotFoundException;
import com.cognizant.cca.exception.TripNotFoundException;
import com.cognizant.cca.model.Booking;
import com.cognizant.cca.model.Trip;
import com.cognizant.cca.repository.BookingRepository;
import com.cognizant.cca.repository.TripRepository;
import com.cognizant.cca.response.ReturnResponse;

@Service
public class TripServiceImpl implements TripService {
	
	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<Trip> getAllRides(String rideStatus) {
		return tripRepository.findByRideStatus(rideStatus);
	}
	
	@Override
	public String addRide(Trip trip) {
		tripRepository.save(trip);
		return trip.getTripId();
	}

	@Override
	public String updateRide(String tripId, Trip trip) {
		var optTrip = tripRepository.findById(tripId);
		if(!optTrip.isPresent()) {
			throw new TripNotFoundException("Trip with id "+ tripId + " not found");
		}
		System.out.println(tripId);
		Trip tripObj = optTrip.get();
		
		if(trip.getVehicleId() != null) {
			tripObj.setVehicleId(trip.getVehicleId());
		}
		if(trip.getRideDate() != null) {
			tripObj.setRideDate(trip.getRideDate());
		}
		if(trip.getRideTime() != null) {
			tripObj.setRideTime(trip.getRideTime());
		}
		if(trip.getNoOfSeat() != 0) {
			tripObj.setNoOfSeat(trip.getNoOfSeat());
		}
		if(trip.getFromLoc() != null) {
			tripObj.setFromLoc(trip.getFromLoc());
		}
		if(trip.getToLoc() != null) {
			tripObj.setToLoc(trip.getToLoc());
		}
		
		tripRepository.save(tripObj);		
		return "Trip with id " + tripId + " updated successfully";
	}

	@Override
	public Trip getRide(String tripId) {
		return tripRepository.findById(tripId)
					.orElseThrow(()-> new TripNotFoundException("Trip with id "+ tripId + " not found"));
	}

//	@Override
//	public List<Trip> getFromLocAndToLocAndRideStatus(String fromLoc, String toLoc, String rideStatus) {
//		return tripRepository.findByFromLocAndToLocAndRideStatus(fromLoc, toLoc, rideStatus);
//	}

	@Override
	public List<Trip> getRideByRpId(String rpId) {
		return tripRepository.findByRpIdAndRideStatus(rpId, "completed");
	}

	@Override
	public ReturnResponse startRide(String tripId,Trip trip) {
		var optTrip = tripRepository.findById(tripId);
		if(!optTrip.isPresent()) {
			throw new TripNotFoundException("Trip with id "+ tripId + " not found");
		}
		System.out.println(tripId);
		Trip tripObj = optTrip.get();
		tripObj.setRideStatus("started");
		tripRepository.save(tripObj);		
		ReturnResponse msg = new ReturnResponse("Trip with id " + tripId + " started");
		return msg;
	}

	@Override
	public ReturnResponse endRide(String tripId, Trip trip) {
		var optTrip = tripRepository.findById(tripId);
		if(!optTrip.isPresent()) {
			throw new TripNotFoundException("Trip with id "+ tripId + " not found");
		}
		System.out.println(tripId);
		Trip tripObj = optTrip.get();
		tripObj.setRideStatus("completed");
		tripRepository.save(tripObj);
		
		List<Booking> bookings= bookingRepository.findByTripId(tripId);
		if(bookings.isEmpty()) {
			throw new BookingNotFoundException("Booking with tripid "+ tripId + " not found");
		}
		for(Booking booking : bookings) {
			booking.setStatus("completed");
			bookingRepository.save(booking);
		}
		ReturnResponse msg = new ReturnResponse("Trip with id " + tripId + " completed");
		return msg;
	}
	
	@Override
	public Boolean deleteById(String tripId) {
		List<Booking> bookings = bookingRepository.findByTripId(tripId);
		if(bookings.isEmpty()) {
			tripRepository.deleteById(tripId);
			return true;
		}
		else {
			return false;
		}
	}

}
