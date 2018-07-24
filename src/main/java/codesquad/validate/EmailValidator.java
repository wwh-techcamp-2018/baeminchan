package codesquad.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String>{

    private String emailRegex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

    @Override
    public void initialize(Email email) {

    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext cxt) {
        return field != null && field.matches(emailRegex);
    }
}
