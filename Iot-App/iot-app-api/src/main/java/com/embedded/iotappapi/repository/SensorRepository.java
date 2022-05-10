package com.embedded.iotappapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.embedded.iotappapi.domain.Sensor;

@Repository
public interface SensorRepository extends PagingAndSortingRepository<Sensor, Long> {

}
