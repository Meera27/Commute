package com.cognizant.cca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.cca.model.RideInfo;

public interface RideInfoRepository extends JpaRepository<RideInfo, String>{
	
	List<RideInfo> findRidesByrpId(String rpId);
	RideInfo findRideByTripId(String tripId);
	String deleteRideByTripId(String tripId);
}

