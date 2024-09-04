package org.libraryaccountingproject.controllers;

import annotations.ISBNValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.libraryaccountingproject.dtos.bookDtos.AddBookRequestDto;
import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.dtos.bookDtos.UpdateBookRequestDto;
import org.libraryaccountingproject.services.BookServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookControllers {

    private final BookServices bookServices;


    @PostMapping("/book")
    public ResponseEntity<BookResponseDto> addBook(@Valid @RequestBody AddBookRequestDto bookDto) {
        return new ResponseEntity<>(bookServices.addBook(bookDto), HttpStatus.CREATED);
    }

    public ResponseEntity<BookResponseDto> updateBook(@Valid @RequestBody UpdateBookRequestDto bookDto) {
        return new ResponseEntity<>(bookServices.updateBook(bookDto), HttpStatus.OK);
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
    public ResponseEntity<List<BookResponseDto>> getBooksBySubjectName(@NotBlank @PathVariable String subject) {
        return new ResponseEntity<>(bookServices.findBooksBySubjectName(subject), HttpStatus.FOUND);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthorId(@Positive @PathVariable int id) {
        return new ResponseEntity<>(bookServices.findBooksByAuthorId(id), HttpStatus.FOUND);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<BookResponseDto>> getBooksByStatus(@NotBlank @PathVariable String status) {
        return new ResponseEntity<>(bookServices.findBooksByStatus(status), HttpStatus.FOUND);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<List<BookResponseDto>> getBooksByIsbn(@ISBN(groups = ISBNValidation.class) @PathVariable String isbn) {
        return new ResponseEntity<>(bookServices.findBooksByISBN(isbn), HttpStatus.FOUND);
    }

}
