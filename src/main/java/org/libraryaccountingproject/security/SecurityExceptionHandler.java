package org.libraryaccountingproject.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.libraryaccountingproject.dtos.authDtos.StandardResponse;
import org.libraryaccountingproject.entities.User;
import org.libraryaccountingproject.exeptions.InvalidJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

public class SecurityExceptionHandler {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(SecurityExceptionHandler.class.getName());

    public static final AuthenticationEntryPoint ENTRY_POINT = (request, response, authException) -> {
        String message = "User not authorized";

        if (authException instanceof InvalidJwtException) {
            message = authException.getMessage();
        }
        fillResponse(response, HttpStatus.UNAUTHORIZED, message);
    };

    public static final AccessDeniedHandler ACCESS_DENY_HANDLER = (request, response, accessDeniedException) -> {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = (authentication != null && authentication.getName() != null) ? authentication.getName() : "unknown";
        String message = "Access denied for user: " + user;

        fillResponse(response, HttpStatus.FORBIDDEN, message);
    };

    private static void fillResponse(HttpServletResponse response, HttpStatus status, String message) {
        try {
            response.setStatus(status.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            StandardResponse standardResponse = new StandardResponse(message + ":" + status.value());
            String body = mapper.writeValueAsString(standardResponse);

        } catch (Exception e) {

            logger.error("Failed to write response: ", e);
            throw new IllegalStateException("Failed to write response", e);
        }
    }
}
