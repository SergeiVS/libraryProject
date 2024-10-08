package org.libraryaccountingproject.dtos.bookDtos;

import org.libraryaccountingproject.annotations.ISBNValidation;
import org.libraryaccountingproject.annotations.StringFormatValidation;
import org.libraryaccountingproject.annotations.SubjectValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequestDto {


    @NotBlank(message = "Book title could not be empty")
    @Size(min = 3, message = "Book title length could be between 3 and 25 characters")
    private String bookTitle;

    @NotEmpty(message = "Authors set could not be empty")
    private List<Integer> authorsIds;

    @NotBlank(message = "ISBN code could not be empty")
    @ISBN(groups = ISBNValidation.class)
    private String codeISBN;

    @NotBlank(message = "Book subject could not be empty")
    @StringFormatValidation(groups = SubjectValidation.class)
    private String bookSubject;

    @NotBlank
    private String status;

}
