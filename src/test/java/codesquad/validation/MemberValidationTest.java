package codesquad.validation;

import codesquad.dto.LoginDto;
import codesquad.dto.MemberDto;
import codesquad.support.MemberDtoBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberValidationTest {
    private static Validator validator;
    public static final Logger log = LoggerFactory.getLogger(MemberValidationTest.class);

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    public void emailValidTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void emailBlankTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().email("").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("메일을 작성해주세요.");
    }

    @Test
    public void emailInvalidFormatTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().email("hi").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void passwordValidTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0); // for length constraint checks whether it is at least 4 letters.
    }

    @Test
    public void passwordBlankTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().password("").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(2); // for length constraint checks whether it is at least 4 letters.
    }

    @Test
    public void passwordShortTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().password("123").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void passwordTooLongTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().password("1234567890123456789012345678901").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void nameValidTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void usernameBlankTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().username("").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void usernameTooLongTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().username("1234512345123451234512345123451234512345").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void phoneNumberValidTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void phoneNumberBlankTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().phoneNumber("").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(2);
    }

    @Test
    public void phoneNumberInvalidFormatTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().phoneNumber("010123a1!3b").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void phoneNumberShortTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().phoneNumber("01012334").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
    @Test
    public void phoneNumberTooLongTest() {
        MemberDto memberDto = MemberDtoBuilder.builder().phoneNumber("010123341234123").build();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void loginEmailValidTest() {
        LoginDto loginDto = new LoginDto("valid@email", "123123");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void loginEmailBlankTest() {
        LoginDto loginDto = new LoginDto("", "123123");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void loginEmailInvalidFormatTest() {
        LoginDto loginDto = new LoginDto("hi", "123123");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void loginPasswordValidTest() {
        LoginDto loginDto = new LoginDto("hi@com", "123123");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void loginPasswordBlankTest() {
        LoginDto loginDto = new LoginDto("hi@com", "");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(2);
    }

    @Test
    public void loginPasswordShortTest() {
        LoginDto loginDto = new LoginDto("hi@com", "123");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void loginPasswordTooLongTest() {
        LoginDto loginDto = new LoginDto("hi@com", "1234567890123456789012345678901");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
}
