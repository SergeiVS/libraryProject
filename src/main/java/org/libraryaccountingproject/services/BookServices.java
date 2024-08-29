package org.libraryaccountingproject.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.libraryaccountingproject.dtos.bookDtos.AddBookRequestDto;
import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.entities.BookStatus;
import org.libraryaccountingproject.entities.Book;
import org.libraryaccountingproject.repositories.BooksRepository;
import org.libraryaccountingproject.services.exeptions.NotCreatedException;
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

        subjectExistValidation(bookDto.getBookSubject());

        Book newBook = bookToBookDtoConverter.convertBookRequestDtoToBook(bookDto, subjectServices);
        newBook.setStatus(BookStatus.AVAILABLE);
        newBook.setAuthors(getAuthorsSet(bookDto));
        Optional<Book> savedBook = Optional.of(booksRepository.save(newBook));

        if (savedBook.isPresent()) {

            Book updatedBook = savedBook.get();

            return bookToBookDtoConverter.convertBookToAddBookResponseDto(updatedBook, dtoToAuthorConverter);

        } else {
            throw new NotCreatedException("Book could not be saved");
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

        return getBookResponseDtoList(foundBooks);
    }


    public List<BookResponseDto> findBookBySubjectName(String subjectName) {

        subjectExistValidation(subjectName);

        List<Book> foundBooks = booksRepository.findBySubjectName(subjectName);

        return getBookResponseDtoList(foundBooks);

    }


    public List<BookResponseDto> findBookByAuthor(Integer authorId) {

        List<Book> foundBooks = booksRepository.findByAuthorId(authorId);

        if (!foundBooks.isEmpty()) {

            return getBookResponseDtoList(foundBooks);
        } else {
            throw new NotFoundException("No books of Author with id: " + authorId + " found");
        }

    }

    public List<BookResponseDto> findBookByStatus(String status) {

        System.out.println(status);

        Optional<BookStatus> statusForSearch = getBookStatusFromString(status);

        System.out.println(statusForSearch);

        if (statusForSearch.isPresent()) {

            List<Book> books = booksRepository.findByStatus(statusForSearch.get());

            if (!books.isEmpty()) {

                return getBookResponseDtoList(books);
            } else {

                throw new NotFoundException("No books of status " + status + " found");
            }
        } else {

            throw new NotFoundException("No status " + status + " found");
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

    private static Optional<BookStatus> getBookStatusFromString(String status) {
        return Arrays.stream(BookStatus.values())
                .filter(objStatus -> objStatus.name().equalsIgnoreCase(status))
                .findFirst();
    }

    private void subjectExistValidation(String subjectName) {
        if (!subjectServices.checkIsSubjectExist(subjectName)) {
            throw new NotFoundException("Subject is not exist");
        }
    }

    private List<BookResponseDto> getBookResponseDtoList(List<Book> foundBooks) {
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
