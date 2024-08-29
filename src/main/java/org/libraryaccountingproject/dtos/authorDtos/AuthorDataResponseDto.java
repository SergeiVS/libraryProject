package org.libraryaccountingproject.dtos.authorDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor
public class AuthorDataResponseDto {

    private Integer id;
    private String firstName;
    private String lastName;
}
