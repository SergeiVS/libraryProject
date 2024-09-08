package org.libraryaccountingproject.services.utils.mappers;

import org.libraryaccountingproject.dtos.subjectDtos.SubjectDto;
import org.libraryaccountingproject.entities.BookSubject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMappers {

    SubjectDto toSubjectDto(BookSubject bookSubject);

    BookSubject toBookSubject(SubjectDto subjectDto);


}
