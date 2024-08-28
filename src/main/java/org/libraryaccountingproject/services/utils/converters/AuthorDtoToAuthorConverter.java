package org.libraryaccountingproject.services.utils.converters;

import org.libraryaccountingproject.dtos.requests.AddAuthorRequestDto;
import org.libraryaccountingproject.dtos.requests.UpdateAuthorDto;
import org.libraryaccountingproject.dtos.responses.AuthorDataResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoToAuthorConverter {

    public Author newAuthorRequestDtoToAuthor(AddAuthorRequestDto dto) {
        Author author = new Author();
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        return author;

    }

    public AuthorDataResponseDto authorToAuthorResponseDto(Author author) {
        return new AuthorDataResponseDto(author.getId(), author.getFirstName(), author.getLastName());
    }

    public Author updateAuthorRequestDtoToAuthor(UpdateAuthorDto dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        return author;
    }

}
