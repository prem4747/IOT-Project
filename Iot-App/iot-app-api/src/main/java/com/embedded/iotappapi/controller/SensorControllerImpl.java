package com.embedded.iotappapi.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.embedded.iotappapi.domain.Sensor;
import com.embedded.iotappapi.exception.ApplicationException;
import com.embedded.iotappapi.exception.SensorNotFoundException;
import com.embedded.iotappapi.service.IService;

@RestController
@RequestMapping("/sensor")
@CrossOrigin(origins = "http://localhost:3000")
@EnableWebMvc
public class SensorControllerImpl implements Controller<Sensor> {

	private static Logger log = LoggerFactory.getLogger(SensorControllerImpl.class);

	@Autowired
	private IService<Sensor> sensorAppService;

	@Override
	public ResponseEntity<Page<Sensor>> findAll(Pageable pageable) {
		log.info("SensorControllerImpl - findAll");
		return new ResponseEntity<>(sensorAppService.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Sensor> findById(Long id) {
		log.info("SensorControllerImpl - findById");
		Optional<Sensor> sensor = sensorAppService.findById(id);
		if (!sensor.isPresent()) {
			throw new SensorNotFoundException("Sensor not found");
		}
		return new ResponseEntity<>(sensor.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Sensor> save(Sensor sensor) {
		log.info("SensorControllerImpl - save");
		log.info(sensor.getDeviceId().toString());
		if (sensor.getDeviceId() == null) {
			throw new ApplicationException("Device ID not found, ID is required to save the data");
		} else {
			Sensor savedSensor = sensorAppService.saveOrUpdate(sensor);
			return new ResponseEntity<>(savedSensor, HttpStatus.CREATED);
		}

	}

	@Override
	public ResponseEntity<Sensor> update(Sensor sensor) {
		log.info("SensorControllerImpl - update");
		if (sensor.getDeviceId() == null) {
			throw new ApplicationException("Device ID not found, Existing ID is required to update the data");
		} else {
			Sensor savedSensor = sensorAppService.saveOrUpdate(sensor);
			return new ResponseEntity<>(savedSensor, HttpStatus.OK);
		}

	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		log.info("SensorControllerImpl - deleteById");
		Optional<Sensor> sensor = sensorAppService.findById(id);
		if (!sensor.isPresent()) {
			throw new SensorNotFoundException("Sensor not found");
		}
		return new ResponseEntity<>(sensorAppService.deleteById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> invalid() {
		log.info("SensorControllerImpl - invalid");
		return new ResponseEntity<>("('message':'something is missing in the request. Please check')",
				HttpStatus.BAD_REQUEST);
	}

}
