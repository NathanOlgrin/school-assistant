package ru.school_assistant.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students_lessons")
public class StudentsLessons {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @JoinColumn(name = "student_id")
    Long studentId;

    @JoinColumn(name = "lesson_id")
    Long lessonId;

    @JoinColumn(name = "assessment")  //Оценка, выставляемая за урок ученику
    int assessment;
}