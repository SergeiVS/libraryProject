package org.libraryaccountingproject.services;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.requests.AddAuthorRequestDto;
import org.libraryaccountingproject.dtos.responses.AuthorDataResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.repositories.AuthorsRepository;
import org.libraryaccountingproject.services.exeptions.AlreadyExistException;
import org.libraryaccountingproject.services.exeptions.NotCreatedException;
import org.libraryaccountingproject.services.exeptions.NotFoundException;
import org.libraryaccountingproject.services.utils.converters.AuthorDtoToAuthorConverter;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServices {

    private final AuthorsRepository authorsRepository;
    private final AuthorDtoToAuthorConverter dtoToAuthorConverter;

    public AuthorDataResponseDto addAuthor(AddAuthorRequestDto authorDto) {

        if (this.authorsRepository.findByFirstNameAndLastName(authorDto.getFirstName(), authorDto.getLastName()).isEmpty()) {

            Optional<Author> savedAuthor = getSavedAuthor(authorDto);

            if (savedAuthor.isPresent()) {

                return dtoToAuthorConverter.authorToAuthorResponseDto(savedAuthor.get());

            } else {
                throw new NotCreatedException("Author could not be created");

            }
        } else {
            throw new AlreadyExistException("Author already exists");
        }

    }


    public AuthorDataResponseDto findAuthorById(Integer id) {

        Optional<Author> author = authorsRepository.findById(Long.valueOf(id));

        if (author.isPresent()) {

            return dtoToAuthorConverter.authorToAuthorResponseDto(author.get());

        } else {
            throw new NotFoundException("Author should not be found");
        }

    }

    public List<AuthorDataResponseDto> findAllAuthors() {

        List<Author> authors = authorsRepository.findAll();
        if (authors.isEmpty()) {
            List<AuthorDataResponseDto> dtos = new ArrayList<>();

            authors.forEach(author -> dtos.add(dtoToAuthorConverter.authorToAuthorResponseDto(author)));

            return dtos;
        } else {
            throw new NotFoundException("Authors should not be found");
        }
    }

    public AuthorDataResponseDto findAuthorByFullname(String firstName, String lastName) {

        Optional<Author> foundAuthor = authorsRepository.findByFirstNameAndLastName(firstName, lastName);

        if (foundAuthor.isPresent()) {
            return dtoToAuthorConverter.authorToAuthorResponseDto(foundAuthor.get());
        } else {
            throw new NotFoundException("Author with name: " + firstName + " " + lastName + " was not found");
        }

    }

    public Author findAuthorEntityById(Integer id) {

        Optional<Author> foundAuthor = authorsRepository.findById(Long.valueOf(id));

        if (foundAuthor.isPresent()) {
            return foundAuthor.get();
        } else {
            throw new NotFoundException("Author with name: " + id + " was not found");
        }

    }

    private Optional<Author> getSavedAuthor(AddAuthorRequestDto authorDto) {

        Author author = new Author();

        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());

        Optional<Author> savedAuthor = Optional.of(authorsRepository.save(author));

        return savedAuthor;
    }

}
