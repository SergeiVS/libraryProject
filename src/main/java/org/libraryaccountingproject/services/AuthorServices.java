package org.libraryaccountingproject.services;

import annotations.NameFormatValidation;
import annotations.StringFormatValidation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.authorDtos.AddUpdateAuthorRequestDto;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.libraryaccountingproject.entities.Author;
import org.libraryaccountingproject.repositories.AuthorsRepository;
import org.libraryaccountingproject.services.exeptions.AlreadyExistException;
import org.libraryaccountingproject.services.exeptions.NotCreatedException;
import org.libraryaccountingproject.services.exeptions.NotFoundException;
import org.libraryaccountingproject.services.utils.converters.AuthorDtoToAuthorConverter;
import org.springframework.data.jpa.repository.Modifying;
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
    public AuthorDataResponseDto addAuthor(AddUpdateAuthorRequestDto authorDto) {

        if (authorsRepository.findByFirstNameAndLastName(authorDto.getFirstName(), authorDto.getLastName()).isEmpty()) {

            Author authorForSave = dtoToAuthorConverter.newAuthorRequestDtoToAuthor(authorDto);
            Optional<Author> savedAuthor = Optional.ofNullable(authorsRepository.save(authorForSave));

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

        if (!authors.isEmpty()) {

            return getAuthorDataResponseDtos(authors);

        } else {

            throw new NotFoundException("Authors should not be found");
        }
    }

    //Current method search by part names with String.contains()
    public List<AuthorDataResponseDto> findAuthorByFullname(@StringFormatValidation(groups = NameFormatValidation.class) String firstName,
                                                            @StringFormatValidation(groups = NameFormatValidation.class) String lastName) {
        List<Author> foundAuthors = authorsRepository.findByFirstNameAndLastName(firstName, lastName);

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

    @Modifying(clearAutomatically = true)
    public AuthorDataResponseDto updateAuthorData(@Valid AddUpdateAuthorRequestDto dto) {

        Optional<Author> foundAuthor = authorsRepository.findById(Long.valueOf(dto.getId()));

        if (foundAuthor.isPresent()) {

            Author author = authorsRepository.save(dtoToAuthorConverter.newAuthorRequestDtoToAuthor(dto));

            return dtoToAuthorConverter.authorToAuthorResponseDto(author);
        } else {
            throw new NotFoundException("Author with id: " + dto.getId() + " was not found");
        }
    }

    @Transactional
    public String deleteAuthorById(Integer id) {
        Optional<Author> authorForDelete = authorsRepository.findById(Long.valueOf(id));

        if (authorForDelete.isPresent()) {

            authorsRepository.delete(authorForDelete.get());

            return "Author with id: " + id + " was deleted";

        } else {
            throw new NotFoundException("Author with id: " + id + " was not found");
        }
    }

    public Author findAuthorEntityById(Integer id) {

        Optional<Author> foundAuthor = authorsRepository.findById(Long.valueOf(id));

        if (foundAuthor.isPresent()) {

            return foundAuthor.get();

        } else {
            throw new NotFoundException("Author with id: " + id + " was not found");
        }

    }


    private List<AuthorDataResponseDto> getAuthorDataResponseDtos(List<Author> authors) {

        List<AuthorDataResponseDto> dtos = new ArrayList<>();

        authors.forEach(author -> dtos.add(dtoToAuthorConverter.authorToAuthorResponseDto(author)));
        return dtos;
    }



}
