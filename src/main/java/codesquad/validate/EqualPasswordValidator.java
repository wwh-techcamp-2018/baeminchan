package codesquad.validate;

import codesquad.user.dto.SignupDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordValidator implements ConstraintValidator<EqualPasswords, SignupDto> {
    @Override
    public void initialize(EqualPasswords constraintAnnotation) {

    }

    @Override
    public boolean isValid(SignupDto dto, ConstraintValidatorContext context) {

        return dto.validatePassword();
    }
}
