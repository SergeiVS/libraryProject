package org.libraryaccountingproject.controllers;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.controllers.api.SubjectsAPI;
import org.libraryaccountingproject.dtos.subjectDtos.SubjectDto;
import org.libraryaccountingproject.services.SubjectServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/users/subjects")
@RequiredArgsConstructor
public class SubjectsController implements SubjectsAPI {

   private final SubjectServices subjectServices;

    @PostMapping
    public ResponseEntity<SubjectDto> addNewSubject(@RequestParam String subject) {
        return new ResponseEntity<>(subjectServices.addNewSubject(subject), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto dto){
        return new ResponseEntity<>(subjectServices.updateSubject(dto), HttpStatus.OK);
    }


    @GetMapping("/subject")
    public ResponseEntity<SubjectDto> getSubject(@RequestParam String subject) {
        return new ResponseEntity<>(subjectServices.findSubjectByName(subject), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Integer id) {
        return new ResponseEntity<>(subjectServices.findSubjectById(id), HttpStatus.FOUND);
    }

}
