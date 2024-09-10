package org.libraryaccountingproject.services.securityServices;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.services.UserServices;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService {

    private final UserServices userServices;

    public UserDetails loadUserByLogin(String login) throws UsernameNotFoundException {
        return userServices.findUserByLogin(login)
                .map(UserToUserDetailsMapper::new)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + login + " not found"));
    }


}
