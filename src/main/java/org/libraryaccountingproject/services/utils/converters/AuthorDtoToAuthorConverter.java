package org.libraryaccountingproject.services.utils.converters;

import jakarta.validation.Valid;
import org.libraryaccountingproject.dtos.authorDtos.NewAuthorRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorUpdateRequestDto;
import org.libraryaccountingproject.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoToAuthorConverter {

    public Author newAuthorRequestDtoToAuthor(@Valid NewAuthorRequestDto dto) {
        return Author.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();

    }

    public AuthorDataResponseDto authorToAuthorResponseDto(Author author) {
        return new AuthorDataResponseDto(author.getId(), author.getFirstName(), author.getLastName());
    }


}
