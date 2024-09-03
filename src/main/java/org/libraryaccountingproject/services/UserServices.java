package org.libraryaccountingproject.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.userDtos.NewUserRequestDto;
import org.libraryaccountingproject.dtos.userDtos.UpdateUserRequestDto;
import org.libraryaccountingproject.dtos.userDtos.UserDataResponseDto;
import org.libraryaccountingproject.entities.User;
import org.libraryaccountingproject.repositories.UserRepository;
import org.libraryaccountingproject.services.exeptions.NotFoundException;
import org.libraryaccountingproject.services.utils.converters.UserToDtoConverter;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository repository;
    private final UserToDtoConverter converter;
    private final UserRoleServices userRoleServices;

    @Transactional
    public UserDataResponseDto addNewUser(NewUserRequestDto dto) {
        if (dto != null) {

            if (checkUserEmailForUnique(dto.getUserEmail()) && checkUserLoginForUnique(dto.getUserLogin())
                    && checkRoleIsPresent(dto.getUserRole())) {

                User newUser = converter.newUserRequestDtotoUser(dto, userRoleServices);

                User foundUser = repository.save(newUser);

                return converter.userToUserDataResponseDto(foundUser);

            } else {
                throw new IllegalArgumentException("User data validation error");
            }
        } else {
            throw new IllegalArgumentException("Incoming data is empty");
        }
    }

    @Transactional
    public UserDataResponseDto updateUserData(UpdateUserRequestDto dto) {
        if (dto != null) {

            if (checkUserEmailForUnique(dto.getUserEmail()) && checkRoleIsPresent(dto.getUserRole())) {

                User userForUpdate = converter.updateUserDataResponseDtotoUser(dto, userRoleServices);

                User userOptional = repository.save(userForUpdate);

                return converter.userToUserDataResponseDto(userOptional);
            } else {
                throw new IllegalArgumentException("User data validation error");
            }
        } else {
            throw new IllegalArgumentException("Incoming data is empty");
        }

    }

    public List<UserDataResponseDto> getAllUsers() {
        List<User> users = repository.findAll();
        if (!users.isEmpty()) {
            return getListResponseUserDto(users);
        } else {
            throw new NotFoundException("Users not found");
        }
    }

    public UserDataResponseDto getUserById(Integer id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            return converter.userToUserDataResponseDto(userOptional.get());
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public UserDataResponseDto getUserByLogin(String login) {
        Optional<User> optionalUser = repository.findByUserLogin(login);
        if (optionalUser.isPresent()) {
            return converter.userToUserDataResponseDto(optionalUser.get());
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public UserDataResponseDto getUserByEmail(String email) {
        Optional<User> userOptional = repository.findByEmail(email);
        if (userOptional.isPresent()) {
            return converter.userToUserDataResponseDto(userOptional.get());
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public Boolean validateLogin(String login, String password) throws AccessDeniedException {
        Optional<User> userOptional = repository.findByUserLogin(login);
        if (userOptional.isPresent()) {
            if (userOptional.get().getPassword().equals(password)) {
                return true;
            } else {
                throw new AccessDeniedException("Login data is not valid");
            }
        } else {
            throw new AccessDeniedException("Login data is not valid");
        }
    }

    public List<UserDataResponseDto> findByLastNameAndFirstName(String lastName, String firstName) {
        List<User> users = repository.findByFirstNameContainsAndLastNameContains(lastName, firstName);
        if(!users.isEmpty()) {
            return getListResponseUserDto(users);
        }else {
            throw new NotFoundException("Users not found");
        }
    }


    //Private Methods

    private List<UserDataResponseDto> getListResponseUserDto(List<User> users) {
        return users.stream()
                .map(converter::userToUserDataResponseDto)
                .toList();
    }

    private Boolean checkRoleIsPresent(String role) {
        if (userRoleServices.isUserRoleExist(role)) {
            return true;
        } else {
            throw new IllegalArgumentException("No UserRol: " + role + " present");
        }
    }

    private Boolean checkUserEmailForUnique(String email) {

        if (repository.findAll().stream().noneMatch(user -> user.getUserEmail().equals(email))) {
            return true;
        } else {
            throw new IllegalArgumentException("Email " + email + " already exists");
        }
    }

    private Boolean checkUserLoginForUnique(String login) {

        if (repository.findAll().stream().noneMatch(user -> user.getUserLogin().equals(login))) {
            return true;
        } else {
            throw new IllegalArgumentException("Login: " + login + " already exists");
        }
    }
}
