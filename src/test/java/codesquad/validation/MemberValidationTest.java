package codesquad.validation;

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
        MemberDto memberDto = new MemberDto("email@com", "1234", "pobi", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void emailBlankTest() {
        MemberDto memberDto = new MemberDto("", "1234", "pobi", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("메일을 작성해주세요.");
    }

    @Test
    public void emailInvalidFormatTest() {
        MemberDto memberDto = new MemberDto("email", "1234", "pobi", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void passwordValidTest() {
        MemberDto memberDto = new MemberDto("email@com", "12345", "pobi", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0); // for length constraint checks whether it is at least 4 letters.
    }

    @Test
    public void passwordBlankTest() {
        MemberDto memberDto = new MemberDto("email@com", "", "pobi", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(2); // for length constraint checks whether it is at least 4 letters.
    }

    @Test
    public void passwordShortTest() {
        MemberDto memberDto = new MemberDto("email@com", "123", "pobi", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void passwordTooLongTest() {
        MemberDto memberDto = new MemberDto("email@com", "1234567890123456789012345678901", "pobi", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void nameValidTest() {
        MemberDto memberDto = new MemberDto("email@com", "1234", "pobi", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void nameBlankTest() {
        MemberDto memberDto = new MemberDto("email@com", "1234", "", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void nameTooLongTest() {
        MemberDto memberDto = new MemberDto("email@com", "1234", "pobi1pobi1pobi1pobi1pobi1pobi1pobi1", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void phoneNumberValidTest() {
        MemberDto memberDto = new MemberDto("email@com", "1234", "pobi", "01012341234");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void phoneNumberBlankTest() {
        MemberDto memberDto = new MemberDto("email@com", "1234", "pobi", "");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(2);
    }

    @Test
    public void phoneNumberInvalidFormatTest() {
        MemberDto memberDto = new MemberDto("email@com", "1234", "pobi", "010123a1!3b");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void phoneNumberShortTest() {
        MemberDto memberDto = new MemberDto("email@com", "1234", "pobi", "01012334");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
    @Test
    public void phoneNumberTooLongTest() {
        MemberDto memberDto = new MemberDto("email@com", "1234", "pobi", "010123341234123");
        Set<ConstraintViolation<MemberDto>> constraintViolations = validator.validate(memberDto);
        log.debug("violations: {}", constraintViolations.toString());
        assertThat(constraintViolations.size()).isEqualTo(1);
    }


}
