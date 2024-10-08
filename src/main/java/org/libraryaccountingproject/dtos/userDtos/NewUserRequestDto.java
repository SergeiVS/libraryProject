package org.libraryaccountingproject.dtos.userDtos;

import org.libraryaccountingproject.annotations.NameFormatValidation;
import org.libraryaccountingproject.annotations.PasswordValidation;
import org.libraryaccountingproject.annotations.StringFormatValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserRequestDto {

    @NotBlank
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String firstName;
    @NotBlank
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String lastName;
    @NotBlank
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String userLogin;
    @NotBlank
    @StringFormatValidation(groups = PasswordValidation.class)
    private String password;
    @NotBlank
    @Email
    private String email;
}
