package codesquad.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final String PHONE_REGEX = "(01[016789])-(\\d{3,4})-(\\d{4})";

    @Override
    public void initialize(Phone phone) {

    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(str).filter(s -> s.matches(PHONE_REGEX)).isPresent();
    }

}