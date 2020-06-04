package com.barnali.rest.webservices.success;

public class SuccessResponse {
	
	private String message;
	
	public SuccessResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SuccessResponse [message=" + message + "]";
	}
	
}
