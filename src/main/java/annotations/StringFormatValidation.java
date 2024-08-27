package annotations;

import jakarta.validation.Payload;

public @interface StringFormatValidation {

    String message() default "Wrong  Format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
