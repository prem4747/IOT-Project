package com.embedded.iotappapi.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.embedded.iotappapi.domain.Role;


public interface IService<T> {
	Page<T> findAll(Pageable pageable);

	Optional<T> findById(Long id);

	T saveOrUpdate(T t);

	String deleteById(Long id);

	Collection<T> findAll();
}
