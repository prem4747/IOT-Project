package com.mongo.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mongo.crud.model.Role;
import com.mongo.crud.model.User;
import com.mongo.crud.service.IService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	@Autowired
	private IService<User> userService;
	
	@Autowired
	private IService<Role> roleService;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleService.saveOrUpdate(new Role(1, "admin"));
		roleService.saveOrUpdate(new Role(2, "user"));

		User user1 = new User();
		user1.setId((long) 1);
		user1.setEmail("test@user.com");
		user1.setName("Test User");
		user1.setRole(roleService.findById(2).get());
		user1.setPassword("testuser");
		user1.setPassword(new BCryptPasswordEncoder().encode("testuser"));
		userService.saveOrUpdate(user1);

		User user2 = new User();
		user2.setId((long) 2);
		user2.setEmail("test@admin.com");
		user2.setName("Test Admin");
		user2.setRole(roleService.findById(1).get());
		user2.setPassword("testadmin");
		user2.setPassword(new BCryptPasswordEncoder().encode("testadmin"));
		userService.saveOrUpdate(user2);
		
	}
}
