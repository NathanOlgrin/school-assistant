package ru.school_assistant.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "lessons")
public class Lessons {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    private String lessonsName;
    private Enum daysOfWeek;
    private LocalTime lessonStart;
    private LocalTime lessonEnd;
    private Long teacherId;
    private Long numberOfClass;
}
