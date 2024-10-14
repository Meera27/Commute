package com.cognizant.cca.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.cca.model.BillMaster;
import com.cognizant.cca.response.ReturnResponse;

@FeignClient(name="bill-management", url ="http://localhost:8070")
public interface BillingClient {

	@PostMapping("/api/bill/new")
	public ResponseEntity<ReturnResponse> generateBill(@RequestBody BillMaster bill);
	
	@GetMapping("/api/bill/{tripId}")
	public BillMaster getBill(@PathVariable String tripId);
	
	@GetMapping("/api/bill/report/{rpId}")
	public List<BillMaster> getMonthlyReport(@PathVariable String rpId);
}
