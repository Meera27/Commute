package com.cognizant.cca.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.cca.exception.RideNotFoundException;
import com.cognizant.cca.exception.RideProviderNotFoundException;
import com.cognizant.cca.model.Booking;
import com.cognizant.cca.model.RideInfo;
import com.cognizant.cca.model.RideProvider;
import com.cognizant.cca.repository.RideInfoRepository;
import com.cognizant.cca.repository.RideProviderRepository;
import com.cognizant.cca.response.ReturnResponse;

import jakarta.transaction.Transactional;

@Service
public class RideProviderServiceImpl implements RideProviderService{

	@Autowired
	private RideProviderRepository rideProviderRepository;
	@Autowired
	private RideInfoRepository rideInfoRepository;
	
	@Override
	public List<RideProvider> getAll() {
		// TODO Auto-generated method stub
		return rideProviderRepository.findAll();
	}
	@Override
	public RideProvider getById(String rpId) {
		// TODO Auto-generated method stub
		return rideProviderRepository.findById(rpId) 
				.orElseThrow(()->new RideProviderNotFoundException("Ride Provider with id " + rpId + " doesn't exist!"));
	}

	@Override
	public List<RideInfo> getRidesById(String rpId) {	
		if(rideInfoRepository.findRidesByrpId(rpId).isEmpty()) {
				throw new RideNotFoundException("Ride with RpId "+rpId+" doesn't exist!");
		}
		return rideInfoRepository.findRidesByrpId(rpId);
	}
	

	@Override
	public RideInfo getByTripId(String tripId) {
		// TODO Auto-generated method stub
//		return rideInfoRepository.findRideByTripId(tripId);
		if(rideInfoRepository.findRideByTripId(tripId) == null) {
			throw new RideNotFoundException("Ride with trip ID "+tripId+" doesn't exist!");
		}
	return rideInfoRepository.findRideByTripId(tripId);
}

	@Override
	public ReturnResponse addRideProvider(RideProvider rideProvider) {		
		rideProviderRepository.save(rideProvider);
		ReturnResponse msg = new ReturnResponse("Ride Provider with id "+rideProvider.getRpId()+" added successfully");
		return msg;
	}	
	

	@Override
	public ReturnResponse addBooking(String rpId, RideInfo rideInfo) {
//		rideInfo.setRpId(rpId);
		System.out.println(rideInfo);
		rideInfoRepository.save(rideInfo);
		ReturnResponse msg = new ReturnResponse("Ride Provider with id "+rideInfo.getTripId()+" added successfully");
		return msg;
	}

	@Override
	public ReturnResponse updateRideProvider(String rpId, RideProvider rideProvider) {
		var optRide = rideProviderRepository.findById(rpId); //OPTIONAL
		System.out.print("OPTIONAL "+optRide);
		
		if(!optRide.isPresent()) {
			throw new RideProviderNotFoundException("Ride Provider with id " + rpId + " doesn't exist!");
		}
		var rider = optRide.get(); // To get actual employee from OPTIONAL ->get()
		
		if(rideProvider.getAadharcard() != null) {
			rider.setAadharcard(rideProvider.getAadharcard());
		}
		if(rideProvider.getEmailId() != null) {
			rider.setEmailId(rideProvider.getEmailId());
		}
		if(rideProvider.getPhone() != null) {
			rider.setPhone(rideProvider.getPhone());
		}
		if(rideProvider.getFirstName() != null) {
			rider.setFirstName(rideProvider.getFirstName());
		}
		if(rideProvider.getLastName() != null) {
			rider.setLastName(rideProvider.getLastName());
		}
		if(rideProvider.getDlNo() != null) {
			rider.setDlNo(rideProvider.getDlNo());
		}
		if(rideProvider.getValidUpto() != null) {
			rider.setValidUpto(rideProvider.getValidUpto());
		}
		if(rideProvider.getStatus() != null) {
			rider.setStatus(rideProvider.getStatus());
		}
		rideProviderRepository.save(rider);
		System.out.print(rider);
		
		ReturnResponse msg = new ReturnResponse("Ride Provider with id "+rpId+" updated successfully");
		return msg;
	}


	@Override
	public String deleteRideProvider(String rpId) {
		var optRide = 	rideProviderRepository.findById(rpId); //OPTIONAL
		if(!optRide.isPresent()) {
			throw new RideProviderNotFoundException("Ride Provider with id " + rpId+ " doesn't exist!");
		}
		else {
			rideProviderRepository.deleteById(rpId);
		}
		return "Ride Provider with id "+rpId+" deleted successfully";
	}

	@Override
	public List<RideInfo> getAllRides() {
		return rideInfoRepository.findAll();
	}
	
	@Override
	public ReturnResponse updateRideInfo(String tripId, RideInfo rideInfo) {
		Optional<RideInfo> optRide = Optional.ofNullable(rideInfoRepository.findRideByTripId(rideInfo.getTripId()));
		//var optRide = rideInfoRepository.findById(rideInfo.getVehicleNo()); //OPTIONAL
		System.out.print("OPTIONAL "+optRide);
	
		if(!optRide.isPresent()) {
			throw new RideProviderNotFoundException("Ride with id " + rideInfo.getTripId() + " doesn't exist!!!");
		}
		var rider = optRide.get(); // To get actual employee from OPTIONAL ->get()
		
		if(rideInfo.getCarType() != null) {
			rider.setCarType(rideInfo.getCarType());
		}
		if(rideInfo.getFuelType() != null) {
			rider.setFuelType(rideInfo.getFuelType());
		}
		if(rideInfo.getNoOfSeats() == 0) {
			rider.setNoOfSeats(rideInfo.getNoOfSeats());
		}
		if(rideInfo.getRideDate() != null) {
			rider.setRideDate(rideInfo.getRideDate());
		}
		if(rideInfo.getCarName() != null) {
			rider.setCarName(rideInfo.getCarName());
		}
		if(rideInfo.getRideTime() != null) {
			rider.setRideTime(rideInfo.getRideTime());
		}
		if(rideInfo.getSource() != null) {
			rider.setSource(rideInfo.getSource());
		}
		if(rideInfo.getDestination() != null) {
			rider.setDestination(rideInfo.getDestination());
		}
		rideInfoRepository.save(rider);
		System.out.print(rider);
		
		ReturnResponse msg = new ReturnResponse("Ride with id "+tripId+" updated successfully");
		return msg;
	}
	@Override
	@Transactional
	public String deleteRideByTripId(String tripId) {
			return rideInfoRepository.deleteRideByTripId(tripId);
	}
}
