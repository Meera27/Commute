package com.mfpe.cca.service;

import java.util.List;

import com.mfpe.cca.model.BillMaster;
import com.mfpe.cca.model.BillReport;
import com.mfpe.cca.model.Trip;
import com.mfpe.cca.response.ReturnResponse;

public interface BillService {
	public ReturnResponse generateBill(BillMaster bill);
	BillMaster getBill(String tripId);
	List<BillReport> getBillReport(String rpId);

}
