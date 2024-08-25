package org.libraryaccountingproject.services.utils.converters;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.requests.AddBookRequestDto;
import org.libraryaccountingproject.dtos.responses.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.responses.BookResponseDto;
import org.libraryaccountingproject.entities.Book;
import org.libraryaccountingproject.entities.BookStatus;
import org.libraryaccountingproject.entities.BookSubject;
import org.libraryaccountingproject.services.AuthorServices;
import org.libraryaccountingproject.services.SubjectServices;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookToBookDtoConverter {

    public Book convertBookRequestDtoToBook(AddBookRequestDto bookDto, SubjectServices subjectServices) {
        Book book = new Book();
        book.setTitle(bookDto.getBookTitle());
        book.setSubject(subjectServices.findSubjectByName(bookDto.getBookSubject()));
        book.setCodeISBN(bookDto.getCodeISBN());
        return book;
    }

    public BookResponseDto convertBookToAddBookResponseDto(Book book, AuthorDtoToAuthorConverter converter) {

        Integer id = book.getBookId();
        String bookTitle = book.getTitle();
        String codeISBN = book.getCodeISBN();
        List<AuthorDataResponseDto> authors = getAuthorDataResponseDtos(book, converter);
        String subject = book.getSubject().getSubject();
        BookStatus status = book.getStatus();

        return new BookResponseDto(id, bookTitle, authors, codeISBN, subject, status);
    }

    private List<AuthorDataResponseDto> getAuthorDataResponseDtos(Book book, AuthorDtoToAuthorConverter converter) {
        List<AuthorDataResponseDto> authors = new ArrayList<>();
        book.getAuthors()
                .forEach(author -> {
                    AuthorDataResponseDto authorDto = converter.authorToAuthorResponseDto(author);
                    authors.add(authorDto);
                });
        return authors;
    }

}
