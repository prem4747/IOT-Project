package com.mongo.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.crud.model.Role;

public interface RoleRepository extends MongoRepository<Role, Integer> {

}
