package org.libraryaccountingproject.services;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.requests.AddBookRequestDto;
import org.libraryaccountingproject.dtos.responses.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.responses.BookResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.entities.BookStatus;
import org.libraryaccountingproject.entities.Book;
import org.libraryaccountingproject.repositories.BooksRepository;
import org.libraryaccountingproject.services.utils.converters.AuthorDtoToAuthorConverter;
import org.libraryaccountingproject.services.utils.converters.BookToBookDtoConverter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookServices {
    private final BooksRepository booksRepository;
    private final AuthorServices authorServices;
    private final BookToBookDtoConverter bookToBookDtoConverter;
    private final AuthorDtoToAuthorConverter dtoToAuthorConverter;

    public BookResponseDto addBook(AddBookRequestDto bookDto) {
        Book newBook = bookToBookDtoConverter.convertBookRequestDtoToBook(bookDto);
        newBook.setStatus(BookStatus.AVAILABLE);
        newBook.setAuthors(getAuthorsSet(bookDto));
        Optional<Book> savedBook = Optional.of(booksRepository.save(newBook));

        if (savedBook.isPresent()) {

            Book updatedBook = savedBook.get();

            List<AuthorDataResponseDto> authors = getAuthorDataResponseDtos(updatedBook);

            return bookToBookDtoConverter.convertBookToAddBookResponseDto(updatedBook, authors);

        } else {
            throw new RuntimeException("Book could not be saved" + this.getClass().getName());
        }
    }

    public List<BookResponseDto> findAllBooks() {

        List<Book> books = booksRepository.findAll();
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();

        books.forEach(book -> {
            List<AuthorDataResponseDto> authors = getAuthorDataResponseDtos(book);
            bookResponseDtos.add(bookToBookDtoConverter.convertBookToAddBookResponseDto(book, authors));
        });
        return bookResponseDtos;
    }

    private List<AuthorDataResponseDto> getAuthorDataResponseDtos(Book updatedBook) {
        List<AuthorDataResponseDto> authors = new ArrayList<>();
        updatedBook.getAuthors()
                .forEach(author -> {
                    AuthorDataResponseDto authorDto = dtoToAuthorConverter.authorToAuthorResponseDto(author);
                    authors.add(authorDto);
                });
        return authors;
    }

    private Set<Author> getAuthorsSet(AddBookRequestDto bookDto) {
        Set<Author> authors = new HashSet<>();
        bookDto.getAuthorsIds()
                .forEach(bookId -> {
                    authors.add(authorServices.findAuthorById(bookId));
                });
        return authors;
    }

}
