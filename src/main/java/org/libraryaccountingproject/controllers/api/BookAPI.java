package org.libraryaccountingproject.controllers.api;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.libraryaccountingproject.dtos.bookDtos.AddBookRequestDto;
import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.dtos.bookDtos.UpdateBookRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/books")
public interface BookAPI {

    @PostMapping
    ResponseEntity<BookResponseDto> addBook(@Valid @RequestBody AddBookRequestDto bookDto);

    @PutMapping
    ResponseEntity<BookResponseDto> updateBook(@Valid @RequestBody UpdateBookRequestDto bookDto);

    @GetMapping("/book/{id}")
    ResponseEntity<BookResponseDto> getBookById(@Positive @PathVariable int id);


    @GetMapping("/author/{id}")
    ResponseEntity<List<BookResponseDto>> getBooksByAuthorId(@Positive(message = "id must be positive number") @PathVariable int id);

    @GetMapping("/{status}")
    ResponseEntity<List<BookResponseDto>> getBooksByStatus(@NotBlank @PathVariable String status);

}
