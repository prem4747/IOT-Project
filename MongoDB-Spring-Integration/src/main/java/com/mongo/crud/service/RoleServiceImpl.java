package com.mongo.crud.service;

import java.util.Collection;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mongo.crud.model.Role;
import com.mongo.crud.repository.RoleRepository;

@Service
public class RoleServiceImpl implements IService<Role> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Collection<Role> findAll() {
		return null;
	}

	@Override
	public Optional<Role> findById(int id) {
		return roleRepository.findById(id);
	}

	@Override
	public Role saveOrUpdate(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonobject = new JSONObject();
		try {
			// roleRepository.deleteById(id);
			jsonobject.put("message", "Record deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Page<Role> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
