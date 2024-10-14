package com.cognizant.cca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.cca.model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, String>{
	List<Trip> findByFromLocAndToLocAndRideStatus(String fromLoc, String toLoc, String rideStatus);
	List<Trip> findByRideStatus(String rideStatus);
	List<Trip> findByRpIdAndRideStatus(String rpId, String rideStatus);
}