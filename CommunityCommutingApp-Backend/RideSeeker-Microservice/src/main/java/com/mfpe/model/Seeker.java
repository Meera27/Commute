package com.mfpe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
@Table(name="rideSeeker")
public class Seeker {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String rsId;
	@Column(unique = true, length = 12)
	private String adharCard;
	@Column(unique = true)
	private String emailId;
	@Column(unique = true)
	private String phone;
	@Size(min = 3)
	private String firstName;
	@Size(min = 3)
	private String lastName;
	private String address;
	@Pattern(regexp = "(registered|un-registered)")
	private String status;
	
	

}
