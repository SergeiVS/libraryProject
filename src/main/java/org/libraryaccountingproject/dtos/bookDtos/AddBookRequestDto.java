package org.libraryaccountingproject.dtos.bookDtos;

import annotations.ISBNValidation;
import annotations.StringFormatValidation;
import annotations.SubjectValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequestDto {

    @PositiveOrZero(message = "Id should be positive or zero")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Integer id;

    @NotBlank(message = "Book title could not be empty")
    @Size(min = 3,  message = "Book title length could be between 3 and 25 characters")
    private String bookTitle;

    @NotEmpty(message = "Authors set could not be empty")
    private List<Integer> authorsIds;

    @NotBlank(message = "ISBN code could not be empty")
    @ISBN(groups = ISBNValidation.class)
    private String codeISBN;

    @NotBlank(message = "Book subject could not be empty")
    @StringFormatValidation(groups = SubjectValidation.class)
    private String bookSubject;

    private String status;

}
