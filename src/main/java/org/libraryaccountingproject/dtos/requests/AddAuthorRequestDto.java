package org.libraryaccountingproject.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor
public class AddAuthorRequestDto {
    private String firstName;
    private String lastName;
}
