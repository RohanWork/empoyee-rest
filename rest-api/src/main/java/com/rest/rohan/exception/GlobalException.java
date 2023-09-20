package com.rest.rohan.exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.micrometer.core.instrument.config.validate.ValidationException;

@ControllerAdvice
public class GlobalException{

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
		BaseException baseException = new BaseException();
		baseException.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
//		baseException.setStatusMessage("Oops, something went wrong. Please try again or review code for requested api.\n ");
		baseException.setStatusMessage(ex.getMessage());
		baseException.setTimeStamp(dateFormat.format(new Date()));
		
		return new ResponseEntity<>(baseException, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleValidationException(ValidationException ex){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
		BaseException baseException = new BaseException();
		baseException.setStatusCode(HttpStatus.OK.value());
		baseException.setStatusMessage(ex.getMessage());
		baseException.setTimeStamp(dateFormat.format(new Date()));
			
		return new ResponseEntity<>(baseException, HttpStatus.OK);
	}
}
