package org.libraryaccountingproject.services.utils.converters;

import org.libraryaccountingproject.dtos.responses.SubjectResponseDto;
import org.libraryaccountingproject.entities.BookSubject;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
@Component
public class SubjectToDtoConverter {

    public SubjectResponseDto convertSubjectToSubjectDto(BookSubject subject) {
        return new SubjectResponseDto(subject.getId(), subject.getSubject());
    }
}
