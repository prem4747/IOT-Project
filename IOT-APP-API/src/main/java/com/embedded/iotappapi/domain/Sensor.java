package com.embedded.iotappapi.domain;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sensor_app")
public class Sensor {

	@Id
	@Column(name = "device_id")
	@NotNull
	private Long deviceId;

	@Basic
	@Column(name = "temperature")
	@NotNull
	private Double temperature;

	@Basic
	@Column(name = "battery_voltage")
	@NotNull
	private Double batteryVoltage;

	@Column(name = "is_on")
	@NotNull
	private boolean isOn;

	@Basic
	@Column(name = "timestamp")
	@NotNull
	private Timestamp timestamp;

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(Double batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
