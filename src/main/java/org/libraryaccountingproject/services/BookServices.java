package org.libraryaccountingproject.services;

import constatnts.UrlPaths;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.bookDtos.AddBookRequestDto;
import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.dtos.bookDtos.UpdateBookRequestDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.entities.Book;
import org.libraryaccountingproject.entities.BookSubject;
import org.libraryaccountingproject.repositories.BooksRepository;
import org.libraryaccountingproject.services.exeptions.NotFoundException;
import org.libraryaccountingproject.services.utils.converters.AuthorDtoToAuthorConverter;
import org.libraryaccountingproject.services.utils.converters.BookToBookDtoConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

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

        subjectExistValidation(bookDto.getBookSubject());

        Book newBook = getNewBook(bookDto);

        Book savedBook = booksRepository.save(newBook);

        return bookToBookDtoConverter.convertBookToBookResponseDto(savedBook, dtoToAuthorConverter);

    }

    @Transactional
    public BookResponseDto updateBook(UpdateBookRequestDto bookDto) {

        subjectExistValidation(bookDto.getBookSubject());

        if (booksRepository.existsById(bookDto.getId())) {
            Set<Author> authors = getAuthorsSet(bookDto.getAuthorsIds());
            Book bookForUpdate = getBookForUpdate(bookDto);

            return bookToBookDtoConverter.convertBookToBookResponseDto(bookForUpdate, dtoToAuthorConverter);
        } else {

            throw new NotFoundException("Book with id: " + bookDto.getId() + " not found");
        }
    }


    public List<BookResponseDto> findAllBooks() {

        List<Book> books = booksRepository.findAll();
        return getBookResponseDtosFromBooksList(books);
    }

    public BookResponseDto findBookById(Integer id) {
        Optional<Book> foundBook = booksRepository.findById(id);

        if (foundBook.isPresent()) {

            return bookToBookDtoConverter.convertBookToBookResponseDto(foundBook.get(), dtoToAuthorConverter);
        } else {
            throw new NotFoundException("Book could not be found");
        }
    }


    public List<BookResponseDto> findBooksByPartTitle(String title) {

        List<Book> foundBooks = booksRepository.findByPartTitle(title);

        return getBookResponseDtoList(foundBooks);
    }


    public List<BookResponseDto> findBooksBySubjectName(String subjectName) {

        subjectExistValidation(subjectName);

        List<Book> foundBooks = booksRepository.findBySubjectName(subjectName);

        return getBookResponseDtoList(foundBooks);

    }


    public List<BookResponseDto> findBooksByAuthor(Integer authorId) {

        List<Book> foundBooks = booksRepository.findByAuthorId(authorId);

        if (!foundBooks.isEmpty()) {

            return getBookResponseDtoList(foundBooks);
        } else {
            throw new NotFoundException("No books of Author with id: " + authorId + " found");
        }

    }

    public List<BookResponseDto> findBooksByStatus(String status) {

        Book.BookStatus statusForSearch = getBookStatusFromString(status);

        List<Book> books = booksRepository.findByStatus(statusForSearch);

        if (!books.isEmpty()) {
            return getBookResponseDtoList(books);
        } else {
            throw new NotFoundException("No books of status " + status + " found");
        }
    }

    public List<BookResponseDto> findBooksByISBN(String isbn) {

        List<Book> foundBooks = booksRepository.findByISBN(isbn);

        if (!foundBooks.isEmpty()) {

            return getBookResponseDtoList(foundBooks);
        } else {
            throw new NotFoundException("No books of ISBN " + isbn + " found");
        }
    }


// Private service methods

    private Book getNewBook(AddBookRequestDto bookDto) {
        String coverUrl = getCoverImageUriString(bookDto);

        Book.BookStatus status = getBookStatusFromString(bookDto.getStatus());

        BookSubject subject = subjectServices.findSubjectObjectByName(bookDto.getBookSubject());

        Set<Author> authors = getAuthorsSet(bookDto.getAuthorsIds());

        Book newBook = Book.builder()
                .title(bookDto.getBookTitle())
                .authors(authors)
                .codeISBN(bookDto.getCodeISBN())
                .status(status)
                .subject(subject)
                .coverageImageUrl(coverUrl)
                .build();
        return newBook;
    }

    private Book getBookForUpdate(UpdateBookRequestDto bookDto) {

        Book.BookStatus status = getBookStatusFromString(bookDto.getStatus());

        BookSubject subject = subjectServices.findSubjectObjectByName(bookDto.getBookSubject());

        Set<Author> authors = getAuthorsSet(bookDto.getAuthorsIds());

        Book newBook = Book.builder()
                .bookId(bookDto.getId())
                .title(bookDto.getBookTitle())
                .authors(authors)
                .codeISBN(bookDto.getCodeISBN())
                .status(status)
                .subject(subject)
                .coverageImageUrl(bookDto.getCoverUrl())
                .build();
        return newBook;
    }

    private static String getCoverImageUriString(AddBookRequestDto bookDto) {
        return UriComponentsBuilder.fromHttpUrl(UrlPaths.BOOK_COVER)
                .path("{bookDto.getCodeISBN()}-L.jpg")
                .buildAndExpand(bookDto.getCodeISBN())
                .toUriString();
    }

    private static Book.BookStatus getBookStatusFromString(String status) {

        if (status.isBlank()) return Book.BookStatus.AVAILABLE;

        return Arrays.stream(Book.BookStatus.values())
                .filter(objStatus -> objStatus.name().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Book status " + status + " not found"));
    }

    private void subjectExistValidation(String subjectName) {

        if (!subjectServices.checkIsSubjectExist(subjectName)) {
            throw new IllegalArgumentException("Subject does not exist");
        }
    }

    private List<BookResponseDto> getBookResponseDtoList(List<Book> foundBooks) {
        if (!foundBooks.isEmpty()) {
            return getBookResponseDtosFromBooksList(foundBooks);
        } else {
            throw new NotFoundException("Book could not be found");
        }
    }


    private List<BookResponseDto> getBookResponseDtosFromBooksList(List<Book> foundBooks) {

        return foundBooks.stream()
                .map(book -> bookToBookDtoConverter.convertBookToBookResponseDto(book, dtoToAuthorConverter))
                .toList();
    }


    private Set<Author> getAuthorsSet(List<Integer> autorIds) {

        return autorIds.stream()
                .map(authorServices::findAuthorEntityById)
                .collect(Collectors.toSet());
    }

}
