package org.libraryaccountingproject.dtos.authorDtos;

import org.libraryaccountingproject.annotations.NameFormatValidation;
import org.libraryaccountingproject.annotations.StringFormatValidation;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequestDto {

    @NotNull
    @Positive
    Integer id;

    @Nullable
    @Size(min = 3, max = 25, message = "Firstname length could be between 3 and 25 characters")
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String firstName;

    @Nullable
    @Size(min = 3, max = 25, message = "Lastname length could be between 3 and 25 characters")
    @StringFormatValidation(groups = NameFormatValidation.class)
    private String lastName;


}
