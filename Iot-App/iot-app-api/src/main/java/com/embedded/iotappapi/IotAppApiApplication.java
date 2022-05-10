package com.embedded.iotappapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.embedded.iotappapi.domain.Role;
import com.embedded.iotappapi.domain.Sensor;
import com.embedded.iotappapi.domain.User;
import com.embedded.iotappapi.service.IService;

@SpringBootApplication
public class IotAppApiApplication implements CommandLineRunner {

	@Autowired
	private IService<Sensor> sensorService;
	@Autowired
	private IService<User> userService;
	@Autowired
	private IService<Role> roleService;

	public static void main(String[] args) {
		SpringApplication.run(IotAppApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleService.saveOrUpdate(new Role(1L, "admin"));
		roleService.saveOrUpdate(new Role(2L, "user"));

		User user1 = new User();
		user1.setId((long) 1);
		user1.setEmail("test@user.com");
		user1.setName("Test User");
		user1.setRole(roleService.findById(2L).get());
		user1.setPassword("testuser");
		user1.setPassword(new BCryptPasswordEncoder().encode("testuser"));
		userService.saveOrUpdate(user1);

		User user2 = new User();
		user2.setId((long) 2);
		user2.setEmail("test@admin.com");
		user2.setName("Test Admin");
		user2.setRole(roleService.findById(1L).get());
		user2.setPassword("testadmin");
		user2.setPassword(new BCryptPasswordEncoder().encode("testadmin"));
		userService.saveOrUpdate(user2);

		for (int i = 1; i < 101; i++) {
			Sensor sensor = new Sensor();
			sensor.setDeviceId((long) i);
			sensor.setTemperature((double) i);
			sensor.setBatteryVoltage((double) i);
			sensor.setOn(false);
			sensor.setTimestamp(null);
			sensorService.saveOrUpdate(sensor);
		}

	}
}
