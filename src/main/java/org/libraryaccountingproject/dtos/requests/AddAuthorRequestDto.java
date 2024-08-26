package org.libraryaccountingproject.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor
public class AddAuthorRequestDto {

    @NotBlank(message = "Firstname could not be empty")
    @Size(min = 3, max = 25, message = "Firstname length could be between 3 and 25 characters" )
    private String firstName;
    @NotBlank(message = "Lastname could not be empty")
    @Size(min = 3, max = 25, message = "Lastname length could be between 3 and 25 characters" )
    private String lastName;
}
