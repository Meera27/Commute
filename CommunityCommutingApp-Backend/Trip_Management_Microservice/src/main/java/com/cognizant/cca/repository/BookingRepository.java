package com.cognizant.cca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cognizant.cca.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, String>{
	List<Booking> findByTripId(String tripId);
	List<Booking> findBySeekerId(String seekerId);
}
