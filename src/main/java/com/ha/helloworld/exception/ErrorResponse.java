package com.ha.helloworld.exception;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 에러 response 포맷
 * @author hajubal
 *
 */
@XmlRootElement(name = "error")
public class ErrorResponse {
	
	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}
	
	private String message;
	
	private List<String> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
}
