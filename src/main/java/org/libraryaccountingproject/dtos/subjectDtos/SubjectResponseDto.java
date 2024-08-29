package org.libraryaccountingproject.dtos.subjectDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseDto {

    private Integer subjectId;
    private String  bookSubject;
}
