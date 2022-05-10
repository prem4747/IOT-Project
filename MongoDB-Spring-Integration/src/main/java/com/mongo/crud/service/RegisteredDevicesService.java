package com.mongo.crud.service;

import java.util.List;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import com.mongo.crud.model.RegisteredDevices;

public interface RegisteredDevicesService {

	void insert(RegisteredDevices registeredDevices);

	List<RegisteredDevices> findAll();

	RegisteredDevices findItemByDeviceId(String deviceId);

	void deleteByDeviceId(String deviceId);

	AggregationResults<RegisteredDevices> getCustomerAccountsByOrgId(String orgId);

}
