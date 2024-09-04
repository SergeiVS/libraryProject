package org.libraryaccountingproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.libraryaccountingproject.dtos.authorDtos.NewAuthorRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.repositories.AuthorsRepository;
import org.libraryaccountingproject.services.utils.converters.AuthorDtoToAuthorConverter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorServicesTest {

//    private AuthorServices authorServices;
//    private AuthorsRepository authorsRepository;
//    private Author author;
//    private AuthorDtoToAuthorConverter converter = new AuthorDtoToAuthorConverter();
//    private NewAuthorRequestDto requestDto;
//    private AuthorDataResponseDto responseDto;
//
//
//    @BeforeEach
//    void setUp() {
//        author = new Author();
//        author.setId(1);
//        author.setFirstName("John");
//        author.setLastName("Doe");
//        authorsRepository = mock(AuthorsRepository.class);
//        authorServices = new AuthorServices(authorsRepository, converter);
//    }
//
//    void fillRequestDto(int id, String firstName, String lastName) {
//
//        requestDto = new NewAuthorRequestDto(id, firstName, lastName);
//    }
//
//    @Test
//    void addAuthor() {
//
//        fillRequestDto(0, "John", "Doe");
//        responseDto = converter.authorToAuthorResponseDto(author);
//        Author authorForSave = converter.newAuthorRequestDtoToAuthor(requestDto);
//        when(authorsRepository.save(authorForSave)).thenReturn(author);
//        assertEquals(responseDto, authorServices.addAuthor(requestDto));
//    }
}