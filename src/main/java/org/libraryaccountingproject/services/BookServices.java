package org.libraryaccountingproject.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.requests.AddBookRequestDto;
import org.libraryaccountingproject.dtos.responses.BookResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.entities.BookStatus;
import org.libraryaccountingproject.entities.Book;
import org.libraryaccountingproject.repositories.BooksRepository;
import org.libraryaccountingproject.services.exeptions.NotFoundException;
import org.libraryaccountingproject.services.utils.converters.AuthorDtoToAuthorConverter;
import org.libraryaccountingproject.services.utils.converters.BookToBookDtoConverter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookServices {
    private final BooksRepository booksRepository;
    private final AuthorServices authorServices;
    private final SubjectServices subjectServices;
    private final BookToBookDtoConverter bookToBookDtoConverter;
    private final AuthorDtoToAuthorConverter dtoToAuthorConverter;

    @Transactional
    public BookResponseDto addBook(AddBookRequestDto bookDto) {
//add validation by odeISBN
        Book newBook = bookToBookDtoConverter.convertBookRequestDtoToBook(bookDto, subjectServices);
        newBook.setStatus(BookStatus.AVAILABLE);
        newBook.setAuthors(getAuthorsSet(bookDto));
        Optional<Book> savedBook = Optional.of(booksRepository.save(newBook));

        if (savedBook.isPresent()) {

            Book updatedBook = savedBook.get();

            return bookToBookDtoConverter.convertBookToAddBookResponseDto(updatedBook, dtoToAuthorConverter);

        } else {
            throw new RuntimeException("Book could not be saved");
        }
    }

    public List<BookResponseDto> findAllBooks() {

        List<Book> books = booksRepository.findAll();
        List<BookResponseDto> bookResponseDtos = getBookResponseDtosFromBooksList(books);
        return bookResponseDtos;
    }

    public BookResponseDto findBookById(Integer id) {
        Optional<Book> foundBook = booksRepository.findById(Long.valueOf(id));

        if (foundBook.isPresent()) {
            return bookToBookDtoConverter.convertBookToAddBookResponseDto(foundBook.get(), dtoToAuthorConverter);
        } else {
            throw new NotFoundException("Book could not be found");
        }
    }

    ;

    public List<BookResponseDto> findBookByPartTitle(String title) {
        List<Book> foundBooks = booksRepository.findByPartTitle(title);
        if (!foundBooks.isEmpty()) {
            List<BookResponseDto> bookDtos = getBookResponseDtosFromBooksList(foundBooks);
            return bookDtos;
        } else {
            throw new NotFoundException("Book could not be found");
        }
    }

    private List<BookResponseDto> getBookResponseDtosFromBooksList(List<Book> foundBooks) {
        List<BookResponseDto> bookDtos = new ArrayList<>();

        foundBooks.forEach(book -> {
            BookResponseDto dto = bookToBookDtoConverter.convertBookToAddBookResponseDto(book, dtoToAuthorConverter);
            bookDtos.add(dto);
        });
        return bookDtos;
    }


    private Set<Author> getAuthorsSet(AddBookRequestDto bookDto) {
        Set<Author> authors = new HashSet<>();
        bookDto.getAuthorsIds()
                .forEach(bookId -> {
                    authors.add(authorServices.findAuthorEntityById(bookId));
                });
        return authors;
    }

}
