package com.mongo.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import com.mongo.crud.model.RegisteredDevices;
import com.mongo.crud.repository.RegisteredDevicesRepository;

@Service
public class RegisteredDevicesServiceImpl implements RegisteredDevicesService {

	@Autowired
	private RegisteredDevicesRepository registeredDevicesRepository;

	@Override
	public void insert(RegisteredDevices registeredDevice) {
		registeredDevicesRepository.insert(registeredDevice);

	}

	@Override
	public List<RegisteredDevices> findAll() {
		return registeredDevicesRepository.findAll();
	}

	@Override
	public RegisteredDevices findItemByDeviceId(String deviceId) {
		return registeredDevicesRepository.findItemByDeviceId(deviceId);
	}

	@Override
	public void deleteByDeviceId(String deviceId) {
		registeredDevicesRepository.deleteByDeviceId(deviceId);

	}

	@Override
	public AggregationResults<RegisteredDevices> getCustomerAccountsByOrgId(String orgId) {
		return registeredDevicesRepository.getCustomerAccountsByOrgId(orgId);

	}
}
