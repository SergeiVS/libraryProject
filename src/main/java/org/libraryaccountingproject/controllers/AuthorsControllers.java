package org.libraryaccountingproject.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.authorDtos.NewAuthorRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorUpdateRequestDto;
import org.libraryaccountingproject.services.AuthorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorsControllers {

    private final AuthorServices authorServices;

    @PostMapping("/author")
    public ResponseEntity<AuthorDataResponseDto> addAuthor(@Valid @RequestBody NewAuthorRequestDto authorDto) {

        return new ResponseEntity<>(authorServices.addAuthor(authorDto), HttpStatus.CREATED);
    }

    @PutMapping("/author")
    public ResponseEntity<AuthorDataResponseDto> updateAuthorData(@Valid @RequestBody AuthorUpdateRequestDto dto){
        return new ResponseEntity<>(authorServices.updateAuthorData(dto),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDataResponseDto>> getAllAuthors() {
       return new ResponseEntity<>(authorServices.findAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDataResponseDto> getAuthorById(@PathVariable Integer id) {
        return new ResponseEntity<>(authorServices.findAuthorById(id),HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {

        return new ResponseEntity<>(authorServices.deleteAuthorById(id), HttpStatus.OK);
    }
    @GetMapping("/fullname")
    public ResponseEntity<List<AuthorDataResponseDto>> getAuthorByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        return new ResponseEntity<>(authorServices.findAuthorByFullname(firstName, lastName), HttpStatus.FOUND) ;
    }

    @GetMapping("/{lastName}")
    public ResponseEntity<List<AuthorDataResponseDto>> getAuthorByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(authorServices.findAuthorsByLastName(lastName), HttpStatus.FOUND);
    }

}
