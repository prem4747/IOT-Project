package com.mongo.crud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Organizations")
public class Organization {

	private int id;

	@Id
	private String orgId;
	private String parentOrgId;
	private String orgName;

}
