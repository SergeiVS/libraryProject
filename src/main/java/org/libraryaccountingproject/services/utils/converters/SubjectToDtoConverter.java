package org.libraryaccountingproject.services.utils.converters;

import org.libraryaccountingproject.dtos.subjectDtos.SubjectDto;
import org.libraryaccountingproject.entities.BookSubject;
import org.springframework.stereotype.Component;

@Component
public class SubjectToDtoConverter {

    public SubjectDto convertSubjectToSubjectDto(BookSubject subject) {
        return new SubjectDto(subject.getId(), subject.getSubject());
    }

    public BookSubject convertSubjectDtoToBookSubject(SubjectDto dto) {
        return new BookSubject(dto.getSubjectId(), dto.getBookSubject());
    }
}
