package org.libraryaccountingproject.controllers;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.responses.SubjectResponseDto;
import org.libraryaccountingproject.services.SubjectServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/subjects")
@RequiredArgsConstructor
public class SubjectsController {

   private final SubjectServices subjectServices;

    @GetMapping("/add-subject")
    public ResponseEntity<SubjectResponseDto> addNewSubject(@RequestParam String subject) {
        return new ResponseEntity<>(subjectServices.addNewSubject(subject), HttpStatus.CREATED);
    }

}
