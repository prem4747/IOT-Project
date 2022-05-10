package com.mongo.crud.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mongo.crud.interceptor.DeviceInterceptor;

@Component
public class DeviceFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(DeviceInterceptor.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("SensorFilter - doFilter");
		chain.doFilter(request, response);
	}

}
