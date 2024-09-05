package org.libraryaccountingproject.services;

import annotations.NameFormatValidation;
import annotations.StringFormatValidation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.authorDtos.NewAuthorRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorUpdateRequestDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.repositories.AuthorsRepository;
import org.libraryaccountingproject.services.exeptions.AlreadyExistException;
import org.libraryaccountingproject.services.exeptions.NotFoundException;
import org.libraryaccountingproject.services.exeptions.RestException;
import org.libraryaccountingproject.services.utils.converters.AuthorDtoToAuthorConverter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServices {

    private final AuthorsRepository authorsRepository;
    private final AuthorDtoToAuthorConverter dtoToAuthorConverter;

    @Transactional

    public AuthorDataResponseDto addAuthor(NewAuthorRequestDto authorDto) {

        if (!authorsRepository.existsByFirstNameAndLastName(authorDto.getFirstName(), authorDto.getLastName())) {

            Author authorForSave = dtoToAuthorConverter.newAuthorRequestDtoToAuthor(authorDto);

            Author savedAuthor = authorsRepository.save(authorForSave);

            return dtoToAuthorConverter.authorToAuthorResponseDto(savedAuthor);
        } else {

            throw new RestException(HttpStatus.CONFLICT, "Author already exists");
        }

    }

    @Modifying(clearAutomatically = true)
    public AuthorDataResponseDto updateAuthorData(AuthorUpdateRequestDto dto) {

        Author author = authorsRepository
                .findById(dto.getId())
                .orElseThrow(() -> new RestException(HttpStatus.CONFLICT, "Author with id: " + dto.getId() + " was not found"));

        if (!dto.getFirstName().isBlank()) {
            author.setFirstName(dto.getFirstName());
        }

        if (!dto.getLastName().isBlank()) {
            author.setLastName(dto.getLastName());
        }
        return dtoToAuthorConverter.authorToAuthorResponseDto(author);
    }


    public AuthorDataResponseDto findAuthorById(Integer id) {

        Author author = authorsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Author should not be found"));

        return dtoToAuthorConverter.authorToAuthorResponseDto(author);
    }


    public List<AuthorDataResponseDto> findAllAuthors() {

        List<Author> authors = authorsRepository.findAll();

        if (!authors.isEmpty()) {

            return getAuthorDataResponseDtos(authors);
        } else {

            throw new NotFoundException("Authors should not be found");
        }
    }

    //Current method search by part names with String.contains()
    public List<AuthorDataResponseDto> findAuthorByFullname(@StringFormatValidation(groups = NameFormatValidation.class) String firstName,
                                                            @StringFormatValidation(groups = NameFormatValidation.class) String lastName) {

        List<Author> foundAuthors = authorsRepository.findByFirstNameContainingAndLastNameContaining(firstName, lastName);

        if (!foundAuthors.isEmpty()) {

            return getAuthorDataResponseDtos(foundAuthors);
        } else {
            throw new NotFoundException("Author with name: " + firstName + " " + lastName + " was not found");
        }

    }

    public List<AuthorDataResponseDto> findAuthorsByLastName(@StringFormatValidation(groups = NameFormatValidation.class) String lastName) {

        List<Author> authors = authorsRepository.findByLastName(lastName);

        if (!authors.isEmpty()) {

            return getAuthorDataResponseDtos(authors);

        } else {

            throw new NotFoundException("Authors should not be found");
        }
    }

    @Transactional
    public String deleteAuthorById(Integer id) {

        if (authorsRepository.existsById(id)) {

            authorsRepository.deleteById(id);

            return "Author with id: " + id + " was deleted";

        } else {
            throw new NotFoundException("Author with id: " + id + " was not found");
        }
    }

    public Author findAuthorEntityById(Integer id) {

        Author foundAuthor = authorsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Author with id: " + id + " was not found"));

        return foundAuthor;
    }

    // Private service methods


    private List<AuthorDataResponseDto> getAuthorDataResponseDtos(List<Author> authors) {

        List<AuthorDataResponseDto> dtos = new ArrayList<>();

        authors.forEach(author -> dtos.add(dtoToAuthorConverter.authorToAuthorResponseDto(author)));
        return dtos;
    }


}
