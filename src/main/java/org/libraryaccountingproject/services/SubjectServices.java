package org.libraryaccountingproject.services;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.responses.SubjectResponseDto;
import org.libraryaccountingproject.entities.BookSubject;
import org.libraryaccountingproject.repositories.BooksSubjectsRepository;
import org.libraryaccountingproject.services.utils.converters.SubjectToDtoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServices {

    private final BooksSubjectsRepository repository;
    private final SubjectToDtoConverter converter;

    public SubjectResponseDto addNewSubject(String subject) {
        BookSubject bookSubject = new BookSubject();
        bookSubject.setSubject(subject);
        BookSubject savedSubject = repository.save(bookSubject);
        return converter.convertSubjectToSubjectDto(savedSubject);
    }

    public BookSubject findSubjectByName(String subject) {
        if (repository.findBySubject(subject).isPresent()) {
            return repository.findBySubject(subject).get();
        } else {
            throw new RuntimeException("Subject " + subject + " not found");
        }
    }

    public List<SubjectResponseDto> findAllSubjects() {

        List<BookSubject> subjects = repository.findAll();

        List<SubjectResponseDto> responseDtos = new ArrayList<>();
        subjects.forEach(subject -> {
            responseDtos.add(converter.convertSubjectToSubjectDto(subject));
        });
        return responseDtos;
    }


}
