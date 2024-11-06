package ru.school_assistant_page.client;

import lombok.Data;

import java.time.LocalTime;

@Data
public class LessonsResponse {

    private Long id;
    private String lessonsName;
    private DayOfWeek daysOfWeek;
    private LocalTime lessonStart;
    private LocalTime lessonEnd;
    private Long teacherId;
    private Long numberOfClass;
}
