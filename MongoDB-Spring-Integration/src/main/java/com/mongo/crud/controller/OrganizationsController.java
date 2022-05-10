package com.mongo.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.crud.model.Organization;
import com.mongo.crud.service.OrganizationService;

@RestController
@RequestMapping("/organizations")
public class OrganizationsController {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	MongoTemplate mongoTemplate;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveDevices(@RequestBody Organization organization) {
		organizationService.insert(organization);
		return "Device with id:" + organization.getId() + "added successfully";
	}

	@GetMapping
	public List<Organization> getOrganizations() {
		return organizationService.findAll();
	}

	@GetMapping("{orgId}")
	public Organization getOrganizationById(@PathVariable String orgId) {
		return organizationService.findItemByOrgId(orgId);
	}

	@DeleteMapping("{orgId}")
	public String deleteOrganization(@PathVariable String orgId) {
		organizationService.deleteByOrgId(orgId);
		return "Organization with id:" + orgId + "deleted successfully";
	}

	@GetMapping("/customeraccounts/{orgId}")
	public List<Organization> getCustomerAccountsByOrgId(@PathVariable String orgId) {

		Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("parentOrgId").is(orgId)),
				Aggregation.project("orgName", "parentOrgId", "id"));

		AggregationResults<Organization> results = mongoTemplate.aggregate(agg, "Organizations", Organization.class);

		List<Organization> listOfCustomerAccounts = results.getMappedResults();
		System.out.println(results.getMappedResults());
		return listOfCustomerAccounts;
	}

}
