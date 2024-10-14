package com.cognizant.cca;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

import com.cognizant.cca.model.Booking;
import com.cognizant.cca.model.Trip;
import com.cognizant.cca.service.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class TripManagementMicroserviceApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
//	
//	@Autowired
//	private WebApplicationContext context;
//	
//	@Autowired
//	private TripService tripService;
	
	private ObjectMapper mapper=new ObjectMapper();

	
	@Test
	public void testGetTrips() throws Exception {
		
		mockMvc.perform(get("/api/tripmanager/trips"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists());
	}
	
	@Test
	public void testAddRide() throws Exception {
		Trip trip = new Trip("0530TRID", "RP00", "KL-05-783", LocalDate.of(2023, 4, 30), LocalTime.of(5, 30), "planned", 6, 0, "TRIVANDRUM", "IDUKKI", new ArrayList<Booking>());
		String jsonData=mapper.writeValueAsString(trip);
		mockMvc.perform(post("/api/tripmanager/new").content(jsonData)
				 .contentType(MediaType.APPLICATION_JSON_VALUE))
				 .andExpect(status().isCreated())
				 .andExpect(content().string("0530TRID"));
	}
	
	@Test
	public void testGetTripById() throws Exception {
		mockMvc.perform(get("/api/tripmanager/0530KOPA").accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.tripId").value("0530KOPA"))
		.andExpect(jsonPath("$.rpId").value("RPA"))
		.andExpect(jsonPath("$.vehicleId").value("VEH2"))
		.andExpect(jsonPath("$.fromLoc").value("KOLLAM"))
		.andExpect(jsonPath("$.toLoc").value("PALAKKAD"))
		.andExpect(jsonPath("$.rideDate").value("2023-06-18"))
		.andExpect(jsonPath("$.rideTime").value("05:30:00"))
		.andExpect(jsonPath("$.noOfSeat").value(4));
	}
	
	@Test
	public void testGetByRpId() throws Exception {
//		
//		Trip trip = new Trip();
//        given(tripService.getRideByRpId("RPA")).willReturn(Arrays.asList(trip));

        
		mockMvc.perform(get("/api/tripmanager/rideprovider/RPA").accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].tripId").value("0900TRID"))
		.andExpect(jsonPath("$[0].rpId").value("RPA"))
		.andExpect(jsonPath("$[0].vehicleId").value("VEH2"))
		.andExpect(jsonPath("$[0].fromLoc").value("TRIVANDRUM"))
		.andExpect(jsonPath("$[0].toLoc").value("IDUKKI"))
		.andExpect(jsonPath("$[0].rideDate").value("2023-05-15"))
		.andExpect(jsonPath("$[0].rideTime").value("09:00:00"))
		.andExpect(jsonPath("$[0].noOfSeat").value(6));
		
//		.andExpect(jsonPath("$[0].tripId").value("0530KOPA"))
//		.andExpect(jsonPath("$[0].rpId").value("RPA"))
//		.andExpect(jsonPath("$[0].vehicleId").value("VEH2"))
//		.andExpect(jsonPath("$[0].fromLoc").value("KOLLAM"))
//		.andExpect(jsonPath("$[0].toLoc").value("PALAKKAD"))
//		.andExpect(jsonPath("$[0].rideDate").value("2023-06-18"))
//		.andExpect(jsonPath("$[0].rideTime").value("05:30:00"))
//		.andExpect(jsonPath("$[0].noOfSeat").value(4));
	}
	
	@Test
	public void testGetBookings() throws Exception {
		mockMvc.perform(get("/api/tripmanager/bookings"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists());
	}
	
	@Test
	public void testGetBookingsByTripId() throws Exception {
		mockMvc.perform(get("/api/tripmanager/0900TRID/bookings"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists());
	}
	
	@Test
	public void testGetBookingsBySeekerId() throws Exception {
		mockMvc.perform(get("/api/tripmanager/RSA/bookings"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists());
	}
	
//	@Test
//	public void testAddBooking() throws Exception {
//		Trip trip = new Trip("0530TRID", "RP00", "KL-05-783", LocalDate.of(2023, 4, 30), LocalTime.of(5, 30), "planned", 6, 0, "TRIVANDRUM", "IDUKKI", new ArrayList<Booking>());
//		String jsonData=mapper.writeValueAsString(trip);
//		mockMvc.perform(post("/api/tripmanager/new").content(jsonData)
//				 .contentType(MediaType.APPLICATION_JSON_VALUE))
//				 .andExpect(status().isCreated())
//				 .andExpect(content().string("0530TRID"));
//	}
	
//	@Test
//	public void testGetBookingById() throws Exception {
//		mockMvc.perform(get("/api/tripmanager/0530KOPA").accept(MediaType.APPLICATION_JSON_VALUE))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.tripId").value("0530KOPA"))
//		.andExpect(jsonPath("$.rpId").value("RPA"))
//		.andExpect(jsonPath("$.vehicleId").value("VEH2"))
//		.andExpect(jsonPath("$.fromLoc").value("KOLLAM"))
//		.andExpect(jsonPath("$.toLoc").value("PALAKKAD"))
//		.andExpect(jsonPath("$.rideDate").value("2023-06-18"))
//		.andExpect(jsonPath("$.rideTime").value("05:30:00"))
//		.andExpect(jsonPath("$.noOfSeat").value(4));
//	}
}
