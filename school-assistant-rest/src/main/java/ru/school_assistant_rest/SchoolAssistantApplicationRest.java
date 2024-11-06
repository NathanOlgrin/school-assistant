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

		LessonsRepository lessonsRepository = context.getBean(LessonsRepository.class);
		TeachersRepository teachersRepository = context.getBean(TeachersRepository.class);
		StudentsRepository studentsRepository = context.getBean(StudentsRepository.class);
		StudentsLessonsRepository studentsLessonsRepository = context.getBean(StudentsLessonsRepository.class);

		Lessons lessons1 = new Lessons();
		lessons1.setLessonsName("Алгебра");
		lessons1.setLessonStart(LocalTime.of(10,0,0));
		lessons1.setLessonEnd(lessons1.getLessonStart().plusMinutes(45));
		lessons1.setDaysOfWeek(DayOfWeek.MONDAY);
		lessons1.setNumberOfClass(8L);
		lessons1.setTeacherId(1L);

		Lessons lessons2 = new Lessons();
		lessons2.setLessonsName("Геометрия");
		lessons2.setLessonStart(LocalTime.of(9,10,0));
		lessons2.setLessonEnd(lessons2.getLessonStart().plusMinutes(45));
		lessons2.setDaysOfWeek(DayOfWeek.TUESDAY);
		lessons2.setNumberOfClass(9L);
		lessons2.setTeacherId(2L);

		lessonsRepository.save(lessons1);
		lessonsRepository.save(lessons2);

		Teachers teacher1 = new Teachers();
		teacher1.setFirstName("Иванов");
		teacher1.setMiddleName("Иван");
		teacher1.setLastName("Иванович");
		teacher1.setBirthday(LocalDate.of(1978,12,11));
		teacher1.setCategory(QualificationCategory.HIGHEST);
		teacher1.setNumberOfClass(9L);

		Teachers teacher2 = new Teachers();
		teacher2.setFirstName("Петров");
		teacher2.setMiddleName("Петр");
		teacher2.setLastName("Петрович");
		teacher2.setBirthday(LocalDate.of(1989,4,21));
		teacher2.setCategory(QualificationCategory.FIRST);
		teacher2.setNumberOfClass(8L);

		teachersRepository.save(teacher1);
		teachersRepository.save(teacher2);

		Students students1 = new Students();
		students1.setFirstName("Сидоров");
		students1.setMiddleName("Сидор");
		students1.setLastName("Сидорович");
		students1.setBirthday(LocalDate.of(2005,8,25));
		students1.setNumberOfClass(8L);

		Students students2 = new Students();
		students2.setFirstName("Павлов");
		students2.setMiddleName("Павел");
		students2.setLastName("Павлович");
		students2.setBirthday(LocalDate.of(2004,3,5));
		students2.setNumberOfClass(9L);

		studentsRepository.save(students1);
		studentsRepository.save(students2);

		StudentsLessons studentsLessons1 = new StudentsLessons();
		studentsLessons1.setLessonsId(1L);
		studentsLessons1.setStudentsId(1L);
		studentsLessons1.setAssessment(5);
		studentsLessons1.setDateAssesment(LocalDate.of(2024,11,4));
		studentsLessons1.setAttendance(true);

		StudentsLessons studentsLessons2 = new StudentsLessons();
		studentsLessons2.setLessonsId(2L);
		studentsLessons2.setStudentsId(2L);
		studentsLessons2.setAssessment(4);
		studentsLessons2.setDateAssesment(LocalDate.of(2024,11,5));
		studentsLessons2.setAttendance(true);

		studentsLessonsRepository.save(studentsLessons1);
		studentsLessonsRepository.save(studentsLessons2);
	}

}
