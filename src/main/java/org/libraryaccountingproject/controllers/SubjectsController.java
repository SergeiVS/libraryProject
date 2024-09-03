package org.libraryaccountingproject.controllers;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.subjectDtos.SubjectDto;
import org.libraryaccountingproject.services.SubjectServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subjects")
@RequiredArgsConstructor
public class SubjectsController {

   private final SubjectServices subjectServices;

    @PostMapping("/subject")
    public ResponseEntity<SubjectDto> addNewSubject(@RequestParam String subject) {
        return new ResponseEntity<>(subjectServices.addNewSubject(subject), HttpStatus.CREATED);
    }

    @PutMapping("/subject")
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto dto)
    {
        return new ResponseEntity<>(subjectServices.updateSubject(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        return new ResponseEntity<>(subjectServices.findAllSubjects(), HttpStatus.FOUND);
    }

    @GetMapping("/{subject}")
    public ResponseEntity<SubjectDto> getSubject(@PathVariable String subject) {
        return new ResponseEntity<>(subjectServices.findSubjectByName(subject), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable String id) {
        return new ResponseEntity<>(subjectServices.findSubjectById(id), HttpStatus.FOUND);
    }

}
