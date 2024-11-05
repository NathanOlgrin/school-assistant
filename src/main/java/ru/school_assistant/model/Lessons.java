package ru.school_assistant.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Time;
import java.util.List;

@Data
@Entity
@Table(name = "lessons")
public class Lessons {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private Enum daysOfWeek;
    private Time lessonStart;
    private Time lessonEnd;
    private Long teacherId;
    private Long numberOfClass;
}
