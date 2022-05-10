package com.embedded.iotappapi.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.embedded.iotappapi.domain.User;
import com.embedded.iotappapi.repository.UserRepository;
import com.embedded.iotappapi.service.IService;

@Service
public class UserServiceImpl implements IService<User> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User saveOrUpdate(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonobject = new JSONObject();
		try {
			userRepository.deleteById(id);
			jsonobject.put("message", "Record deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonobject.toString();
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
