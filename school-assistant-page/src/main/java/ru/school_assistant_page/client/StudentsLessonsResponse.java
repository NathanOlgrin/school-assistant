package ru.school_assistant_page.client;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentsLessonsResponse {

    Long id;
    Long studentsId;
    Long lessonsId;
    int assessment;
    LocalDate dateAssesment;
    boolean attendance;
}
