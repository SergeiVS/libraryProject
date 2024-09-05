package org.libraryaccountingproject.dtos.bookDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.entities.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private Integer id;
    private String bookTitle;
    private Set<AuthorDataResponseDto> authors;
    private String codeISBN;
    private String bookSubject;
    private String bookStatus;
    private String coverImageUrl;


}
