package com.ba.error;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ba.domain.error.Error;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
 
	private static final String DEVELOPER_LINK="https://developer.iag.com/ba/errors";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	  MethodArgumentTypeMismatchException ex, WebRequest request) {
		Error detailError = new Error("INVALID_PARAMETER","DATA_INVALID","Incorrect data passed", DEVELOPER_LINK,null,null,null);
		
		List errors = new ArrayList<Error>();
		
		errors.add(detailError);
	 
	    Error apiError = 
	      new Error(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.getReasonPhrase(),ex.getLocalizedMessage(),
	    		  DEVELOPER_LINK, new Date(),String.valueOf(Math.random()), errors);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}

