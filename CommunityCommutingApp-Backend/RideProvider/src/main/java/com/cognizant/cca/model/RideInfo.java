package com.cognizant.cca.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name="rideInfo")
public class RideInfo {

	@Id
	@Column(name="vehicleNo")
	private String vehicleNo;
	
	@Column(nullable = false)
	private String carName;
	
	private String rpId;
	private String tripId;
	
	@Column(nullable = false)
	private String carType;
	
	@Column(nullable = false)
	private String fuelType;
	
	@Column(nullable = false)
	@Max(10)
	private int noOfSeats;
	
	@Column(nullable = false)
//	@Temporal(TemporalType.TIME)
	private LocalTime rideTime;
	//@Column(nullable = false)
//	@Temporal(TemporalType.DATE)
	private LocalDate rideDate;
	//@Column(nullable = false)
	private String source;
	@Column(nullable = false)
	private String destination;

//	@OneToMany(cascade=CascadeType.ALL, fetch =FetchType.EAGER)
//	@JoinColumn(name = "rpId", referencedColumnName="rpId")
//	private List<Smiles> smiles = new ArrayList<>();

	
}
