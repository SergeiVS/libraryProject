package org.libraryaccountingproject.services.utils.mappers;

import org.libraryaccountingproject.dtos.userDtos.NewUserRequestDto;
import org.libraryaccountingproject.dtos.userDtos.UserDataResponseDto;
import org.libraryaccountingproject.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(NewUserRequestDto requestDto);
    UserDataResponseDto toUserDataResponseDto(User user);
}
