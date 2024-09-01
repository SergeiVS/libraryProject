package org.libraryaccountingproject.services.utils.converters;

import org.libraryaccountingproject.dtos.userRoleDtos.UserRoleDto;
import org.libraryaccountingproject.entities.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter {

    public UserRoleDto convertUserRoletoDto(UserRole userRole) {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setId(userRole.getId());
        userRoleDto.setRoleName(userRole.getRoleName());
        return userRoleDto;
    }

    public UserRole convertUserRoleDtoToUserRole(UserRoleDto userRoleDto) {
        UserRole userRole = new UserRole();
        userRole.setId(userRoleDto.getId());
        userRole.setRoleName(userRoleDto.getRoleName().toUpperCase());
        return userRole;
    }
}
