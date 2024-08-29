package org.libraryaccountingproject.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.libraryaccountingproject.dtos.requests.AddUpdateAuthorRequestDto;
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
    public ResponseEntity<AuthorDataResponseDto> addAuthor(@Valid @RequestBody AddUpdateAuthorRequestDto authorDto) {

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

    @GetMapping("/find-by-fullname")
    public ResponseEntity<List<AuthorDataResponseDto>> getAuthorByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        return new ResponseEntity<>(authorServices.findAuthorByFullname(firstName, lastName), HttpStatus.FOUND) ;
    }

    @GetMapping("/find-by-lastname")
    public ResponseEntity<List<AuthorDataResponseDto>> getAuthorByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(authorServices.findAuthorsByLastName(lastName), HttpStatus.FOUND);
    }

    @PutMapping("/update-author")
    public ResponseEntity<AuthorDataResponseDto> updateAuthorData(@Valid @RequestBody AddUpdateAuthorRequestDto dto){
        return new ResponseEntity<>(authorServices.updateAuthorData(dto),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAuthor(@RequestParam Integer id) {

        return new ResponseEntity<>(authorServices.deleteAuthorById(id), HttpStatus.OK);
    }
}
