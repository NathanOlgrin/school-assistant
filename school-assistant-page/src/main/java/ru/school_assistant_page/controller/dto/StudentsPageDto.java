package ru.school_assistant_page.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentsPageDto {

    private String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthday;
    private String numberOfClass;
}
