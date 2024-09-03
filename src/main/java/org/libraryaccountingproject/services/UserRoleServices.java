package org.libraryaccountingproject.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.userRoleDtos.UserRoleDto;
import org.libraryaccountingproject.entities.UserRol;
import org.libraryaccountingproject.repositories.UserRoleRepository;
import org.libraryaccountingproject.services.exeptions.NotFoundException;
import org.libraryaccountingproject.services.utils.converters.UserRoleConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleServices {

    private final UserRoleRepository repository;
    private final UserRoleConverter converter;

    @Transactional
    public UserRoleDto addUserRole(String role) {

        checkRoleName(role.toUpperCase());

        UserRol roleForSave = new UserRol();
        roleForSave.setRoleName(role);

        UserRol savedUserRol = repository.save(roleForSave);

        return converter.convertUserRoletoDto(savedUserRol);

    }

    @Transactional
    public UserRoleDto updateUserRole(UserRoleDto userRoleDto) {

        checkRoleNameDto(userRoleDto);

        UserRol roleForSave = converter.convertUserRoleDtoToUserRole(userRoleDto);

        UserRol savedUserRol = repository.save(roleForSave);

        return converter.convertUserRoletoDto(savedUserRol);
    }

    public List<UserRoleDto> getAllUserRoles() {

        List<UserRol> userRols = repository.findAll();

        if (!userRols.isEmpty()) {

            return getListOfUserRolesDto(userRols);
        } else {
            throw new NotFoundException("No userRols found");
        }
    }

    public UserRoleDto getUserRoleByRoleName(String role) {
        Optional<UserRol> foundRole = repository.findByRoleName(role);
        if (foundRole.isPresent()) {
            return converter.convertUserRoletoDto(foundRole.get());
        } else {
            throw new NotFoundException("No userRole: " + role + " found");
        }
    }
    public Boolean isUserRoleExist(String role) {
       return repository.existsByRoleName(role);
    }

    public UserRol getUserRoleEntityByRoleName(String role) {
        Optional<UserRol> foundRole = repository.findByRoleName(role);
        if (foundRole.isPresent()) {
            return foundRole.get();
        }else {
            throw new NotFoundException("No userRole: " + role + " found");
        }
    }

    public UserRoleDto getUserRoleById(Long id) {
        Optional<UserRol> foundRole = repository.findById(id);
        if (foundRole.isPresent()) {
            return converter.convertUserRoletoDto(foundRole.get());
        }else {
            throw new NotFoundException("No userRole: " + id + " found");
        }
    }


    private List<UserRoleDto> getListOfUserRolesDto(List<UserRol> userRols) {
        return userRols.stream()
                .map(converter::convertUserRoletoDto)
                .toList();
    }

    private void checkRoleName(String role) {
        if (role.isBlank()) {
            throw new IllegalArgumentException("Role cannot be blank");
        }
        if (!repository.findAll().stream().noneMatch(userRol -> userRol.equals(role.toUpperCase()))) {
            throw new IllegalArgumentException("Role already exist");
        }
    }

    private void checkRoleNameDto(UserRoleDto userRoleDto) {
        if (userRoleDto == null) {
            throw new IllegalArgumentException("UserRoleDto cannot be null");
        }

        if (userRoleDto.getRoleName().isBlank() || userRoleDto.getId() == 0) {
            throw new IllegalArgumentException("Role name cannot be blank, role id can not be 0");
        }

        if (repository.findAll().stream()
                .noneMatch(userRol -> userRol.getId() == userRoleDto.getId())) {
            throw new IllegalArgumentException("Role id: " + userRoleDto.getId() + " does not exist");
        }
    }


}
