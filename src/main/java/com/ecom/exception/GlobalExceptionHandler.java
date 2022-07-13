package com.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.payload.ApiResonse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResonse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ApiResonse apiResonse = new ApiResonse(ex.getMessage(), false);
		return new ResponseEntity<ApiResonse>(apiResonse, HttpStatus.NOT_FOUND);

	}
}
