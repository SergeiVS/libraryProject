package org.libraryaccountingproject.dtos.userDtos;

import org.libraryaccountingproject.annotations.NameFormatValidation;
import org.libraryaccountingproject.annotations.StringFormatValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {

    @NotNull
    @Positive
    private Integer userId;
    @NotBlank
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String firstName;
    @NotBlank
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String lastName;
    @NotBlank
    @Email
    private String userEmail;
    @NotBlank
    private String userRole;
}
