package ru.school_assistant.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teachers_students")
public class TeachersStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @JoinColumn(name = "teacher_id")
    Long teacherId;

    @JoinColumn(name = "student_id")
    Long studentId;
}
