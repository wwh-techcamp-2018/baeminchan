package codesquad.validation;

import codesquad.dto.LoginDto;
import codesquad.dto.MemberDto;
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
        MemberDto memberDto = MemberDto.defaultMemberDto();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void emailBlankTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setEmail("");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("메일을 작성해주세요.");
    }

    @Test
    public void setEmailInvalidFormatTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setEmail("hi");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void passwordValidTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0); // for length constraint checks whether it is at least 4 letters.
    }

    @Test
    public void passwordBlankTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setPassword("");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(2); // for length constraint checks whether it is at least 4 letters.
    }

    @Test
    public void passwordShortTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setPassword("123");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void passwordTooLongTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setPassword("1234567890123456789012345678901");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void nameValidTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void usernameBlankTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setUsername("");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void usernameTooLongTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setUsername("1234512345123451234512345123451234512345");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void phoneNumberValidTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto();
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void phoneNumberBlankTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setPhoneNumber("");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(2);
    }

    @Test
    public void phoneNumberInvalidFormatTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setPhoneNumber("010123a1!3b");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void phoneNumberShortTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setPhoneNumber("01012334");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
    @Test
    public void phoneNumberTooLongTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto().setPhoneNumber("010123341234123");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void loginEmailValidTest() {
        LoginDto loginDto = LoginDto.defaultLoginDto();
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void loginEmailBlankTest() {
        LoginDto loginDto = LoginDto.defaultLoginDto().setEmail("");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void loginEmailInvalidFormatTest() {
        LoginDto loginDto = LoginDto.defaultLoginDto().setEmail("email");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void loginPasswordValidTest() {
        LoginDto loginDto = LoginDto.defaultLoginDto();
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void loginPasswordBlankTest() {
        LoginDto loginDto = LoginDto.defaultLoginDto().setPassword("");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(2);
    }

    @Test
    public void loginPasswordShortTest() {
        LoginDto loginDto = LoginDto.defaultLoginDto().setPassword("123");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void loginPasswordTooLongTest() {
        LoginDto loginDto = LoginDto.defaultLoginDto().setPassword("1234567890123456789012345678901");
        Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
}
