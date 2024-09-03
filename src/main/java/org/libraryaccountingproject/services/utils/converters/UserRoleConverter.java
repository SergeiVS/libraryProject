package org.libraryaccountingproject.services.utils.converters;

import org.libraryaccountingproject.dtos.userRoleDtos.UserRoleDto;
import org.libraryaccountingproject.entities.UserRol;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter {

    public UserRoleDto convertUserRoletoDto(UserRol userRol) {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setId(userRol.getId());
        userRoleDto.setRoleName(userRol.getRoleName());
        return userRoleDto;
    }

    public UserRol convertUserRoleDtoToUserRole(UserRoleDto userRoleDto) {
        UserRol userRol = new UserRol();
        userRol.setId(userRoleDto.getId());
        userRol.setRoleName(userRoleDto.getRoleName().toUpperCase());
        return userRol;
    }
}
