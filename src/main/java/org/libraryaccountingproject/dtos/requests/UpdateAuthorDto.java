package org.libraryaccountingproject.dtos.requests;

import annotations.NameFormatValidation;
import annotations.StringFormatValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateAuthorDto {
    @NotNull
    @Positive
    Integer id;
    @NotBlank
    @StringFormatValidation(groups = NameFormatValidation.class)
    String firstName;
    @NotBlank
    @StringFormatValidation(groups = NameFormatValidation.class)
    String lastName;

}
