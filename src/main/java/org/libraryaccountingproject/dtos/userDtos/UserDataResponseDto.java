package org.libraryaccountingproject.dtos.userDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponseDto {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userRole;
}
