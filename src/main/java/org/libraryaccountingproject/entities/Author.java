package org.libraryaccountingproject.entities;

import annotations.NameFormatValidation;
import annotations.StringFormatValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Firstname could not be empty")
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String firstName;

    @NotBlank(message = "Lastname could not be empty")
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String lastName;

}
