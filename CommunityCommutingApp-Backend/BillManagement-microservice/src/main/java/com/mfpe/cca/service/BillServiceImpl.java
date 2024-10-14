package com.mfpe.cca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.cca.response.ReturnResponse;
import com.mfpe.cca.exception.BillNotFoundException;
import com.mfpe.cca.feign.TripClient;
import com.mfpe.cca.model.BillMaster;
import com.mfpe.cca.model.BillReport;
import com.mfpe.cca.model.Fees;
import com.mfpe.cca.model.Trip;
import com.mfpe.cca.repository.BillMasterRepository;
import com.mfpe.cca.repository.FeesRepository;

@Service
public class BillServiceImpl  implements BillService{
	int i=1;
	
	@Autowired
	private BillMasterRepository billMasterRepository;
	
	@Autowired
	private FeesRepository feesRepository;
	
	@Autowired
	private TripClient tripClient;
	
	@Override
	public ReturnResponse generateBill(BillMaster bill) {
		System.out.println(bill.getCarType());
		Fees fee = feesRepository.findByCarTypeAndFuelType(bill.getCarType(), bill.getFuelType());
		System.out.println(fee);
		int fixedCostPerKm = fee.getCostOfFuel() / fee.getAverageInKm();
		int costKmWise = fixedCostPerKm * bill.getNoOfKm();
		bill.setTotalBill(costKmWise + fee.getWearTearCost() + fee.getDriverCharges() + fee.getCarPoolCommission());
		bill.setCostPerOccupant(bill.getTotalBill() / bill.getNoOfOccupants());
		bill.setBillId("B"+ bill.getTripId() + i++);
		bill.setFeeId(fee.getFeeId());
		billMasterRepository.save(bill);
		ReturnResponse msg = new ReturnResponse("Bill with id "+ bill.getBillId() + " added successfully");
		return msg;
	}

	@Override
	public BillMaster getBill(String tripId) {
		if(billMasterRepository.findByTripId(tripId) == null) {
			throw new BillNotFoundException("Bill with tripId "+ tripId +" not found");
		}
		return billMasterRepository.findByTripId(tripId);
	}
	
	@Override
	public List<BillReport> getBillReport(String rpId) {
		List<BillReport> billReports = new ArrayList<>();
		List<Trip> trips = tripClient.getRideByRpId(rpId);
		for(Trip trip :trips){
			BillReport billReport = new BillReport();
			billReport.setTripId(trip.getTripId());
			billReport.setSource(trip.getFromLoc());
			billReport.setDestination(trip.getToLoc());
			billReport.setRideDate(trip.getRideDate());
			BillMaster bill = billMasterRepository.findByTripId(trip.getTripId());
			billReport.setTotalCost(bill.getTotalBill());
			billReports.add(billReport);
		}
		return billReports;
	}

//	@Override
//	public List<BillMaster> getMonthlyReport(int month, String rpId) {
//		return billMasterRepository.findByRideDateMonthAndRpId(month, rpId);
//	}

//	@Override
//	public List<BillMaster> getBill() {
//		List<BillMaster> bills = billMasterRepository.findAll();
//		System.out.println ("controller" + bills.size());
//		return billMasterRepository.findAll();
//	}

	
	

}
