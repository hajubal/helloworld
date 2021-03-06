package com.ha.helloworld.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 에러 공통 핸들러
 * 
 * @author hajubal
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
//public class CustomExceptionHandler {
	Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();

		details.add(ex.getLocalizedMessage());
		
		logger.error(ex.getMessage(), ex);
		
		ErrorResponse error = new ErrorResponse("Server Error", details);

		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
		
		List<String> details = new ArrayList<>();
		
		details.add(ex.getLocalizedMessage());
		
		ErrorResponse error = new ErrorResponse("Record Not Found", details);
		
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(BindException.class)
//	public final ResponseEntity<Object> handleBindException(BindException ex, WebRequest request) {
//		List<String> details = new ArrayList<>();
//		
//		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
//			details.add(error.getDefaultMessage());
//		}
//		
//		ErrorResponse error = new ErrorResponse("Validation Failed", details);
//		
//		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//	}
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		
		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		
		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}
