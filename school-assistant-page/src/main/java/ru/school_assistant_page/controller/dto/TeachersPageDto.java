package ru.school_assistant_page.controller.dto;

import lombok.Data;
import ru.school_assistant_page.client.QualificationCategory;

import java.time.LocalDate;

@Data
public class TeachersPageDto {

    private String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthday;
    private String category;
    private String numberOfClass;
}
