package com.mongo.crud.service;

import java.util.List;

import com.mongo.crud.model.Organization;

public interface OrganizationService {

	void insert(Organization organization);

	List<Organization> findAll();

	Organization findItemByOrgId(String orgId);

	void deleteByOrgId(String orgId);

}
