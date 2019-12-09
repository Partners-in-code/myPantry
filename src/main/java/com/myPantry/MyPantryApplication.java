package com.myPantry;

import java.util.HashSet;
import java.util.Set;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myPantry.MyPantryApplication;
import com.myPantry.domain.User;
import com.myPantry.domain.security.Role;
import com.myPantry.domain.security.UserRole;
import com.myPantry.service.UserService;

@SpringBootApplication
public class MyPantryApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(MyPantryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("Maria");
		user1.setLastName("Bobes");
		user1.setUsername("MB");
		user1.setPassword("mb");
		user1.setEmail("maria.bobes08@gmail.com");

		Set<UserRole> userRoles = new HashSet<>();

		Role role1 = new Role();

		role1.setRoleId(1);
		role1.setName("ROLE_USER");

		userRoles.add(new UserRole(user1, role1));

		userService.createUser(user1, userRoles);

	}

}
