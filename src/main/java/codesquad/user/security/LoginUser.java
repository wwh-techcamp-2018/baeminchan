package codesquad.user.security;

import codesquad.user.domain.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    boolean required() default true;
    Role role() default Role.DEFAULT;
}