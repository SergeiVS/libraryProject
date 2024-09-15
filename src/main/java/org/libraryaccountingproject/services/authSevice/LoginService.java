package org.libraryaccountingproject.services.authSevice;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.authDtos.LoginRequest;
import org.libraryaccountingproject.dtos.authDtos.LoginResponse;
import org.libraryaccountingproject.services.UserServices;
import org.libraryaccountingproject.services.securityServices.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class LoginService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    private final UserServices userServices;

    public ResponseEntity<LoginResponse> authenticate(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.getJwtToken(authentication.getName());

        return ResponseEntity.ok(new LoginResponse(jwt));
    }

}
