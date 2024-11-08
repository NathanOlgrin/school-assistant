package ru.school_assistant_page.controller.dto;

import lombok.Data;
import ru.school_assistant_page.client.StudentsResponse;

import java.util.List;

@Data
public class ClassPageDto {

    private String numberOfClass;
    private String teachersName;
    private String studentsName;
}
