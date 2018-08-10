package codesquad.validation;

import codesquad.dto.SignupDto;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.HashSet;

public class SignupDtoValidationTest {
    private SignupDto.SignupDtoBuilder builder;

    @Before
    public void setup() {
        builder = SignupDto.builder()
                .email("aa@aa.aa")
                .password("password")
                .passwordConfirm("password")
                .name("aabbcc")
                .phoneNumber("010-0101-0011");
    }

    @Test
    public void emailPatternNotMatch() throws Exception {
        ValidationUtil.assertValidate(
                builder.email("aaaa").build(),
                new HashSet<>(Arrays.asList(Pattern.class))
        );
    }

    @Test
    public void phoneNumberPatternNotMatch() throws Exception {
        ValidationUtil.assertValidate(
                builder.phoneNumber("001a-a-").build(),
                new HashSet<>(Arrays.asList(Pattern.class))
        );
    }

    @Test
    public void nameSizeNotMatch() throws Exception {
        ValidationUtil.assertValidate(
                builder.name("").build(),
                new HashSet<>(Arrays.asList(NotBlank.class, Size.class))
        );
    }
}