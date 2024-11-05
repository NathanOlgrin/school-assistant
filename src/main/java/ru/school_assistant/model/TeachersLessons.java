package ru.school_assistant.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teachers_lessons")
public class TeachersLessons {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @JoinColumn(name = "teasher_id")
    Long teasherId;

    @JoinColumn(name = "lesson_id")
    Long lesson_id;
}