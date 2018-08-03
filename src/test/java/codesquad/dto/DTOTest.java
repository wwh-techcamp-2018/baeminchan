package codesquad.dto;

import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@ActiveProfiles("develop")
public class DTOTest<T> {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public boolean isValid(T dto) {
        Set<ConstraintViolation<T>> violations = validate(dto);
        return violations.size() == 0;
    }

    public Set<ConstraintViolation<T>> validate(T dto) {
        return validator.validate(dto);
    }
}
