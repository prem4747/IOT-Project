package com.mongo.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongo.crud.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

	@Query(value = "{'email' : ?0}")
	User findUserByEmail(String email);
}
