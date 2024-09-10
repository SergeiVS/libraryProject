package org.libraryaccountingproject.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.subjectDtos.SubjectDto;
import org.libraryaccountingproject.entities.BookSubject;
import org.libraryaccountingproject.repositories.BooksSubjectsRepository;
import org.libraryaccountingproject.exeptions.NotFoundException;
import org.libraryaccountingproject.exeptions.RestException;
import org.libraryaccountingproject.services.utils.mappers.SubjectMappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServices {

    private final BooksSubjectsRepository repository;
    private final SubjectMappers mappers;

    @Transactional
    public SubjectDto addNewSubject(String subject) {

        if (!repository.existsBySubject(subject)) {

            BookSubject bookSubject = getBookSubjectFromString(subject);

            BookSubject savedSubject = repository.save(bookSubject);

            return mappers.toSubjectDto(savedSubject);
        } else {
            throw new RestException(HttpStatus.CONFLICT, "Subject: " + subject + " already exist");
        }
    }


    @Transactional
    public SubjectDto updateSubject(SubjectDto subjectDto) {

        if (repository.existsById(subjectDto.getId())) {
            BookSubject bookSubject = repository.save(mappers.toBookSubject(subjectDto));
            return mappers.toSubjectDto(bookSubject);
        } else {
            throw new RestException(HttpStatus.CONFLICT, "Subject: " + subjectDto.getId() + " does not exist");
        }
    }

    public SubjectDto findSubjectByName(String subject) {

        BookSubject subjectFound = repository.findBySubject(subject)
                .orElseThrow(() -> new NotFoundException("Subject: " + subject + " not found"));

        return mappers.toSubjectDto(subjectFound);

    }

    public BookSubject findSubjectObjectByName(String subject) {

        return repository.findBySubject(subject)
                .orElseThrow(() -> new NotFoundException("Subject: " + subject + " does not exist"));

    }

    public List<SubjectDto> findAllSubjects() {

        List<BookSubject> subjects = repository.findAll();

        return getSubjectDtos(subjects);
    }


    public SubjectDto findSubjectById(Integer id) {

        BookSubject subject = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject: " + id + " does not exist"));

        return mappers.toSubjectDto(subject);
    }

    public Boolean checkIsSubjectExist(String subjectName) {
        return repository.existsBySubject(subjectName);
    }

    private List<SubjectDto> getSubjectDtos(List<BookSubject> subjects) {
        if (!subjects.isEmpty()) {
            return subjects.stream()
                    .map(mappers::toSubjectDto)
                    .toList();
        } else {
            throw new NotFoundException("No subjects found");
        }
    }

    private static BookSubject getBookSubjectFromString(String subject) {
        return BookSubject.builder()
                .subject(subject)
                .build();
    }
}
