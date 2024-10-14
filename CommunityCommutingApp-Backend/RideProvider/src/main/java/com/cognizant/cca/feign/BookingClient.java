package com.cognizant.cca.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.cca.model.Booking;

@FeignClient(name = "trip-manager", url ="http://localhost:8090")
public interface BookingClient {

	@GetMapping("/api/tripmanager/{tripId}/bookings")
	public List<Booking> getBookingsByTripId(@PathVariable String tripId);
}
