package ru.school_assistant.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Entity
@Table(name = "teashers")
public class Teashers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private Enum category;
}
