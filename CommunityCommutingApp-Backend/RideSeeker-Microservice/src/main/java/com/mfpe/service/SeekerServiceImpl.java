package com.mfpe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.exception.SeekerNotFoundException;
import com.mfpe.model.AddResponse;
import com.mfpe.model.Seeker;
import com.mfpe.repository.SeekerRepository;


@Service
public class SeekerServiceImpl implements SeekerService {
	
	@Autowired
	private SeekerRepository seekerRepository;

	@Override
	public List<Seeker> getAll() {
		
		return seekerRepository.findAll();
	}

	@Override
	public Seeker getById(String rsId) {
		
		return seekerRepository.findById(rsId)
			.orElseThrow (()->new SeekerNotFoundException("Seeker with id "+ rsId +" doesn't exist"));
	}

	@Override
	public AddResponse addSeeker(Seeker seeker) {   
		seekerRepository.save(seeker);
		AddResponse response=new AddResponse("Seeker with the id "+ seeker.getRsId() +" added successfully");
		return response;
	}

	@Override
//	public String deleteSeeker(String rsId) {
//		var optSeeker=seekerRepository.findById(rsId);
//		if(!optSeeker.isPresent()) {
//			throw new SeekerNotFoundException("Seeker with id"+rsId+"not found");
//		}
//		seekerRepository.deleteById(rsId);
//		return " Seeker with Id "+rsId+" deleted successfully ";
//	}
	public AddResponse deleteSeeker(String rsId) {
		var optSeeker=seekerRepository.findById(rsId);
		if(!optSeeker.isPresent()) {
			throw new SeekerNotFoundException("Seeker with id "+rsId+" doesn't exist");
		}
		seekerRepository.deleteById(rsId);
		AddResponse response=new AddResponse(" Seeker with Id "+rsId+" deleted successfully ");
		return response;
	}

	@Override
	public AddResponse updateSeeker(String rsId, Seeker seeker) {
	    var optSeeker = seekerRepository.findById(rsId);
	    
	    if(!optSeeker.isPresent()) {
	    	throw new SeekerNotFoundException("Seeker with id " +rsId+ " doen't exist!");
	    }
	    
	    var rideseeker=optSeeker.get();
	    if(seeker.getAdharCard()!=null) {
	    	rideseeker.setAdharCard(seeker.getAdharCard());
	    }
	    
	    if(seeker.getEmailId()!=null) {
	    	rideseeker.setEmailId(seeker.getEmailId());
	    }
	    
	    if(seeker.getPhone()!=null) {
	    	rideseeker.setPhone(seeker.getPhone());
	    }
	    
	    if(seeker.getFirstName()!= null) {
	    	rideseeker.setFirstName(seeker.getFirstName());
	    }
	    
	    if(seeker.getLastName()!= null) {
	    	rideseeker.setLastName(seeker.getLastName());
	    }
	    
	    if(seeker.getAddress()!= null) {
	    	rideseeker.setAddress(seeker.getAddress());
	    }
	    
	    if(seeker.getStatus()!= null) {
	    	rideseeker.setStatus(seeker.getStatus());
	    }
	    
	    seekerRepository.save(rideseeker);
//		return "Ride Seeker with id" +seeker.getRsId()+ "updated successfully";
	    AddResponse response=new AddResponse("Ride Seeker with id" +seeker.getRsId()+ "updated successfully");
	    return response;
	    
	}
}	

	