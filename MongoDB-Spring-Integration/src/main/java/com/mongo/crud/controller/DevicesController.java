package com.mongo.crud.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
@CrossOrigin(origins = "http://localhost:3000")
public class DevicesController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@GetMapping
	public List<Document> getDevices() {

		List<Document> listOfDocs = mongoTemplate.findAll(Document.class, "DeviceMetrics");
		return listOfDocs;
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody String jsonString) {

		Document doc = Document.parse(jsonString);
		mongoTemplate.insert(doc, "DeviceMetrics");
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@GetMapping("{deviceId}")
	public Page<Document> getDevices(@PathVariable String deviceId) {

		Pageable pageable = PageRequest.of(0, 5); // Page number - 0, Number of Elements to be returned
		
		// Query query = new Query().limit(2); - For limiting number of results returned
		Query query = new Query().with(pageable);
		query.addCriteria(Criteria.where("deviceId").is(deviceId));
		List<Document> listOfDocs = mongoTemplate.find(query, Document.class, "DeviceMetrics");
		
		
		Page<Document> resultSet = PageableExecutionUtils.getPage(
				listOfDocs,
		        pageable,
		        () -> mongoTemplate.count(query, getClass()));
		
		return resultSet;
	}
}
