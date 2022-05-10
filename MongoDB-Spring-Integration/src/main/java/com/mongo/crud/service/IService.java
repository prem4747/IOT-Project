package com.mongo.crud.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T> {

	Page<T> findAll(Pageable pageable);

	Optional<T> findById(int id);

	T saveOrUpdate(T t);

	String deleteById(Long id);

	Collection<T> findAll();

}
