package ru.school_assistant_page.client;

import lombok.Data;

import java.util.List;

@Data
public class ClassResponse {

    private Long numberOfClass;
    private String teachersName;
    private String studentsName;
}
