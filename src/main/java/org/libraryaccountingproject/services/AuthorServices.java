package org.libraryaccountingproject.services;

import annotations.NameFormatValidation;
import annotations.StringFormatValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.libraryaccountingproject.dtos.authorDtos.NewAuthorRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorUpdateRequestDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.repositories.AuthorsRepository;
import org.libraryaccountingproject.services.exeptions.NotFoundException;
import org.libraryaccountingproject.services.exeptions.RestException;
import org.libraryaccountingproject.services.utils.mappers.AuthorMappers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServices {

    private final AuthorsRepository authorsRepository;
    private final AuthorMappers authorMappers;

    @Transactional
    public AuthorDataResponseDto addAuthor(NewAuthorRequestDto authorDto) {

        if (!authorsRepository.existsByFirstNameAndLastName(authorDto.getFirstName(), authorDto.getLastName())) {

            Author authorForSave = authorMappers.newAuthorFromNewAuthorRequestDto(authorDto);

            Author savedAuthor = authorsRepository.save(authorForSave);

            return authorMappers.toAuthorDataResponseDto(savedAuthor);
        } else {
            throw new RestException(HttpStatus.CONFLICT, "Author already exists");
        }

    }

    @Modifying(clearAutomatically = true)
    public AuthorDataResponseDto updateAuthorData(AuthorUpdateRequestDto dto) {

        Author author = authorsRepository
                .findById(dto.getId())
                .orElseThrow(() -> new RestException(HttpStatus.CONFLICT, "Author with id: " + dto.getId() + " was not found"));

        authorMappers.updateAuthorFromAuthorUpdateDto(author, dto);

        Author savedAuthor = authorsRepository.save(author);

        return authorMappers.toAuthorDataResponseDto(savedAuthor);
    }


    public AuthorDataResponseDto findAuthorById(Integer id) {

        Author author = authorsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Author should not be found"));

        return authorMappers.toAuthorDataResponseDto(author);
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
        log.info(foundAuthors.toString());
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

    public Set<Author> getAuthorsSetFromAuthorIdsList(List<Integer> authorIds) {

        return authorIds.stream()
                .map(id -> authorsRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Author with id: " + id + " was not found")))
                .collect(Collectors.toSet());
    }

    public boolean authorExistById(Integer id) {
        return authorsRepository.existsById(id);
    }

    // Private service methods


    private List<AuthorDataResponseDto> getAuthorDataResponseDtos(List<Author> authors) {

        List<AuthorDataResponseDto> dtos = new ArrayList<>();

        authors.forEach(author -> dtos.add(authorMappers.toAuthorDataResponseDto(author)));

        return dtos;
    }


}
