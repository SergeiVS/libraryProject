package org.libraryaccountingproject.controllers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ValidationsExceptionsHandlers {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException exception) {
        StringBuilder response = new StringBuilder();

        exception.getConstraintViolations().forEach(constraintViolation -> {
            response.append(constraintViolation.getMessage()).append("\n");
        });

        return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)

    public ResponseEntity<String> handlerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        StringBuilder response = new StringBuilder();

        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            String field = ((FieldError) objectError).getField();
            String error = objectError.getDefaultMessage();

            response.append(error + ": " + field).append("\n");
        });
        return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handlerAccessDeniedException(AccessDeniedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}
