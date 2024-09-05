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

        if (!repository.existsBySubject(subject)) {
            BookSubject bookSubject = BookSubject.builder()
                    .subject(subject)
                    .build();
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

        BookSubject subjectOptional = repository.findBySubject(subject)
                .orElseThrow(() -> new NotFoundException(subject));

        return converter.convertSubjectToSubjectDto(subjectOptional);

    }

    public BookSubject findSubjectObjectByName(String subject) {

        return repository.findBySubject(subject)
                .orElseThrow(() -> new NotFoundException("Subject: " + subject + " does not exist"));

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

        return converter.convertSubjectToSubjectDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject: " + id + " does not exist")));
    }

    public Boolean checkIsSubjectExist(String subjectName) {
        return repository.existsBySubject(subjectName);
    }


}
