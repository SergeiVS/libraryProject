package org.libraryaccountingproject.controllers;

import jakarta.validation.Valid;
import org.libraryaccountingproject.dtos.bookDtos.AddBookRequestDto;
import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.services.BookServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")

public class BookControllers {

    private final BookServices bookServices;

    public BookControllers(BookServices bookServices) {
        this.bookServices = bookServices;
    }


    @PostMapping("/book")
    public ResponseEntity<BookResponseDto> addBook(@Valid @RequestBody AddBookRequestDto bookDto) {
        return new ResponseEntity<>(bookServices.addOrUpdateBook(bookDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return new ResponseEntity<>(bookServices.findAllBooks(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable int id) {

        return new ResponseEntity<>(bookServices.findBookById(id), HttpStatus.FOUND);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<BookResponseDto>> getBooksByPartTitle(@PathVariable String title) {
        return new ResponseEntity<>(bookServices.findBooksByPartTitle(title), HttpStatus.FOUND);
    }

    @GetMapping("/{subject}")
    public ResponseEntity<List<BookResponseDto>> getBooksBySubjectName(@PathVariable String subject) {
        return new ResponseEntity<>(bookServices.findBooksBySubjectName(subject), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthorId(@PathVariable int id) {
        return new ResponseEntity<>(bookServices.findBooksByAuthor(id), HttpStatus.FOUND);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<BookResponseDto>> getBooksByStatus(@PathVariable String status) {
        return new ResponseEntity<>(bookServices.findBooksByStatus(status), HttpStatus.FOUND);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<List<BookResponseDto>> getBooksByIsbn(@PathVariable String isbn) {
        return new ResponseEntity<>(bookServices.findBooksByISBN(isbn), HttpStatus.FOUND);
    }

}
