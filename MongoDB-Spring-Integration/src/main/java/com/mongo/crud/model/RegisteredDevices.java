package com.mongo.crud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "RegisteredDevices")
public class RegisteredDevices {

	private int id;

	@Id
	private String deviceId;
	private String orgId;

}
