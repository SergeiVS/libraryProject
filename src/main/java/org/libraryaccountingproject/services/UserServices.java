package org.libraryaccountingproject.services;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.userDtos.NewUserRequestDto;
import org.libraryaccountingproject.dtos.userDtos.UserDataResponseDto;
import org.libraryaccountingproject.entities.User;
import org.libraryaccountingproject.entities.UserRole;
import org.libraryaccountingproject.repositories.UserRepository;
import org.libraryaccountingproject.services.exeptions.AlreadyExistException;
import org.libraryaccountingproject.services.utils.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;
    private final ConfirmationMessageServices confirmationService;
    private final UserMapper userMapper;

    public UserDataResponseDto registerNewReader(NewUserRequestDto requestDto) {

        userExistByLogin(requestDto.getUserLogin());
        userExistByEmail(requestDto.getUserEmail());

        User userForSave = buildNewReader(requestDto);
        User savedUser = userRepository.save(userForSave);
        String confirmation = confirmationService.createNewMessage(savedUser);

        sendConfirmationEmail(confirmation);

        return userMapper.toUserDataResponseDto(savedUser);
    }

    public UserDataResponseDto confirmUser(String confirmationMessage) {

        User user = confirmationService.confirm(confirmationMessage);

        user.setUserState(User.UserState.valueOf("CONFIRMED"));

        return userMapper.toUserDataResponseDto(userRepository.save(user));
    }


//Private service methods

    private void sendConfirmationEmail(String confirmationMessage) {

    }

    private User buildNewReader(NewUserRequestDto requestDto) {


        User userForSave = userMapper.toUser(requestDto);
        userForSave.setUserRole(UserRole.valueOf("READER"));
        userForSave.setUserState(User.UserState.valueOf("REGISTERED"));
        return userForSave;
    }

    private void userExistByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistException("User with email " + email + " already exists");
        }
    }

    private void userExistByLogin(String login) {
        if (userRepository.existsByUserLogin(login)) {
            throw new AlreadyExistException("User with login " + login + " already exists");
        }
    }

}
