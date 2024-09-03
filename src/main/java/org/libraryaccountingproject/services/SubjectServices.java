package org.libraryaccountingproject.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.subjectDtos.SubjectDto;
import org.libraryaccountingproject.entities.BookSubject;
import org.libraryaccountingproject.repositories.BooksSubjectsRepository;
import org.libraryaccountingproject.services.exeptions.NotCreatedException;
import org.libraryaccountingproject.services.exeptions.NotFoundException;
import org.libraryaccountingproject.services.exeptions.RestException;
import org.libraryaccountingproject.services.utils.converters.SubjectToDtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectServices {

    private final BooksSubjectsRepository repository;
    private final SubjectToDtoConverter converter;

    @Transactional
    public SubjectDto addNewSubject(String subject) {

        if (repository.existsBySubject(subject)) {

            BookSubject bookSubject = new BookSubject();
            bookSubject.setSubject(subject);
            BookSubject savedSubject = repository.save(bookSubject);

            return converter.convertSubjectToSubjectDto(savedSubject);
        } else {
            throw new RestException(HttpStatus.CONFLICT, "Subject: " + subject + " already exist");
        }
    }

    @Transactional
    public SubjectDto updateSubject(SubjectDto subjectDto) {
        if (repository.existsById(subjectDto.getSubjectId())) {

            BookSubject bookSubject = repository.save(converter.convertSubjectDtoToBookSubject(subjectDto));
            return converter.convertSubjectToSubjectDto(bookSubject);
        } else {
            throw new RestException(HttpStatus.CONFLICT, "Subject: " + subjectDto.getSubjectId() + " does not exist");
        }
    }

    public SubjectDto findSubjectByName(String subject) {

        Optional<BookSubject> subjectOptional = repository.findBySubject(subject);

        if (subjectOptional.isPresent()) {

            SubjectDto dto = converter.convertSubjectToSubjectDto(subjectOptional.get());

            return dto;

        } else {
            throw new NotFoundException("Subject " + subject + " not found");
        }
    }

    public BookSubject findSubjectObjectByName(String subject) {

        if (repository.existsBySubject(subject)) {

            return repository.findBySubject(subject).get();

        } else {
            throw new NotFoundException("Subject " + subject + " not found");
        }
    }

    public List<SubjectDto> findAllSubjects() {

        List<BookSubject> subjects = repository.findAll();
        if (!subjects.isEmpty()) {

            return subjects.stream()
                    .map(converter::convertSubjectToSubjectDto)
                    .toList();
        } else {
            throw new NotFoundException("No subjects found");
        }
    }

    public SubjectDto findSubjectById(Integer id) {

        if (repository.existsById(id)) {

            return converter.convertSubjectToSubjectDto(repository.findById(id).get());
        } else {

            throw new NotFoundException("Subject " + id + " not found");
        }

    }

    public Boolean checkIsSubjectExist(String subjectName) {
        return repository.existsBySubject(subjectName);
    }


}
