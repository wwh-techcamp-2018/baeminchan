package codesquad.dto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

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
