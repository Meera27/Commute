package com.cognizant.cca.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.cca.model.Trip;


@FeignClient(name = "trip-manager", url ="http://localhost:8090")
public interface TripManagerClient {

	@PostMapping("/api/tripmanager/new")
	public String addRide(@RequestBody Trip trip);
	
	@PutMapping("/api/tripmanager/{tripId}/update")
	public String updateRide(@PathVariable String tripId, @RequestBody Trip trip);
	
	@DeleteMapping("/api/tripmanager/deletetrip/{tripId}")
	public Boolean deleteRide(@PathVariable String tripId);

}
