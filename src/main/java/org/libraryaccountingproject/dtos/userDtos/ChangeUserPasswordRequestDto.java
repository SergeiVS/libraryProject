package org.libraryaccountingproject.dtos.userDtos;

import org.libraryaccountingproject.annotations.PasswordValidation;
import org.libraryaccountingproject.annotations.StringFormatValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserPasswordRequestDto {

    @NotNull
    @Positive
    private Integer userId;
    @NotBlank
    private String oldPassword;
    @StringFormatValidation(groups = PasswordValidation.class)
    private String newPassword;
}
