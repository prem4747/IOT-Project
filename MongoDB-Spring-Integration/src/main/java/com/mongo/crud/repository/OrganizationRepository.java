package com.mongo.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongo.crud.model.Organization;

public interface OrganizationRepository extends MongoRepository<Organization, Integer> {

	@Query("{orgId:'?0'}")
	Organization findItemByOrgId(String orgId);

	@Query(value = "{'_id' : ?0}", delete = true)
	public void deleteByOrgId(String orgId);

}
