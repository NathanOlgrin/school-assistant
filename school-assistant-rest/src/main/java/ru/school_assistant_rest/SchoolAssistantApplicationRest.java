package ru.school_assistant_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import ru.school_assistant_rest.model.*;
import ru.school_assistant_rest.repository.LessonsRepository;
import ru.school_assistant_rest.repository.StudentsLessonsRepository;
import ru.school_assistant_rest.repository.StudentsRepository;
import ru.school_assistant_rest.repository.TeachersRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@EnableDiscoveryClient
@SpringBootApplication
public class SchoolAssistantApplicationRest {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SchoolAssistantApplicationRest.class, args);
	}

}
