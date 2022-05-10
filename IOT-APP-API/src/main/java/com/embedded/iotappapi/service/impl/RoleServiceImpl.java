package com.embedded.iotappapi.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.embedded.iotappapi.domain.Role;
import com.embedded.iotappapi.repository.RoleRepository;
import com.embedded.iotappapi.service.IService;

@Service
public class RoleServiceImpl implements IService<Role> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Collection<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Role saveOrUpdate(Role role) {
		return roleRepository.saveAndFlush(role);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonobject = new JSONObject();
		try {
			roleRepository.deleteById(id);
			jsonobject.put("message", "Record deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonobject.toString();
	}

	@Override
	public Page<Role> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
