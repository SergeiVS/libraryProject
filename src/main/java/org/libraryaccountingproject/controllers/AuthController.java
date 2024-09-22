package org.libraryaccountingproject.controllers;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.libraryaccountingproject.dtos.authDtos.LoginRequest;
import org.libraryaccountingproject.dtos.authDtos.LoginResponse;
import org.libraryaccountingproject.dtos.userDtos.NewUserRequestDto;
import org.libraryaccountingproject.dtos.userDtos.UserDataResponseDto;
import org.libraryaccountingproject.services.UserServices;
import org.libraryaccountingproject.services.authSevice.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserServices userServices;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<UserDataResponseDto> registerNewUser(@Valid @RequestBody NewUserRequestDto dto) throws MessagingException {
        System.out.println("Controller Entered");
        log.info(dto.toString());
        return new ResponseEntity<>(userServices.registerNewReader(dto), HttpStatus.CREATED);
    }

    @PostMapping("/login")

    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest dto) throws MessagingException {

        log.info("Attempting to authenticate user: {}", dto.getLogin());

        return new ResponseEntity<>(new LoginResponse(loginService.authenticate(dto)), HttpStatus.CREATED);
    }

    @PostMapping("/login/basic")
    public ResponseEntity<LoginResponse> loginBasic(@RequestHeader(value = "Authorization") String header) throws MessagingException {
        log.info("Attempting to authenticate user: {}", header.isBlank());

        return new ResponseEntity<>(new LoginResponse(loginService.basicAuthentication(header)), HttpStatus.CREATED);
    }
}
