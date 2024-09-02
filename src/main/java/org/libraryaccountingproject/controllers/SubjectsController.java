package org.libraryaccountingproject.controllers;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.subjectDtos.SubjectResponseDto;
import org.libraryaccountingproject.services.SubjectServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/subjects")
@RequiredArgsConstructor
public class SubjectsController {

   private final SubjectServices subjectServices;

    @GetMapping("/subject")
    public ResponseEntity<SubjectResponseDto> addNewSubject(@RequestParam String subject) {
        return new ResponseEntity<>(subjectServices.addNewSubject(subject), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> getAllSubjects() {
        return new ResponseEntity<>(subjectServices.findAllSubjects(), HttpStatus.FOUND);
    }

}
