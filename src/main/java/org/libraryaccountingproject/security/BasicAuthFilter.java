package org.libraryaccountingproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
@Component
@Slf4j
public class BasicAuthFilter extends BasicAuthenticationFilter {

    public BasicAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    private Authentication extractAuthentication(String header) {

        String base64Credentials = header.substring("Basic ".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded);

        final String[] credentialsArray = credentials.split(":", 2);

        if (credentialsArray.length != 2) {
            throw new BadCredentialsException("Failed to parse credentials");
        }
        String username = credentialsArray[0];
        String password = credentialsArray[1];

        log.info("Extracted username {} and password {}", username, password);

        return new UsernamePasswordAuthenticationToken(username, password, null);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        log.info("BasicAuthFilter 48 {}", request.getRequestURI());
        String header = request.getHeader("Authorization");

        log.info("is header exist: {}", (header != null && header.startsWith("Basic ")));

        if (header != null && header.startsWith("Basic ")) {
            try {
                Authentication authentication = getAuthenticationManager().authenticate(extractAuthentication(header));
                log.info("Authentication {}, {}", authentication.getPrincipal(), this.getClass().getSimpleName());


                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Security context set {}", SecurityContextHolder.getContext().getAuthentication());

            } catch (BadCredentialsException e) {
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        log.info("BasicAuthFilter doFilterIntern complete: {}  authenticated {}", request.getRequestURI(), response.getStatus());
        filterChain.doFilter(request, response);
    }
}
