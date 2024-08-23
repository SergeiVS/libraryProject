package org.libraryaccountingproject.controllers;

import lombok.AllArgsConstructor;
import org.libraryaccountingproject.dtos.requests.AddAuthorRequestDto;
import org.libraryaccountingproject.dtos.responses.AuthorDataResponseDto;
import org.libraryaccountingproject.services.AuthorServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorsControllers {

    private final AuthorServices authorServices;

    @PostMapping("/add-author")
    public AuthorDataResponseDto addAuthor(@RequestBody AddAuthorRequestDto authorDto) {
        return authorServices.addAuthor(authorDto);
    }

    @GetMapping
    public List<AuthorDataResponseDto> getAllAuthors() {
       return authorServices.findAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDataResponseDto getAuthorById(@PathVariable Integer id) {
        return authorServices.findAuthorById(id);
    }

    @GetMapping("/{firstName}-{lastName}")
    public AuthorDataResponseDto getAuthorByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        return authorServices.findAuthorsByFullname(firstName, lastName);
    }
}
