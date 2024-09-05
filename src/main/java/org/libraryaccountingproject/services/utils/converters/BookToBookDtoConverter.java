package org.libraryaccountingproject.services.utils.converters;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.bookDtos.AddBookRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.dtos.bookDtos.UpdateBookRequestDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.entities.Book;
import org.libraryaccountingproject.services.SubjectServices;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BookToBookDtoConverter {



    public BookResponseDto convertBookToBookResponseDto(Book book, Set<AuthorDataResponseDto> authors) {


        Integer id = book.getBookId();
        String bookTitle = book.getTitle();
        String codeISBN = book.getCodeISBN();
        String subject = book.getSubject().getSubject();
        Book.BookStatus status = book.getStatus();
        String coverImageUrl = book.getCoverageImageUrl();

        return new BookResponseDto(id, bookTitle, authors, codeISBN, subject, status.name(), coverImageUrl);
    }


    private List<AuthorDataResponseDto> getAuthorDataResponseDtos(Book book, AuthorDtoToAuthorConverter converter) {

        return book.getAuthors().stream()
                .map(converter::authorToAuthorResponseDto)
                .toList();
    }

}
