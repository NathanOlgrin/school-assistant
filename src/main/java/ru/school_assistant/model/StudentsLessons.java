package ru.school_assistant.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "students_lessons")
public class StudentsLessons {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @JoinColumn(name = "student_id")
    Long studentsId;

    @JoinColumn(name = "lesson_id")
    Long lessonsId;

    @JoinColumn(name = "assessment")  //Оценка, выставляемая за урок ученику
    int assessment;

    @JoinColumn(name = "date_assesment")
    LocalDate dateAssesment;

    @JoinColumn(name = "attendance")
    boolean attendance;
}
