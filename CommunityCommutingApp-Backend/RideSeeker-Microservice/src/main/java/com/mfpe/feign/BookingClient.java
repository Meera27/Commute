package com.mfpe.feign;

import java.util.List;
import com.mfpe.model.ReturnResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mfpe.model.Booking;




@FeignClient(name="trip-manager", url="http://localhost:8090/api/tripmanager")

public interface BookingClient {


	@GetMapping("/{seekerId}/bookedrides")
	public List<Booking> getBookingsBySeekerId(@PathVariable String seekerId);
	
	@PostMapping("/bookRide")
    public ResponseEntity<ReturnResponse> addBookRide(@RequestBody Booking booking);
	
	@DeleteMapping("/{bookingId}/delete")
	public ResponseEntity<ReturnResponse> deleteBooking(@PathVariable String bookingId);
}
