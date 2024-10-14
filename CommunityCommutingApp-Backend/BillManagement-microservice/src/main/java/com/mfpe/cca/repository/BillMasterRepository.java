package com.mfpe.cca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfpe.cca.model.BillMaster;

public interface BillMasterRepository extends JpaRepository<BillMaster, String>{
	BillMaster findByTripId(String tripId);
}
