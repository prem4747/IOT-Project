package com.mongo.crud.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class DeviceInterceptorConfig extends WebMvcConfigurationSupport {

	@Autowired
	private DeviceInterceptor deviceInterceptor;

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(deviceInterceptor);
	}
}
