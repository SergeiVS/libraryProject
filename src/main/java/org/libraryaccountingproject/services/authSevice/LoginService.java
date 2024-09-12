package org.libraryaccountingproject.services.authSevice;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.authDtos.LoginRequest;
import org.libraryaccountingproject.services.UserServices;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class LoginService {

    private final UserServices userServices;

    public String login(LoginRequest request) {
        return "";
    }

}
