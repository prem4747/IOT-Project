package com.embedded.iotappapi.exception;

public class SensorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2533194229906054487L;

	public SensorNotFoundException(String message) {
		super(message);
	}
}
