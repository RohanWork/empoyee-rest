package com.rest.rohan.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException {
	
	private int statusCode;
	private String statusMessage;
	private String timeStamp;

	public BaseException() {
		super();
	}

	public BaseException(int statusCode, String statusMessage, String timeStamp) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.timeStamp = timeStamp;
	}
	
}
