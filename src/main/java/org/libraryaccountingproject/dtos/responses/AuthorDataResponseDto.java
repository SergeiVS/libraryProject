package org.libraryaccountingproject.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor
public class AuthorDataResponseDto {

    private Integer id;
    private String firstName;
    private String lastName;
}
