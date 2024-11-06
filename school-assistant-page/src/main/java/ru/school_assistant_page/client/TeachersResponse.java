package ru.school_assistant_page.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
public class TeachersResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    private QualificationCategory category;
    private Long numberOfClass;
}
