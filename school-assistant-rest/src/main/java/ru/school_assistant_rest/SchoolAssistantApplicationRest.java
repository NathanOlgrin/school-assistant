package ru.school_assistant_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import ru.school_assistant_rest.model.user_model.*;
import ru.school_assistant_rest.repository.security_repository.RolesRepository;
import ru.school_assistant_rest.repository.security_repository.UserRepository;
import ru.school_assistant_rest.repository.security_repository.UserRoleRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@EnableDiscoveryClient
@SpringBootApplication
public class SchoolAssistantApplicationRest {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SchoolAssistantApplicationRest.class, args);

//		UserRepository userReposiroty = context.getBean(UserRepository.class);
//		UserRoleRepository userRoleRepository = context.getBean(UserRoleRepository.class);
//		RolesRepository rolesReposiroty = context.getBean(RolesRepository.class);
//
//		User user = new User();
//		user.setLogin("user");
//		user.setPassword("$2a$12$Vqo1hQvoKxSvNDzMospHpOP7TqAFjq9GPW/t3oLWUzJXCLUXsm7mi");  // user
//
//		user = userReposiroty.save(user);
//
//		Roles userRoles = new Roles();
//		userRoles.setName("user");
//		rolesReposiroty.save(userRoles);
//
//		UserRole adminAdminRole = new UserRole();
//		adminAdminRole.setUserId(user.getId());
//		adminAdminRole.setRolesId(userRoles.getId());
//		userRoleRepository.save(adminAdminRole);
	}
}
