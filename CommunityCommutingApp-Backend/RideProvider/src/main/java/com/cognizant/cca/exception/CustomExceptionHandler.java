package com.cognizant.cca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler({RideProviderNotFoundException.class, RideNotFoundException.class})
	public ResponseEntity<String> handleNotFound(Exception ex) {
		ResponseEntity<String> res = new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
		return res;
	}
}
