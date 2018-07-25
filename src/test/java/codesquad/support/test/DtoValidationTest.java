package codesquad.support.test;

import org.junit.BeforeClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class DtoValidationTest<T> {
    private static Validator validator;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void validate(T dto, int expectedViolationSize) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        assertThat(violations.size()).isEqualTo(expectedViolationSize);
    }
}
