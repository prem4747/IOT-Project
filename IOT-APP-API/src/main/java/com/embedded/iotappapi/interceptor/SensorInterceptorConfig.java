package com.embedded.iotappapi.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class SensorInterceptorConfig extends WebMvcConfigurationSupport {

	@Autowired
	private SensorInterceptor sensorInterceptor;

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sensorInterceptor);
	}
}
