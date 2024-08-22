package org.libraryaccountingproject.services;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.requests.AddAuthorRequestDto;
import org.libraryaccountingproject.dtos.responses.AuthorDataResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.repositories.AuthorsRepository;
import org.libraryaccountingproject.services.utils.converters.AuthorDtoToAuthorConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServices {

    private final AuthorsRepository authorsRepository;
    private final AuthorDtoToAuthorConverter dtoToAuthorConverter;

    public AuthorDataResponseDto addAuthor(AddAuthorRequestDto authorDto) {
        Author author = new Author();
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        Optional<Author> savedAuthor = Optional.of(authorsRepository.save(author));
        if (savedAuthor.isPresent()) {
            return dtoToAuthorConverter.authorToAuthorResponseDto(savedAuthor.get());
        } else {
            throw new RuntimeException("Author should not be saved" + this.getClass().getName());

        }
    }

    public Author findAuthorById(Integer id) {
        Optional<Author> author = authorsRepository.findById(Long.valueOf(id));
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new RuntimeException("Author should not be found" + this.getClass().getName());
        }

    }

    public List<AuthorDataResponseDto> findAllAuthors() {
        List<Author> authors = authorsRepository.findAll();
        List<AuthorDataResponseDto> dtos = new ArrayList<>();
        authors.forEach(author -> dtos.add(dtoToAuthorConverter.authorToAuthorResponseDto(author)));
        return dtos;
    }
}
