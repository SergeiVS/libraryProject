package org.libraryaccountingproject.controllers;

import jakarta.validation.Valid;
import org.libraryaccountingproject.dtos.requests.AddBookRequestDto;
import org.libraryaccountingproject.dtos.responses.BookResponseDto;
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


    @PostMapping("/add-book")
    public ResponseEntity<BookResponseDto> addBook(@Valid @RequestBody AddBookRequestDto bookDto) {
        return new ResponseEntity<>(bookServices.addBook(bookDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return new ResponseEntity<>(bookServices.findAllBooks(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable int id) {

        return new ResponseEntity<>(bookServices.findBookById(id), HttpStatus.FOUND);
    }

    @GetMapping("/find-by-title")
    public ResponseEntity<List<BookResponseDto>> getBooksByPartTitle(@RequestParam String partTitle) {
        return new ResponseEntity<>(bookServices.findBookByPartTitle(partTitle), HttpStatus.FOUND);
    }

    @GetMapping("/find-by-subject")
    public ResponseEntity<List<BookResponseDto>> getBooksBySubjectName(@RequestParam String subjectName) {
        return new ResponseEntity<>(bookServices.findBookBySubjectName(subjectName), HttpStatus.FOUND);
    }

}
