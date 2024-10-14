package com.cognizant.cca.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Pattern;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
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
@Table(name="rideProvider")
public class RideProvider {
	@Id
	private String rpId;
	
	@Column(nullable = false, unique = true)
	@Pattern(regexp = "^\\d{12}$", message = "Aadhaar number must be 12 digits")
	private String aadharcard;
	
	@Column(nullable = false, unique = true)
	@Pattern(regexp = ".*cognizant\\.com$", message = "Email should end with @cognizant.com")
	private String emailId;
	
	@Column(nullable = false, unique = true)
	@Pattern(regexp = "^[789]\\d{9}$", message = "Phone number must be 10 digits starting with 9, 8, or 7")
	private String phone; 
	
	@Size(min = 3)
	@Column(nullable = false)
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Fistname should only contain alphabets")
	private String firstName;
	
	@Size(min = 3 ,message = " Size is 3")	
	@Column(nullable = false)
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Lastname should only contain alphabets")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@Pattern(regexp="\\d{4}-\\d{4}-\\d{4}-\\d{4}", message = "Licence number should be 16 characters with 3 hyphens (0000-0000-0000-0000")
	private String dlNo;
	//	@NotNull
	@Temporal(TemporalType.DATE)
	private Date validUpto;
	@Pattern(regexp = "Registered|Un-Registered")
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "rpId", referencedColumnName="rpId")
	private List<RideInfo> rideInfo = new ArrayList<>();
	
	
	//	private List<RideInfo> rideInfo = new ArrayList<>();
	
	
}
