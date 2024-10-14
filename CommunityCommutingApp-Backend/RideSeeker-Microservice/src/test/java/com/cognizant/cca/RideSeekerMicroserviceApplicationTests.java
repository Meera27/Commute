package com.cognizant.cca;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfpe.RideSeekerMicroserviceApplication;
import com.mfpe.model.Seeker;

@SpringBootTest(classes = RideSeekerMicroserviceApplication.class)
@AutoConfigureMockMvc
class RideSeekerMicroserviceApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	private ObjectMapper mapper=new ObjectMapper();

//	@Test
//	void contextLoads() {
//		
//	}

	@Test
	public void testGetAll() throws Exception {
		
		mockMvc.perform(get("/api/rideseeker"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists());
		
		
	}
	
	@Test
	public void testGetById() throws Exception {
		mockMvc.perform(get("/api/rideseeker/1").accept(MediaType.APPLICATION_JSON_VALUE)) 
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.rsId").value("1"))
		.andExpect(jsonPath("$.adharCard").value("123456785964"))
		.andExpect(jsonPath("$.emailId").value("pooja@cognizant.com"))
		.andExpect(jsonPath("$.phone").value("8281097834"))
		.andExpect(jsonPath("$.firstName").value("Pooja"))
		.andExpect(jsonPath("$.lastName").value("Das"))
		.andExpect(jsonPath("$.address").value("Trivandrum"))
		.andExpect(jsonPath("$.status").value("registered"));
	}
	
	@Test
	public void testAdd() throws Exception {
		Seeker seeker=new Seeker("1","123456785964","pooja@cognizant.com","8281097834","Pooja","Das","Trivandrum","registered");
	 String jsonData=mapper.writeValueAsString(seeker);
	 mockMvc.perform(post("/api/rideseeker/new").content(jsonData)
			 .contentType(MediaType.APPLICATION_JSON_VALUE))
	 .andExpect(status().isCreated())
	 .andExpect(content().string("RideSeeker Added successfully"));
	}

}
