package annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraints.ISBN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ISBNValidation implements ConstraintValidator<ISBN, String> {
    @Override
    public void initialize(ISBN constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^[0-9]{13}$");
        Matcher matcher = pattern.matcher(isbn);
        if (!matcher.matches()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("ISBN is in wrong format. Expected number format, length 13 digits").addConstraintViolation();
        }
        return matcher.matches();
    }
}
