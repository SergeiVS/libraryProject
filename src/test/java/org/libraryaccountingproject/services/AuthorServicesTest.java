package org.libraryaccountingproject.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.libraryaccountingproject.dtos.authorDtos.NewAuthorRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.repositories.AuthorsRepository;
import org.libraryaccountingproject.services.exeptions.RestException;
import org.libraryaccountingproject.services.utils.mappers.AuthorMappers;
import org.libraryaccountingproject.services.utils.mappers.AuthorMappersImpl;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorServicesTest {

    private AuthorServices authorServices;
    @Mock
    private AuthorsRepository authorsRepository;
    private Author author;
    private NewAuthorRequestDto requestDto;
    private final AuthorMappers mappers = new AuthorMappersImpl();


    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1);
        author.setFirstName("John");
        author.setLastName("Doe");
        authorsRepository = mock(AuthorsRepository.class);
        when(authorsRepository.save(any())).thenReturn(author);

        authorServices = new AuthorServices(authorsRepository, mappers);
        requestDto = new NewAuthorRequestDto("John", "Doe");

    }


    @Test
    void addAuthorSuccess() {

        AuthorDataResponseDto responseDto = mappers.toAuthorDataResponseDto(author);
        when(authorsRepository.existsByFirstNameAndLastName(any(), any())).thenReturn(false);

        assertEquals(responseDto, authorServices.addAuthor(requestDto), "Expected: " + responseDto + " Real: " + authorServices.addAuthor(requestDto));
    }

    @Test
    void addAuthorAlreadyExistsException() {

        when(authorsRepository.existsByFirstNameAndLastName(any(), any())).thenReturn(true);
        assertThrows(RestException.class, () -> authorServices.addAuthor(requestDto));
    }


}

