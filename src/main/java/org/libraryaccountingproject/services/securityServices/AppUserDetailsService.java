package org.libraryaccountingproject.services.securityServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.libraryaccountingproject.services.UserServices;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserDetailsService implements UserDetailsService {

    private final UserServices userServices;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.info("Login: {} {}", login, this.getClass().getSimpleName());

        return userServices.findUserByLogin(login)
                .map(UserToUserDetailsMapper::new)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + login + " not found"));
    }
}
