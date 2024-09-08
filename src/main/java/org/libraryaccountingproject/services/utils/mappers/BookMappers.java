package org.libraryaccountingproject.services.utils.mappers;

import org.libraryaccountingproject.dtos.bookDtos.BookResponseDto;
import org.libraryaccountingproject.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuthorMappers.class})
public interface BookMappers {

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "subject", ignore = true)
    BookResponseDto toBookResponseDto(Book book);
}