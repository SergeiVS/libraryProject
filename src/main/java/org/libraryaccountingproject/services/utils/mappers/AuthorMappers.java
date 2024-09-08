package org.libraryaccountingproject.services.utils.mappers;

import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorUpdateRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.NewAuthorRequestDto;
import org.libraryaccountingproject.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMappers {

    AuthorDataResponseDto toAuthorDataResponseDto(Author author);

    void updateAuthorFromAuthorUpdateDto(@MappingTarget Author author, AuthorUpdateRequestDto dto);

    Author newAuthorFromNewAuthorRequestDto(NewAuthorRequestDto dto);
}
