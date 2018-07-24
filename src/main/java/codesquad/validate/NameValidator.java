package codesquad.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<Name, String> {

    private String nameRegex = "[가-힣]{2,16}|[a-zA-Z]{2,16}";

    @Override
    public void initialize(Name name) {

    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext cxt) {

        return field != null && field.matches(nameRegex);
    }
}
