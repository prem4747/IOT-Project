package com.embedded.iotappapi.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.embedded.iotappapi.domain.Sensor;
import com.embedded.iotappapi.repository.SensorRepository;
import com.embedded.iotappapi.service.IService;

@Service
public class SensorAppServiceImpl implements IService<Sensor> {

	@Autowired
	private SensorRepository sensorRepository;

	@Override
	public Page<Sensor> findAll(Pageable pageable) {
		return sensorRepository.findAll(pageable);
	}

	@Override
	public Optional<Sensor> findById(Long id) {
		return sensorRepository.findById(id);
	}

	@Override
	public Sensor saveOrUpdate(Sensor sensor) {

		return sensorRepository.save(sensor);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonobject = new JSONObject();
		try {
			sensorRepository.deleteById(id);
			jsonobject.put("message", "Record deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonobject.toString();
	}

	@Override
	public Collection<Sensor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
