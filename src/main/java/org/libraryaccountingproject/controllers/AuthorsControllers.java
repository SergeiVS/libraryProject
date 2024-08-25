package org.libraryaccountingproject.controllers;

import lombok.AllArgsConstructor;
import org.libraryaccountingproject.dtos.requests.AddAuthorRequestDto;
import org.libraryaccountingproject.dtos.responses.AuthorDataResponseDto;
import org.libraryaccountingproject.services.AuthorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorsControllers {

    private final AuthorServices authorServices;

    @PostMapping("/add-author")
    public ResponseEntity<AuthorDataResponseDto> addAuthor(@RequestBody AddAuthorRequestDto authorDto) {
        return new ResponseEntity<>(authorServices.addAuthor(authorDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDataResponseDto>> getAllAuthors() {
       return new ResponseEntity<>(authorServices.findAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDataResponseDto> getAuthorById(@PathVariable Integer id) {
        return new ResponseEntity<>(authorServices.findAuthorById(id),HttpStatus.FOUND);
    }

    @GetMapping("/{firstName}-{lastName}")
    public ResponseEntity<AuthorDataResponseDto> getAuthorByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        return new ResponseEntity<>(authorServices.findAuthorByFullname(firstName, lastName), HttpStatus.FOUND) ;
    }
}
