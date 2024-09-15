package org.libraryaccountingproject.services.authSevice;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.libraryaccountingproject.dtos.authDtos.LoginRequest;
import org.libraryaccountingproject.services.UserServices;
import org.libraryaccountingproject.services.securityServices.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public String authenticate(LoginRequest loginRequest) {

        return authenticateUser(loginRequest.getLogin(), loginRequest.getPassword());
    }

    public String basicAuthentication(String authHeader) {

        log.info("Started {}", this.getClass().getSimpleName());

        if (authHeader != null && authHeader.startsWith("Basic ")) {

            String[] credentails = decodeAuthHeader(authHeader);

            return authenticateUser(credentails[0], credentails[1]);
        } else {
            throw new IllegalArgumentException("Authentication data is  not valid");
        }
    }

    private String[] decodeAuthHeader(String authHeader) {

        String cred64 = authHeader.substring("Basic ".length()).trim();
        byte[] credBytes = Base64.getDecoder().decode(cred64);
        String credString = new String(credBytes);
        String[] credValues = credString.split(":");

        if (credValues.length != 2) {
            throw new IllegalArgumentException("Invalid Login Token");
        }
        return credValues;
    }

    private String authenticateUser(String login, String password) {
        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return jwtProvider.getJwtToken(authentication.getName());

        } catch (Exception e) {
            log.info("Login failed {}", login);
            throw e;
        }
    }
}
