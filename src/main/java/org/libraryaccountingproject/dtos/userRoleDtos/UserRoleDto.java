package org.libraryaccountingproject.dtos.userRoleDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserRoleDto {
    @NotNull
    private int id;
    @NotBlank
    private String roleName;
}
