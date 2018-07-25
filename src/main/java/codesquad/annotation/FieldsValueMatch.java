package codesquad.annotation;

import codesquad.dto.UserDto;
import codesquad.validate.FieldsValueMatchValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();

    Class<UserDto>[] groups() default {};

    Class<UserDto>[] payload() default {};
}

