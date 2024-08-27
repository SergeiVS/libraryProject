package annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

public class SubjectValidation implements ConstraintValidator<StringFormatValidation, String> {


    @Override
    public void initialize(StringFormatValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String subject, ConstraintValidatorContext context) {
        List<String> errors = new ArrayList<>();

        if (subject == null) {
            errors.add("Subject must not be null");
        }

        if (!subject.matches("^[A-Za-z\\d\\s]{3,35}$")) {
            errors.add("Subject must contain Letters, Numbers and spaces, it can not be shorter as 3 and " +
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
