package com.mfpe.cca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.cca.model.BillMaster;
import com.mfpe.cca.model.BillReport;
import com.mfpe.cca.response.ReturnResponse;
import com.mfpe.cca.service.BillService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/bill")
@CrossOrigin
public class BillManagementController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BillManagementController.class);
	
	@Autowired
	private BillService billService;
	
	//To generate new bill
	@PostMapping("/new")
	public ResponseEntity<ReturnResponse> generateBill(@RequestBody BillMaster bill) {
		LOGGER.info("START Generate Bill");
		ReturnResponse result = billService.generateBill(bill);
		LOGGER.debug(result.getReturnMsg());
		LOGGER.info("END Generate Bill");
		return new ResponseEntity<ReturnResponse>(result, HttpStatus.CREATED);	
	}
	
	//To view each bill
	@GetMapping("/{tripId}")
	public BillMaster getBill(@PathVariable String tripId) {
		LOGGER.info("START View Bill");
		LOGGER.info("END View Bill");
		return billService.getBill(tripId);
	}
	
	//To get bill report
	@GetMapping("/report/{rpId}")
	public List<BillReport> getBillReport(@PathVariable String rpId) {
		LOGGER.info("START Bill Report");
		LOGGER.info("END Bill Report");
		return billService.getBillReport(rpId);
	}


}
