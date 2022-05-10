package com.mongo.crud.service;

import java.util.Collection;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mongo.crud.model.User;
import com.mongo.crud.repository.UserRepository;

@Service
public class UserServiceImpl implements IService<User> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		return null;
	}

	@Override
	public User saveOrUpdate(User user) {
		return userRepository.save(user);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonobject = new JSONObject();
		try {
			// userRepository.deleteById(id);
			jsonobject.put("message", "Record deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
