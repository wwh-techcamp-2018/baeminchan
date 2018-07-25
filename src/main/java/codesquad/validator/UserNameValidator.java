package codesquad.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UserNameValidator implements ConstraintValidator<UserName, String> {

    private static final String USER_NAME_REGEX = "[가-힣]{2,16}";

    @Override
    public void initialize(UserName userName) {

    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(str).filter(s -> s.matches(USER_NAME_REGEX)).isPresent();
    }

}