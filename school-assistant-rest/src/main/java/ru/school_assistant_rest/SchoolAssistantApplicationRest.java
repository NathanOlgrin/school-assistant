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
//		User admin = new User();
//		admin.setLogin("admin");
//		admin.setPassword("$2a$12$srFV5znyP.go2SErncePxeP0C2KMXfGckt4MtiQ98zynyC82G2xc2");  // admin
//
//		admin = userReposiroty.save(admin);
//
//		Roles adminRoles = new Roles();
//		adminRoles.setName("admin");
//		rolesReposiroty.save(adminRoles);
//
//		UserRole adminAdminRole = new UserRole();
//		adminAdminRole.setUserId(admin.getId());
//		adminAdminRole.setRolesId(adminRoles.getId());
//		userRoleRepository.save(adminAdminRole);
	}
}
