package com.mfpe.cca.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.mfpe.cca.model.Trip;

@FeignClient(name="tripmanager", url="http://localhost:8090/api/tripmanager/")
public interface TripClient {
	
	@GetMapping("rideprovider/{rpId}")
	public List<Trip> getRideByRpId(@PathVariable String rpId);
}
