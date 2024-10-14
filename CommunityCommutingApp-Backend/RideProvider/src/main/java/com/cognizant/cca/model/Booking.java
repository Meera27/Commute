package com.cognizant.cca.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	
	private String bookingId;
	private int numberOfSeat;
	private String seekerId;
	@Pattern(regexp = "booked|completed|cancelled")
	private String status;
	
//	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="tripId")
	private String tripId;
}