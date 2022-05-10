package com.mongo.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.crud.model.Organization;
import com.mongo.crud.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public void insert(Organization organization) {
		organizationRepository.insert(organization);

	}

	@Override
	public List<Organization> findAll() {
		return organizationRepository.findAll();
	}

	@Override
	public Organization findItemByOrgId(String orgId) {
		return organizationRepository.findItemByOrgId(orgId);
	}

	@Override
	public void deleteByOrgId(String orgId) {
		organizationRepository.deleteByOrgId(orgId);

	}

}
