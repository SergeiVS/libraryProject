package org.libraryaccountingproject.services.utils.converters;

import org.libraryaccountingproject.dtos.subjectDtos.SubjectResponseDto;
import org.libraryaccountingproject.entities.BookSubject;
import org.springframework.stereotype.Component;

@Component
public class SubjectToDtoConverter {

    public SubjectResponseDto convertSubjectToSubjectDto(BookSubject subject) {
        return new SubjectResponseDto(subject.getId(), subject.getSubject());
    }
}
