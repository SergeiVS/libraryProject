package org.libraryaccountingproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;


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
    @Pattern(regexp = "^[A-Za-z0-9] $")
    private String firstName;
    @NotBlank(message = "Lastname could not be empty")
    @Pattern(regexp = "^[A-Za-z0-9] $")
    private String lastName;

}
