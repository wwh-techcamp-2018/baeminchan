package codesquad.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<UserName, String> {

    private static final Logger log = LoggerFactory.getLogger(UserNameValidator.class);
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 16;
    private static final String USER_NAME_REGEX = "[0-9$&+,:;=?@#|’<>.-^*()%!]";

    @Override
    public void initialize(UserName userName) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.length() < MIN_LENGTH || s.length() > MAX_LENGTH)
            return false;
        if (Pattern.compile(USER_NAME_REGEX).matcher(s).find())
            return false;
        return true;
    }
}