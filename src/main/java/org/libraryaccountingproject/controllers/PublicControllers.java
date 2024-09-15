package org.libraryaccountingproject.controllers;

import annotations.ISBNValidation;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.libraryaccountingproject.controllers.api.PublicAPI;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.dtos.subjectDtos.SubjectDto;
import org.libraryaccountingproject.services.AuthorServices;
import org.libraryaccountingproject.services.BookServices;
import org.libraryaccountingproject.services.SubjectServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicControllers implements PublicAPI {

    private final AuthorServices authorServices;
    private final BookServices bookServices;
    private final SubjectServices subjectServices;


    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDataResponseDto>> getAllAuthors() {
        return new ResponseEntity<>(authorServices.findAllAuthors(), HttpStatus.OK);
    }


    @GetMapping("/authors/fullname")
    public ResponseEntity<List<AuthorDataResponseDto>> getAuthorByFullName(@RequestParam @Nullable String firstName, @RequestParam @Nullable String lastName) {
        return new ResponseEntity<>(authorServices.findAuthorByFullname(firstName, lastName), HttpStatus.FOUND);
    }

    @GetMapping("/authors/ {lastName}")
    public ResponseEntity<List<AuthorDataResponseDto>> getAuthorByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(authorServices.findAuthorsByLastName(lastName), HttpStatus.FOUND);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return new ResponseEntity<>(bookServices.findAllBooks(), HttpStatus.FOUND);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<BookResponseDto>> getBooksByPartTitle(@PathVariable String title) {
        return new ResponseEntity<>(bookServices.findBooksByPartTitle(title), HttpStatus.FOUND);
    }

    @GetMapping("/books/subject")
    public ResponseEntity<List<BookResponseDto>> getBooksBySubjectName(@NotBlank(message = "Request could not be empty") @RequestParam String subject) {
        return new ResponseEntity<>(bookServices.findBooksBySubjectName(subject), HttpStatus.FOUND);
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<List<BookResponseDto>> getBooksByIsbn(@ISBN(groups = ISBNValidation.class) @PathVariable String isbn) {
        return new ResponseEntity<>(bookServices.findBooksByISBN(isbn), HttpStatus.FOUND);
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        return new ResponseEntity<>(subjectServices.findAllSubjects(), HttpStatus.FOUND);
    }

//    @GetMapping("/books/author")
//    public ResponseEntity<List<BookResponseDto>> getBooksByAuthorLastName(@NotBlank(message = "Request could not be empty") @RequestParam String authorLastName) {
//        return new ResponseEntity<>(bookServices.findBooksByAuthorLastName(authorLastName), HttpStatus.FOUND);
//    }

}
