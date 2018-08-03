package codesquad.promotion.dto;

import codesquad.promotion.controller.PromotionDto;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class PromotionDtoValidationTest {
    private static Validator validator;

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void correctURL() {
        PromotionDto dto = new PromotionDto("https://blah.kr:8080/static/img/test.jpg");
        Set<ConstraintViolation<PromotionDto>> valid = validator.validate(dto);
        assertThat(valid).hasSize(0);
    }

    @Test
    public void incorrectURL() {
        PromotionDto dto = new PromotionDto("string");
        Set<ConstraintViolation<PromotionDto>> valid = validator.validate(dto);
        assertThat(valid).hasSize(1);
    }

    @Test
    public void URL_is_null() {
        PromotionDto dto = new PromotionDto(null);
        Set<ConstraintViolation<PromotionDto>> valid = validator.validate(dto);
        assertThat(valid).hasSize(1);
    }
}