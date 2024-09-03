package org.libraryaccountingproject.dtos.authorDtos;

import annotations.NameFormatValidation;
import annotations.StringFormatValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AuthorUpdateRequestDto {

    @NotBlank
    @Positive
    Integer id;

    @NotBlank(message = "Firstname could not be empty")
    @Size(min = 3, max = 25, message = "Firstname length could be between 3 and 25 characters")
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String firstName;

    @NotBlank(message = "Lastname could not be empty")
    @Size(min = 3, max = 25, message = "Lastname length could be between 3 and 25 characters")
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String lastName;
}
