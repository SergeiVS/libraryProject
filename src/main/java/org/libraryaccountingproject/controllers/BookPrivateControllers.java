package org.libraryaccountingproject.controllers;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.libraryaccountingproject.controllers.api.BookAPI;
import org.libraryaccountingproject.dtos.bookDtos.AddBookRequestDto;
import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.dtos.bookDtos.UpdateBookRequestDto;
import org.libraryaccountingproject.services.BookServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/private/books")
@RequiredArgsConstructor
public class BookPrivateControllers implements BookAPI {

    private final BookServices bookServices;


    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(@Valid @RequestBody AddBookRequestDto bookDto) {
        return new ResponseEntity<>(bookServices.addBook(bookDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BookResponseDto> updateBook(@Valid @RequestBody UpdateBookRequestDto bookDto) {
        return new ResponseEntity<>(bookServices.updateBook(bookDto), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable int id) {
        return new ResponseEntity<>(bookServices.findBookById(id), HttpStatus.FOUND);
    }


    @GetMapping("/author/{id}")
    public  ResponseEntity<List<BookResponseDto>> getBooksByAuthorId(@Positive(message = "id must be positive number") @PathVariable int id) {
        log.info("getBooksByAuthorId {}", id);
        return new ResponseEntity<>(bookServices.findBooksByAuthorId(id), HttpStatus.FOUND);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<BookResponseDto>> getBooksByStatus(@NotBlank @PathVariable String status) {
        return new ResponseEntity<>(bookServices.findBooksByStatus(status), HttpStatus.FOUND);
    }

}
