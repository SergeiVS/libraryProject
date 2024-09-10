package org.libraryaccountingproject.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class AppAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Authorization failed: " + authException.getMessage();

        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(message));
    }
}
