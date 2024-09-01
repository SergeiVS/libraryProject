package org.libraryaccountingproject.services.utils.converters;

import org.libraryaccountingproject.dtos.userDtos.NewUserRequestDto;
import org.libraryaccountingproject.dtos.userDtos.UpdateUserRequestDto;
import org.libraryaccountingproject.dtos.userDtos.UserDataResponseDto;
import org.libraryaccountingproject.entities.User;
import org.libraryaccountingproject.services.UserRoleServices;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoConverter {

    public User newUserRequestDtotoUser(NewUserRequestDto dto, UserRoleServices roleServices) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUserLogin(dto.getUserLogin());
        user.setPassword(dto.getPassword());
        user.setUserRole(roleServices.getUserRoleEntityByRoleName(dto.getUserRole()));
        return user;
    }

    public User updateUserDataResponseDtotoUser(UpdateUserRequestDto dto, UserRoleServices roleServices) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUserEmail(dto.getUserEmail());
        user.setUserRole(roleServices.getUserRoleEntityByRoleName(dto.getUserRole()));
        return user;
    }

    public UserDataResponseDto userToUserDataResponseDto(User user) {
        UserDataResponseDto dto = new UserDataResponseDto();

        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUserEmail(user.getUserLogin());
        dto.setUserRole(user.getUserRole().getRoleName());

        return dto;

    }
}
