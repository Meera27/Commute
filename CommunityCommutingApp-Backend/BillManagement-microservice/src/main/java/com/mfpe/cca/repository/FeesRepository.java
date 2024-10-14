package com.mfpe.cca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfpe.cca.model.Fees;
@Repository
public interface FeesRepository  extends JpaRepository<Fees, Integer>{
	Fees findByCarTypeAndFuelType(String carType, String fuelType);
}
