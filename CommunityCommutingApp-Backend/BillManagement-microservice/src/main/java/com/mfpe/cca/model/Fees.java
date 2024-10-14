package com.mfpe.cca.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.AnyDiscriminator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Fees {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feeId;
	private String carType;
	private String carName;
	private String fuelType;
	@Min(value = 0, message = "Average In Km cannot be negative")
	private int averageInKm;
	@Min(value = 0, message = "Cost of fuel cannot be negative")
	private int costOfFuel;
    @Min(value = 1, message = "Wear and Tear cost cannot be negative")
	private int wearTearCost;
    @Min(value = 0, message = "Driver Charges cannot be negative")
	private int driverCharges;
	@Min(value = 0, message = "Car pool commision cannot be negative")
	private int carPoolCommission;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="feeId" , referencedColumnName="feeId")
	private List<BillMaster> bills;

}
