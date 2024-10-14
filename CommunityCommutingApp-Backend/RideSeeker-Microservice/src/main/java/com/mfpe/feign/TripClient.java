package com.mfpe.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.mfpe.model.Trip;
@FeignClient(name="trip-manager", url="http://localhost:8090")
public interface TripClient {
	
	@GetMapping("api/tripmanager/trips")
	public List<Trip> getAllRides();

}
