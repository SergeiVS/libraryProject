package org.libraryaccountingproject.controllers.api;

import annotations.ISBNValidation;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.ISBN;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.dtos.subjectDtos.SubjectDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public interface PublicAPI {

    @GetMapping("/authors")
    ResponseEntity<List<AuthorDataResponseDto>> getAllAuthors();


    @GetMapping("/authors/fullname")
    ResponseEntity<List<AuthorDataResponseDto>> getAuthorByFullName(@RequestParam String firstName, @RequestParam String lastName);

    @GetMapping("/authors/ {lastName}")
    ResponseEntity<List<AuthorDataResponseDto>> getAuthorByLastName(@PathVariable String lastName);

    @GetMapping("/books")
    ResponseEntity<List<BookResponseDto>> getAllBooks();

    @GetMapping("/{title}")
    ResponseEntity<List<BookResponseDto>> getBooksByPartTitle(@PathVariable String title);

    @GetMapping("/books/subject")
    ResponseEntity<List<BookResponseDto>> getBooksBySubjectName(@NotBlank(message = "Request could not be empty") @RequestParam String subject);

    @GetMapping("/books/{isbn}")
    ResponseEntity<List<BookResponseDto>> getBooksByIsbn(@ISBN(groups = ISBNValidation.class) @PathVariable String isbn);


    @GetMapping("/subjects")
    ResponseEntity<List<SubjectDto>> getAllSubjects();


//    @GetMapping("/books/{authorLastName}")
//    public ResponseEntity<List<BookResponseDto>> getBooksByIsbn( @PathVariable String authorLastName);

}
