package org.libraryaccountingproject.services;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.responses.SubjectResponseDto;
import org.libraryaccountingproject.entities.BookSubject;
import org.libraryaccountingproject.repositories.BooksSubjectsRepository;
import org.libraryaccountingproject.services.utils.converters.SubjectToDtoConverter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServices {

   private final BooksSubjectsRepository repository;
   private final SubjectToDtoConverter converter;

   public SubjectResponseDto addNewSubject(String subject) {
       BookSubject bookSubject = new BookSubject();
       bookSubject.setSubject(subject);
       BookSubject savedSubject= repository.save(bookSubject);
       return converter.convertSubjectToSubjectDto(savedSubject);
   }
}
