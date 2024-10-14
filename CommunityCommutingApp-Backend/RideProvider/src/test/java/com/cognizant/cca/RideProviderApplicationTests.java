package com.cognizant.cca;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.cca.model.Booking;
import com.cognizant.cca.model.RideInfo;
import com.cognizant.cca.model.RideProvider;
import com.cognizant.cca.response.ReturnResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class RideProviderApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	
	private ObjectMapper mapper=new ObjectMapper();
	
	@Test
	public void getAll() throws Exception{
		mockMvc.perform(get("/api/rideProviders"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists());
	}
	@Test
	public void getById() throws Exception { //GET Ride Providers By id
		mockMvc.perform(get("/api/rideProviders/RPNa29").accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.aadharcard").value("654444387654"))
		.andExpect(jsonPath("$.emailId").value("meeramnair99@cognizant.com"))
		.andExpect(jsonPath("$.phone").value("9061563531"))
		.andExpect(jsonPath("$.validUpto").value("2023-04-29"))
		.andExpect(jsonPath("$.status").value("Registered"))
		.andExpect(jsonPath("$.dlNo").value("1234-8765-8765-8765"))
		.andExpect(jsonPath("$.firstName").value("Meera"))
		.andExpect(jsonPath("$.lastName").value("Nair"));
		}

	@Test
	public void addRideProvider() throws Exception{
		RideProvider rideProvider = new RideProvider(null,"654444387650","1234-8765-8765-8760","meeramnair990@cognizant.com", "Nimisha","Murali","9061563530", new Date(2023,04,29), "Registered",null);
		String jsonData=mapper.writeValueAsString(rideProvider);
		mockMvc.perform(post("/api/rideProviders/new").content(jsonData)
			 .contentType(MediaType.APPLICATION_JSON_VALUE))
			 .andExpect(status().isOk())
			 .andExpect(content().string("Ride Provider with id "+rideProvider.getRpId()+" added successfully"));
	}
	
	@Test
	public void getAllRides() throws Exception {
		mockMvc.perform(get("/api/rideProviders/rides/viewAll"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists());
	}
//	@Test
//	public void getRideById() throws Exception {
//		mockMvc.perform(get("/0750TRCh/bookings").accept(MediaType.APPLICATION_JSON_VALUE))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.vehicleNo").value("77-87-765"))
//		.andExpect(jsonPath("$.carName").value("BENZ"))
//		.andExpect(jsonPath("$.rpId").value("RPNa27"))
//		.andExpect(jsonPath("$.tripId").value("0750TrCh"))
//		.andExpect(jsonPath("$.carType").value("SEDAN"))
//		.andExpect(jsonPath("$.fuelType").value("PETROL"))
//		.andExpect(jsonPath("$.rideTime").value("07:50:00"))
//		.andExpect(jsonPath("$.source").value("Trivandrum"))
//		.andExpect(jsonPath("$.destination").value("Chennai"));	
//	}
	
	@Test
	public void getByTripId() throws Exception {
		mockMvc.perform(get("/api/rideProviders/viewRides/1609DeCh").accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.vehicleNo").value("87-87-BG678"))
		.andExpect(jsonPath("$.carName").value("BMW"))
		.andExpect(jsonPath("$.rpId").value("RPKr11"))
		.andExpect(jsonPath("$.tripId").value("1609DeCh"))
		.andExpect(jsonPath("$.carType").value("SEDAN"))
		.andExpect(jsonPath("$.fuelType").value("PETROL"))
		.andExpect(jsonPath("$.noOfSeats").value(6))
		.andExpect(jsonPath("$.rideTime").value("16:09:00"))
		.andExpect(jsonPath("$.rideDate").value("2023-04-28"))
		.andExpect(jsonPath("$.source").value("Delhi"))
		.andExpect(jsonPath("$.destination").value("Chennai"));	
	}
	@Test
	public void getRidesById() throws Exception { // GET RIDES BY Rpid - rideInfo
		mockMvc.perform(get("/api/rideProviders/rides/RPNa27").accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		
		.andExpect(jsonPath("$.carName").value("BENZ"))
		.andExpect(jsonPath("$.rpId").value("RPNa27"))
		.andExpect(jsonPath("$.tripId").value("0750TrCh"))
		.andExpect(jsonPath("$.carType").value("SEDAN"))
		.andExpect(jsonPath("$.fuelType").value("PETROL"))
		.andExpect(jsonPath("$.vehicleNo").value("77-87-765"))
		.andExpect(jsonPath("$.noOfSeats").value(7))
		.andExpect(jsonPath("$.rideDate").value("2023-11-23"))
		.andExpect(jsonPath("$.rideTime").value("07:50:00"))
		.andExpect(jsonPath("$.source").value("Trivandrum"))
		.andExpect(jsonPath("$.destination").value("Chennai"));
	}
	@Test
	public void addBooking() throws Exception{
//		RideInfo rideProvider = new RideInfo("77-87-765", "BENZ","RPNa27", "0750TrCh","SEDAN","PETROL", 7, LocalTime.of(7, 50), LocalDate.of(2023,11,23), "Trivandrum","Chennai",new List<Smiles>());
		RideInfo rideInfo = new RideInfo("77-87-765", "BENZ", "RPNa27", "0750TrCh", "SEDAN", "PETROL", 7, LocalTime.of(7, 50), LocalDate.of(2023,11,23), "Trivandrum", "Chennai");
		String jsonData=mapper.writeValueAsString(rideInfo);
		mockMvc.perform(post("/api/rideProviders/rides/addnewRide/RPNa27").content(jsonData)
			 .contentType(MediaType.APPLICATION_JSON_VALUE))
			 .andExpect(status().isOk())
			 .andExpect(content().string("Ride Provider with id "+rideInfo.getTripId()+" added successfully"));
	}
	@Test
	public void deleteRide() throws Exception{
        // Perform the delete request to the controller using MockMvc
        mockMvc.perform(delete("/api/rideProviders/bookings/{tripId}", "1156ThCh"))
               .andExpect(status().isOk());
	}
	
}
