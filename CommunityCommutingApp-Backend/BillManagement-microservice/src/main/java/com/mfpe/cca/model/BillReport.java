package com.mfpe.cca.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillReport {
	private String tripId;
	private LocalDate rideDate;
	private String source;
	private String destination;
	private int totalCost;
}
