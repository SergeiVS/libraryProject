package org.libraryaccountingproject.controllers;

import org.libraryaccountingproject.dtos.requests.AddBookRequestDto;
import org.libraryaccountingproject.dtos.responses.BookResponseDto;
import org.libraryaccountingproject.services.BookServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")

public class BookControllers {

    private final BookServices bookServices;

    public BookControllers(BookServices bookServices) {
        this.bookServices = bookServices;
    }


    @PostMapping("/add-book")
    public BookResponseDto addBook(@RequestBody AddBookRequestDto bookDto) {
        return bookServices.addBook(bookDto);
    }

    @GetMapping
    public List<BookResponseDto> getAllBooks() {
        return bookServices.findAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable int id) {

        return bookServices.findBookById(id);
    }

}
