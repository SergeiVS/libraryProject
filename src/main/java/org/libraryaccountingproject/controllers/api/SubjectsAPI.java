package org.libraryaccountingproject.controllers.api;

import org.libraryaccountingproject.dtos.subjectDtos.SubjectDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/subjects")
public interface SubjectsAPI {

    @PostMapping
    ResponseEntity<SubjectDto> addNewSubject(@RequestParam String subject);

    @PutMapping
    ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto dto);

    @GetMapping("/subject")
    ResponseEntity<SubjectDto> getSubject(@RequestParam String subject);

    @GetMapping("/{id}")
    ResponseEntity<SubjectDto> getSubjectById(@PathVariable Integer id);

}
