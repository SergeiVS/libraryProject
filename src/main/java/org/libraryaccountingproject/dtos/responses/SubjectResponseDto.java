package org.libraryaccountingproject.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseDto {

    private Integer subjectId;
    private String  bookSubject;
}
