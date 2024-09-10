package org.libraryaccountingproject.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.libraryaccountingproject.dtos.authorDtos.AuthorUpdateRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.NewAuthorRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.repositories.AuthorsRepository;
import org.libraryaccountingproject.exeptions.RestException;
import org.libraryaccountingproject.services.utils.mappers.AuthorMappers;
import org.libraryaccountingproject.services.utils.mappers.AuthorMappersImpl;
import org.mockito.Mock;

import java.util.Optional;

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
    private AuthorUpdateRequestDto updateRequestDto;
    private AuthorDataResponseDto responseDto;
    private final AuthorMappers mappers = new AuthorMappersImpl();


    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1);
        author.setFirstName("John");
        author.setLastName("Doe");
        authorsRepository = mock(AuthorsRepository.class);
        when(authorsRepository.save(any())).thenReturn(author);
        updateRequestDto = new AuthorUpdateRequestDto(author.getId(), author.getFirstName(), author.getLastName());
        authorServices = new AuthorServices(authorsRepository, mappers);
        requestDto = new NewAuthorRequestDto("John", "Doe");
        responseDto = mappers.toAuthorDataResponseDto(author);
    }


    @Test
    void addAuthorSuccess() {


        when(authorsRepository.existsByFirstNameAndLastName(any(), any())).thenReturn(false);

        assertEquals(responseDto, authorServices.addAuthor(requestDto), "Expected: " + responseDto + " Real: " + authorServices.addAuthor(requestDto));
    }

    @Test
    void addAuthorAlreadyExistsException() {

        when(authorsRepository.existsByFirstNameAndLastName(any(), any())).thenReturn(true);
        assertThrowsExactly(RestException.class, () -> authorServices.addAuthor(requestDto));

    }


    @Test
    void updateAuthorData() {
        when(authorsRepository.findById(any())).thenReturn(Optional.of(author));
        assertEquals(responseDto, authorServices.addAuthor(requestDto), "Expected: " + responseDto + " Real: " + authorServices.addAuthor(requestDto));

    }
}

