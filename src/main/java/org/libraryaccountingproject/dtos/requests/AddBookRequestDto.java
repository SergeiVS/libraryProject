package org.libraryaccountingproject.dtos.requests;

import annotations.StringFormatValidation;
import annotations.SubjectValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.ISBN;
import org.libraryaccountingproject.entities.BookSubject;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequestDto {

    @NotBlank(message = "Book title could not be empty")
    @Size(min = 3, max = 25, message = "Book title length could be between 3 and 25 characters")
    private String bookTitle;

    @NotBlank(message = "Authors set could not be empty")
    private List<Integer> authorsIds;

    @NotBlank(message = "ISBN code could not be empty")
    @ISBN
    private String codeISBN;

    @NotBlank(message = "Book subject could not be empty")
    @StringFormatValidation(groups = SubjectValidation.class)
    private String bookSubject;

}
