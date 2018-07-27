package codesquad.validation;

import codesquad.dto.MemberJoinDto;
import codesquad.dto.MemberJoinDtoTest;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberValidationTest {

    private static Validator validator;

    @Before
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void create_fail_when_no_email() {
        MemberJoinDto testMemberDto = MemberJoinDtoTest.newMemberDto("");
        Set<ConstraintViolation<MemberJoinDto>> constraintViolations = validator.validate(testMemberDto);
        assertThat(contains(constraintViolations, "please input email")).isTrue();
    }

    @Test
    public void create_fail_when_wrong_format_email() {
        MemberJoinDto testMemberDto = MemberJoinDtoTest.newMemberDto("dooho");
        Set<ConstraintViolation<MemberJoinDto>> constraintViolations = validator.validate(testMemberDto);
        assertThat(contains(constraintViolations, "wrong email format")).isTrue();
    }

    @Test
    public void create_fail_when_number_only_password() {
        MemberJoinDto testMemberDto = MemberJoinDtoTest.newMemberDto("test2@woowahan.com", "11111111");
        Set<ConstraintViolation<MemberJoinDto>> constraintViolations = validator.validate(testMemberDto);
        assertThat(contains(constraintViolations, "Invalid Password")).isTrue();
    }

    public boolean contains(Set<ConstraintViolation<MemberJoinDto>> constraintViolations, String message) {
        for (ConstraintViolation<MemberJoinDto> constraintViolation : constraintViolations) {
            if (constraintViolation.getMessage().equals(message)) {
                return true;
            }
        }
        return false;
    }
}
