package org.libraryaccountingproject.controllers;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.libraryaccountingproject.dtos.userDtos.NewUserRequestDto;
import org.libraryaccountingproject.dtos.userDtos.UserDataResponseDto;
import org.libraryaccountingproject.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<UserDataResponseDto> registerNewUser(@RequestBody NewUserRequestDto dto) throws MessagingException {
        System.out.println("Controller Entered");
        return new ResponseEntity<>(userServices.registerNewReader(dto), HttpStatus.CREATED);
    }
}
