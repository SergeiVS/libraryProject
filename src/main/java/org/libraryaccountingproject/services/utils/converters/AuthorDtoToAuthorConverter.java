package org.libraryaccountingproject.services.utils.converters;

import jakarta.validation.Valid;
import org.libraryaccountingproject.dtos.authorDtos.AddUpdateAuthorRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoToAuthorConverter {

    public Author newAuthorRequestDtoToAuthor(@Valid AddUpdateAuthorRequestDto dto) {
        Author author = new Author();

        if (dto.getId() != null) {
            author.setId(0);
        } else {
            author.setId(dto.getId());
        }

        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        return author;

    }

    public AuthorDataResponseDto authorToAuthorResponseDto(Author author) {
        return new AuthorDataResponseDto(author.getId(), author.getFirstName(), author.getLastName());
    }


}
