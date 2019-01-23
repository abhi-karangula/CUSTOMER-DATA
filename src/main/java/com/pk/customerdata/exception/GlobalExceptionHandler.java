package com.pk.customerdata.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleException() {
		return buildResponse("Error", HttpStatus.OK);
	}

	public ResponseEntity<Map<String, String>> buildResponse(String message, HttpStatus status) {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("message", message);
		ResponseEntity<Map<String, String>> response = new ResponseEntity<>(responseMap, status);
		return response;
	}

}
