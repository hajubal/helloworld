package com.ha.helloworld.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 사용자 못찼았을 경우 던지는 예외
 * @author hajubal
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8130371070858589559L;

	public RecordNotFoundException(String exception) {
		super(exception);
	}
}
