package com.mongo.crud.exception;

public class DeviceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2533194229906054487L;

	public DeviceNotFoundException(String message) {
		super(message);
	}
}
