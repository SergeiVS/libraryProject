package org.libraryaccountingproject.controllers.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorUpdateRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.NewAuthorRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/authors")
public interface AuthorsAPI {

    @PostMapping("/author")
    ResponseEntity<AuthorDataResponseDto> addAuthor(@Valid @RequestBody NewAuthorRequestDto authorDto);

    @PutMapping("/author")
    ResponseEntity<AuthorDataResponseDto> updateAuthorData(@Valid @RequestBody AuthorUpdateRequestDto dto);


    @GetMapping("/{id}")
    ResponseEntity<AuthorDataResponseDto> getAuthorById(@PathVariable @Positive(message = "id should be a positive number") Integer id);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteAuthor(@PathVariable Integer id);

}
