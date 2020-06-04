package com.barnali.rest.webservices.error;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice     //as this controller sends response to all the controllers in this project so want this controller to be accessible to all controllers
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)  //this annotation handles all exceptions 
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

		//we want to return our specific/customized exception
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(UserException.class)  //this annotation handles User exceptions 
	public ResponseEntity<Object> handleUserException(Exception ex, WebRequest request) {

		//we want to return our specific/customized exception
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		if(ex.getMessage().equals("Missing user attributes"))
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
		
		if(ex.getMessage().contains("not found"))
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
