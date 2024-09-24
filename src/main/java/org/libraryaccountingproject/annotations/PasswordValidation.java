package org.libraryaccountingproject.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidation implements ConstraintValidator<StringFormatValidation, String> {
    @Override
    public void initialize(StringFormatValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        List<String> errors = new ArrayList<>();

        if (password == null || password.isEmpty()) {
            addConstraintViolation(context, "Password cannot be null");
            return false;
        }
        if (password.length() < 8 || password.length() > 25) {
            errors.add("Password must be at least 8 characters");
        }

        if (!password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!_/@$%#&])+[A-Z\\d!_/@$%#&]{8,25} $")) {
            errors.add("Password must contain at least 1 Uppercase letter, 1 digit, and 1 special character");
        }

        if (!errors.isEmpty()) {
            errors.forEach(error -> addConstraintViolation(context, error));
            return false;
        }



        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
