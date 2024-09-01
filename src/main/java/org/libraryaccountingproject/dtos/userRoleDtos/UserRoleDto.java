package org.libraryaccountingproject.dtos.userRoleDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    @NotNull
    private int id;
    @NotBlank
    private String roleName;
}
