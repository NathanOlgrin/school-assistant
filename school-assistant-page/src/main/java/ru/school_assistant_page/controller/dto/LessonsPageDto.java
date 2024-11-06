package ru.school_assistant_page.controller.dto;

import lombok.Data;
import ru.school_assistant_page.client.DayOfWeek;

import java.time.LocalTime;

@Data
public class LessonsPageDto {

    private String id;
    private String lessonsName;
    private String daysOfWeek;
    private String lessonStart;
    private String lessonEnd;
    private String teacherName;
    private String numberOfClass;
}
