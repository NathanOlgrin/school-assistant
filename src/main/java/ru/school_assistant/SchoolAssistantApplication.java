package ru.school_assistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.school_assistant.model.*;
import ru.school_assistant.repository.LessonsRepository;
import ru.school_assistant.repository.StudentsLessonsRepository;
import ru.school_assistant.repository.StudentsRepository;
import ru.school_assistant.repository.TeachersRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class SchoolAssistantApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SchoolAssistantApplication.class, args);
}
