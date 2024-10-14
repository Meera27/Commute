package com.mfpe.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Trip {
	
	private String tripId;
	private String rpId;
	private String vehicleId;
//	@Future(message = "Date must be in the future")
	private LocalDate rideDate;
//	@Future(message = "Time must be in the future")
	private LocalTime rideTime;
	@Pattern(regexp = "planned|started|completed|cancelled")
	private String rideStatus;
	private int noOfSeat;
	private int seatsFilled;
	private String fromLoc;
	private String toLoc;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="tripId", referencedColumnName = "tripId")
	private List<Booking> bookings;
}
