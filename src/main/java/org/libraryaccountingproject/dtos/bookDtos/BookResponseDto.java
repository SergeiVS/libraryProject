package org.libraryaccountingproject.dtos.bookDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.entities.BookStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
