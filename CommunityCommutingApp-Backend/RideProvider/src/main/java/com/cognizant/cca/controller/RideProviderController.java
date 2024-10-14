package com.cognizant.cca.controller;

import java.util.Calendar;  

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cca.feign.BillingClient;
import com.cognizant.cca.feign.BookingClient;
import com.cognizant.cca.feign.TripManagerClient;
import com.cognizant.cca.model.BillMaster;
import com.cognizant.cca.model.Booking;
import com.cognizant.cca.model.RideInfo;
import com.cognizant.cca.model.RideProvider;
import com.cognizant.cca.model.Trip;
import com.cognizant.cca.repository.RideProviderRepository;
import com.cognizant.cca.response.ReturnResponse;
import com.cognizant.cca.service.RideProviderService;

@RestController
@RequestMapping("/api/rideProviders")
@CrossOrigin
public class RideProviderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RideProviderController.class);

	@Autowired
	private RideProviderService rideProviderService;
	
	@Autowired
	private RideProviderRepository rideProviderRepository;
	
	@Autowired
	private TripManagerClient tripManagerClient;
	
	@Autowired
	private BookingClient bookingClient;
	
	@Autowired
	private BillingClient billingClient;
	
	@GetMapping
	public List<RideProvider> getAll(){
		
		LOGGER.info("START");
		LOGGER.info("Get all Ride Providers");
		LOGGER.debug("{}",rideProviderService.getAll());
		LOGGER.info("END");
		return rideProviderService.getAll();
	}

	@GetMapping("/{rpId}")
	public RideProvider getById(@PathVariable String rpId) { //GET Ride Providers By id
		LOGGER.info("START");
		LOGGER.info("Get Ride Provider by rpId");
		LOGGER.info("END");
		return rideProviderService.getById(rpId);
	}

	@GetMapping("/{tripId}/bookings")							// Get Bookings for a ride by tripId
	public List<Booking> getRideById(@PathVariable String tripId) {
		LOGGER.info("START");
		LOGGER.info("Get Bookings for a ride by tripId");
		LOGGER.info("END");
		return bookingClient.getBookingsByTripId(tripId);
	}
	
	@GetMapping("/rides/{rpId}")
	public List<RideInfo> getRidesById(@PathVariable String rpId) { // GET RIDES BY Rpid - rideInfo
		LOGGER.info("START");
		LOGGER.info("Get Ride Provider by rpId");
		LOGGER.info("END");
		return rideProviderService.getRidesById(rpId);
	}

	@GetMapping("/viewRides/{tripId}")
	public RideInfo getByTripId(@PathVariable String tripId) { //GET RIDE BY TRIP ID
		LOGGER.info("START");
		LOGGER.info("Get Ride by tripId");
		LOGGER.info("END");
		return rideProviderService.getByTripId(tripId);
	}
	
	@PostMapping("/new")
	public ResponseEntity<ReturnResponse> addRideProvider(@RequestBody RideProvider rideProvider) {
		LOGGER.info("START");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(rideProvider.getValidUpto());
		int date = calendar.get(Calendar.DAY_OF_MONTH);
		var id = ("RP"+rideProvider.getLastName().substring(0, 2)).toUpperCase()+ String.valueOf(date).substring(0);
		rideProvider.setRpId(id);
		LOGGER.info("Adding Ride provider");
		rideProviderRepository.save(rideProvider);
		ReturnResponse result = rideProviderService.addRideProvider(rideProvider);
		LOGGER.info("END");
		return new ResponseEntity<ReturnResponse>(result, HttpStatus.CREATED);
	}

	@PutMapping("/{rpId}/update") //UPDATE RIDE PROVIDER
	public ResponseEntity<ReturnResponse> updateRideProvider(@PathVariable String rpId, @RequestBody RideProvider rideProvider) {
		LOGGER.info("START");
		LOGGER.info("Updating Ride provider");
		LOGGER.info("END");
		ReturnResponse msg = rideProviderService.updateRideProvider(rpId, rideProvider);
		return new ResponseEntity<ReturnResponse>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/{rpId}")// DELETE RIDE PROVIDER
	public String deleteRideProvider(@PathVariable String rpId) {
		LOGGER.info("START");
		LOGGER.info("Deleting Ride provider");
		LOGGER.info("END");
		return rideProviderService.deleteRideProvider(rpId);
	}

	@PostMapping("/rides/addnewRide/{rpId}")
	public ResponseEntity<ReturnResponse> addBooking(@PathVariable String rpId, @RequestBody RideInfo rideInfo) {
		LOGGER.info("START");
		Trip trip = new Trip();
		trip.setRpId(rpId);
		trip.setVehicleId(rideInfo.getVehicleNo());
		trip.setRideDate(rideInfo.getRideDate());
		trip.setRideTime(rideInfo.getRideTime());
		trip.setRideStatus("planned");
		trip.setNoOfSeat(rideInfo.getNoOfSeats());
		trip.setFromLoc(rideInfo.getSource());
		trip.setToLoc(rideInfo.getDestination());
		trip.setSeatsFilled(0);
		LOGGER.info("Adding ride to a Ride provider");
		String tripId = tripManagerClient.addRide(trip);
		rideInfo.setTripId(tripId);
		rideInfo.setRpId(rpId);	
		System.out.println("RP id set"+ rideInfo.getRpId()); 		
		ReturnResponse msg =  rideProviderService.addBooking(rpId, rideInfo);
		LOGGER.info("END");
		return new ResponseEntity<ReturnResponse>(msg, HttpStatus.CREATED);
	}

	
	@GetMapping("/rides/viewAll") // view all rides
	public List<RideInfo> getAllRides() {
		LOGGER.info("START");
		LOGGER.info("View All Rides");
		LOGGER.info("END");
		return rideProviderService.getAllRides();
	}

	@PutMapping("/{tripId}/bookings")
	public ResponseEntity<ReturnResponse> updateRideInfo(@PathVariable String tripId,@RequestBody RideInfo rideInfo) {	
		Trip trip = new Trip();
		trip.setVehicleId(rideInfo.getVehicleNo());
		trip.setRideDate(rideInfo.getRideDate());
		trip.setRideTime(rideInfo.getRideTime());
		trip.setRideStatus("planned");
		trip.setNoOfSeat(rideInfo.getNoOfSeats());
		trip.setFromLoc(rideInfo.getSource());
		trip.setToLoc(rideInfo.getDestination());
		trip.setSeatsFilled(0);
		LOGGER.info("START");
		LOGGER.info("Updating Ride Information");
		tripManagerClient.updateRide(tripId, trip);
		LOGGER.info("END");
		ReturnResponse msg = rideProviderService.updateRideInfo(tripId, rideInfo);
		return new ResponseEntity<ReturnResponse>(msg, HttpStatus.OK);
	}

//	@PostMapping("/api/bill/new")
//	public ResponseEntity<ReturnResponse> generateBill(@RequestBody BillMaster bill){
//		LOGGER.info("START");
//		LOGGER.info("Generating Bill");
//		LOGGER.info("END");
//		ResponseEntity<ReturnResponse> msg = billingClient.generateBill(bill);
//		return new ResponseEntity<ReturnResponse>(HttpStatus.CREATED);
//	}
	
	@DeleteMapping("/bookings/{tripId}")
	public ReturnResponse deleteRide(@PathVariable String tripId) {
		LOGGER.info("START");
		LOGGER.info("Deleting Ride Information");
		Boolean result = tripManagerClient.deleteRide(tripId);
		ReturnResponse msg;
		if(result == true) {
			 rideProviderService.deleteRideByTripId(tripId);
			 msg = new ReturnResponse("Ride Deleted");
		}
		else {
			 msg = new ReturnResponse(" Couldn't delete as bookings exist for the selected ride!");
			 }
		LOGGER.info("END");
		return msg;
	}
}

