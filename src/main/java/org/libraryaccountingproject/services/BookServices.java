package org.libraryaccountingproject.services;

import constatnts.UrlPaths;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.libraryaccountingproject.dtos.bookDtos.AddBookRequestDto;
import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.dtos.bookDtos.UpdateBookRequestDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.entities.Book;
import org.libraryaccountingproject.entities.BookSubject;
import org.libraryaccountingproject.repositories.BooksRepository;
import org.libraryaccountingproject.services.exeptions.NotFoundException;
import org.libraryaccountingproject.services.utils.mappers.BookMappers;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServices {

    private final BooksRepository booksRepository;
    private final AuthorServices authorServices;
    private final SubjectServices subjectServices;
    private final BookMappers bookMappers;

    @Transactional
    public BookResponseDto addBook(AddBookRequestDto bookDto) {

        subjectExistValidation(bookDto.getBookSubject());

        Book newBook = getNewBook(bookDto);

        Book savedBook = booksRepository.save(newBook);

        return getBookResponseDtoFromBook(savedBook);

    }

    @Transactional
    public BookResponseDto updateBook(UpdateBookRequestDto bookDto) {

        subjectExistValidation(bookDto.getBookSubject());

        Book bookForUpdate = getBookForUpdate(bookDto);

        log.info(bookForUpdate.toString());

        Book savedBook = booksRepository.save(bookForUpdate);

        return getBookResponseDtoFromBook(savedBook);
    }


    public List<BookResponseDto> findAllBooks() {

        List<Book> books = booksRepository.findAll();

        return getBookResponseDtoList(books);
    }

    public BookResponseDto findBookById(Integer id) {
        Book foundBook = booksRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id: " + id + " not found"));

        return getBookResponseDtoFromBook(foundBook);

    }


    public List<BookResponseDto> findBooksByPartTitle(String title) {

        List<Book> foundBooks = booksRepository.findByTitleContaining(title);

        return getBookResponseDtoList(foundBooks);
    }


    public List<BookResponseDto> findBooksBySubjectName(String subjectName) {

        subjectExistValidation(subjectName);

        List<Book> foundBooks = booksRepository.findBySubject(subjectServices.findSubjectObjectByName(subjectName));

        return getBookResponseDtoList(foundBooks);

    }


    public List<BookResponseDto> findBooksByAuthorId(Integer authorId) {

        checkAuthorExistById(authorId);

        List<Book> foundBooks = booksRepository.findByAuthorsId(authorId);

        return getBookResponseDtoList(foundBooks);

    }

    public List<BookResponseDto> findBooksByStatus(String status) {


        Book.BookStatus statusForSearch = getBookStatusFromString(status);

        List<Book> books = booksRepository.findByStatus(statusForSearch);

        return getBookResponseDtoList(books);

    }

    public List<BookResponseDto> findBooksByISBN(String isbn) {

        List<Book> foundBooks = booksRepository.findByCodeISBN(isbn);

        return getBookResponseDtoList(foundBooks);

    }

//    public List<BookResponseDto> findBooksByAuthorLastName(String authorLastName) {
//
//
//
//    }


// Private service methods

    private void checkAuthorExistById(Integer id) {
        if (!authorServices.authorExistById(id)) {
            throw new NotFoundException("Author with id: " + id + " not found");
        }
    }

    private Book getNewBook(AddBookRequestDto bookDto) {

        String coverUrl = getCoverImageUriString(bookDto);
        log.info("COverage URL: " + coverUrl);
        Book.BookStatus status = getBookStatusFromString(bookDto.getStatus());
        BookSubject subject = subjectServices.findSubjectObjectByName(bookDto.getBookSubject());
        Set<Author> authors = authorServices.getAuthorsSetFromAuthorIdsList(bookDto.getAuthorsIds());
        Book book = Book.builder()
                .title(bookDto.getBookTitle())
                .authors(authors)
                .codeISBN(bookDto.getCodeISBN())
                .status(status)
                .subject(subject)
                .coverImageUrl(coverUrl)
                .build();

        log.info(book.toString());
        return book;
    }

    private Book getBookForUpdate(UpdateBookRequestDto bookDto) {

        Book bookForUpdate = booksRepository.findById(bookDto.getId())
                .orElseThrow(() -> new NotFoundException("Book with id: " + bookDto.getId() + " not found"));


        if (!bookDto.getBookSubject().isBlank()) {
            bookForUpdate.setSubject(subjectServices.findSubjectObjectByName(bookDto.getBookSubject()));
        }
        if (!bookDto.getStatus().isBlank()) {
            bookForUpdate.setStatus(getBookStatusFromString(bookDto.getStatus()));
        }
        return bookForUpdate;
    }

    private static String getCoverImageUriString(AddBookRequestDto bookDto) {
        return UriComponentsBuilder.fromHttpUrl(UrlPaths.BOOK_COVER)
                .path("/{bookDto.getCodeISBN()}-L.jpg")
                .buildAndExpand(bookDto.getCodeISBN())
                .toUriString();
    }

    private static Book.BookStatus getBookStatusFromString(String status) {

        return Arrays.stream(Book.BookStatus.values())
                .filter(objStatus -> objStatus.name().equals(status.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book status " + status + " does not exist"));
    }

    private void subjectExistValidation(String subjectName) {

        if (!subjectServices.checkIsSubjectExist(subjectName)) {
            throw new IllegalArgumentException("Subject does not exist");
        }
    }

    private BookResponseDto getBookResponseDtoFromBook(Book book) {

        BookResponseDto dto = bookMappers.toBookResponseDto(book);
        dto.setSubject(book.getSubject().getSubject());
        dto.setStatus(book.getStatus().name());
        return dto;
    }

    private List<BookResponseDto> getBookResponseDtoList(List<Book> foundBooks) {

        if (!foundBooks.isEmpty()) {
            return foundBooks.stream()
                    .map(this::getBookResponseDtoFromBook)
                    .toList();
        } else {
            throw new NotFoundException("No books found");
        }
    }


}
