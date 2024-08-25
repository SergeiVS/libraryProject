package org.libraryaccountingproject.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.entities.BookStatus;
import org.libraryaccountingproject.entities.BookSubject;
import org.libraryaccountingproject.repositories.AuthorsRepository;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private Integer id;
    private String bookTitle;
    private List<AuthorDataResponseDto> authors;
    private String codeISBN;
    private String bookSubject;
    private BookStatus bookStatus;


}
