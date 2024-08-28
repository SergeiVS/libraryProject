package annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class NameFormatValidation implements ConstraintValidator<StringFormatValidation, String> {


    @Override
    public void initialize(StringFormatValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        List<String> errors = new ArrayList<>();

        if (name == null) {
            errors.add("Name must not be empty");
            return false;
        }


        if (!name.matches("[A-Za-z0-9\\s]{3,25}")) {
            errors.add("Name must contain Letters, Numbers and spaces, it can not be shorter as 3 and " +
                    "longer as 35 symbols");
        }

        if (!errors.isEmpty()) {
            for (String error : errors) {
                addConstrainViolation(context, error);
            }
        }
        return false;
    }

    private void addConstrainViolation(ConstraintValidatorContext context, String message) {
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
