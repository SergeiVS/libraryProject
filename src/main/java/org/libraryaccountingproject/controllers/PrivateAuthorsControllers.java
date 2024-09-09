package org.libraryaccountingproject.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.controllers.api.AuthorsAPI;
import org.libraryaccountingproject.dtos.authorDtos.NewAuthorRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorUpdateRequestDto;
import org.libraryaccountingproject.services.AuthorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/authors/")
@RequiredArgsConstructor
public class PrivateAuthorsControllers implements AuthorsAPI {

    private final AuthorServices authorServices;

    @PostMapping("/author")
    public ResponseEntity<AuthorDataResponseDto> addAuthor(@Valid @RequestBody NewAuthorRequestDto authorDto) {

        return new ResponseEntity<>(authorServices.addAuthor(authorDto), HttpStatus.CREATED);
    }

    @PutMapping("/author")
    public ResponseEntity<AuthorDataResponseDto> updateAuthorData(@Valid @RequestBody AuthorUpdateRequestDto dto){
        return new ResponseEntity<>(authorServices.updateAuthorData(dto),HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<AuthorDataResponseDto> getAuthorById(@PathVariable @Positive(message = "id should be a positive number") Integer id) {
        return new ResponseEntity<>(authorServices.findAuthorById(id),HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {

        return new ResponseEntity<>(authorServices.deleteAuthorById(id), HttpStatus.OK);
    }


}
