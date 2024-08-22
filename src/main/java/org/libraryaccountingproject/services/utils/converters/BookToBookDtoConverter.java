package org.libraryaccountingproject.services.utils.converters;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.requests.AddBookRequestDto;
import org.libraryaccountingproject.dtos.responses.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.responses.BookResponseDto;
import org.libraryaccountingproject.entities.Book;
import org.libraryaccountingproject.entities.BookStatus;
import org.libraryaccountingproject.entities.BookSubject;
import org.libraryaccountingproject.services.AuthorServices;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookToBookDtoConverter {

    public Book convertBookRequestDtoToBook(AddBookRequestDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getBookTitle());
        book.setSubject(bookDto.getBookSubject());
        book.setCodeISBN(bookDto.getCodeISBN());
        return book;
    }

    public BookResponseDto convertBookToAddBookResponseDto(Book book, List<AuthorDataResponseDto> authors) {

        Integer id = book.getBookId();
        String bookTitle = book.getTitle();
        String codeISBN = book.getCodeISBN();
        BookSubject subject = book.getSubject();
        BookStatus status = book.getStatus();

        return new BookResponseDto(id, bookTitle, authors, codeISBN, subject, status);
    }


}
