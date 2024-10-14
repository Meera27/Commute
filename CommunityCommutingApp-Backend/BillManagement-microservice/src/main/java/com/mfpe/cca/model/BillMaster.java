package com.mfpe.cca.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name="billmaster")
public class BillMaster {
	@Id
	private String billId;
	private String tripId;
	private int noOfKm;
	private int totalBill;
	private int noOfOccupants;
	private int feeId;
	private int costPerOccupant;
	private String carType;
	private String fuelType;

}
