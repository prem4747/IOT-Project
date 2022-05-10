package com.mongo.crud.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.crud.model.RegisteredDevices;
import com.mongo.crud.service.RegisteredDevicesService;
import com.mongodb.client.MongoCollection;

@RestController
@RequestMapping("/registered-devices")
public class RegisteredDevicesController {

	@Autowired
	private RegisteredDevicesService registeredDevicesService;

	@Autowired
	MongoTemplate mongoTemplate;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveDevices(@RequestBody RegisteredDevices registeredDevices) {
		registeredDevicesService.insert(registeredDevices);
		return "Device with id:" + registeredDevices.getId() + "added successfully";
	}

	@GetMapping
	public List<RegisteredDevices> getOrganizations() {
		return registeredDevicesService.findAll();
	}

	@GetMapping("{deviceId}")
	public RegisteredDevices getRegisteredDevicesById(@PathVariable String deviceId) {
		return registeredDevicesService.findItemByDeviceId(deviceId);
	}

	@DeleteMapping("{deviceId}")
	public String deleteRegisteredDevice(@PathVariable String deviceId) {
		registeredDevicesService.deleteByDeviceId(deviceId);
		return "Organization with id:" + deviceId + "deleted successfully";
	}

	@GetMapping("/customeraccounts/{orgId}")
	public List<Document> getCustomerAccountsByOrgId(@PathVariable String orgId) {

		List<Bson> pipeline = Arrays.asList(
				new Document("$lookup",
						new Document().append("from", "Organizations").append("localField", "orgId")
								.append("foreignField", "_id").append("as", "Organizations_docs")),
				new Document("$match", new Document("Organizations_docs", new Document("$ne", Arrays.asList()))),
				new Document("$addFields",
						new Document("Organizations_docs",
								new Document("$arrayElemAt", Arrays.asList("$Organizations_docs", 0)))),
				new Document("$match", new Document("Organizations_docs._id", orgId)),
				new Document("$project", new Document().append("_id", 1).append("orgId", 1)),
				new Document("$unionWith",
						new Document().append("coll", "RegisteredDevices").append("pipeline", Arrays.asList(
								new Document("$lookup",
										new Document().append("from", "Organizations").append("localField", "orgId")
												.append("foreignField", "_id").append("as", "Organizations_docs")),
								new Document("$match",
										new Document("Organizations_docs", new Document("$ne", Arrays.asList()))),
								new Document("$addFields",
										new Document("Organizations_docs",
												new Document("$arrayElemAt", Arrays.asList("$Organizations_docs", 0)))),
								new Document("$match", new Document("Organizations_docs.parentOrgId", orgId)),
								new Document("$project", new Document().append("_id", 1).append("orgId", 1))))),
				new Document("$group", new Document("_id", "$$ROOT")),
				new Document("$replaceRoot", new Document("newRoot", "$_id")),
				new Document("$sort", new Document("_id", 1)), new Document("$limit", 100));

		MongoCollection<Document> collection = mongoTemplate.getCollection("RegisteredDevices");
		List<Document> results = collection.aggregate(pipeline).into(new ArrayList<>());
		
		return results;
	}
}
