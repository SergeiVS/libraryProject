package org.libraryaccountingproject.dtos.subjectDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    @NotNull
    @Positive
    private Integer id;
    @NotBlank
    private String  subject;
}
