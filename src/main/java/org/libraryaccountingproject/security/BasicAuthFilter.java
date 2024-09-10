package org.libraryaccountingproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;


public class BasicAuthFilter extends BasicAuthenticationFilter {


    public BasicAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public Authentication extractAuthentication(String header) {

        String base64Credentials = header.substring("Basic ".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded);

        final String[] credentialsArray = credentials.split(":", 2);

        if (credentialsArray.length != 2) {
            throw new BadCredentialsException("Failed to parse credentials");
        }
        String username = credentialsArray[0];
        String password = credentialsArray[1];

        return new UsernamePasswordAuthenticationToken(username, password, null);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Basic ")) {
            try {
                Authentication auth = getAuthenticationManager().authenticate(extractAuthentication(header));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (BadCredentialsException e) {
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
