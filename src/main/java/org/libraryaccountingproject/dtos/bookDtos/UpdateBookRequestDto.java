package org.libraryaccountingproject.dtos.bookDtos;

import annotations.ISBNValidation;
import annotations.StringFormatValidation;
import annotations.SubjectValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequestDto {

    @Positive
    @NotNull
    private Integer id;

    private String bookSubject;

    private String status;


}
