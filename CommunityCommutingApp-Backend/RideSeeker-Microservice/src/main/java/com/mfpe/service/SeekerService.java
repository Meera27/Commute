package com.mfpe.service;

import java.util.List;

import com.mfpe.model.AddResponse;
import com.mfpe.model.Seeker;

public interface SeekerService {
	
	List<Seeker> getAll();
	Seeker getById(String id);
	AddResponse addSeeker(Seeker seeker);
//	String updateSeeker(String id, Seeker seeker);
	AddResponse updateSeeker(String id, Seeker seeker);
//	String deleteSeeker(String id);
	AddResponse deleteSeeker(String id);
	
	

}
