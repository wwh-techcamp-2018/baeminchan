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
        builder = new SignupDto.SignupDtoBuilder()
                .withEmail("aa@aa.aa")
                .withPassword("password")
                .withPasswordConfirm("password")
                .withName("aabbcc")
                .withPhoneNumber("010-0101-0011");
    }

    @Test
    public void emailPatternNotMatch() throws Exception {
        ValidationUtil.assertValidate(
                builder.withEmail("aaaa").build(),
                new HashSet<>(Arrays.asList(Pattern.class))
        );
    }

    @Test
    public void phoneNumberPatternNotMatch() throws Exception {
        ValidationUtil.assertValidate(
                builder.withPhoneNumber("001a-a-").build(),
                new HashSet<>(Arrays.asList(Pattern.class))
        );
    }

    @Test
    public void nameSizeNotMatch() throws Exception {
        ValidationUtil.assertValidate(
                builder.withName("").build(),
                new HashSet<>(Arrays.asList(NotBlank.class, Size.class))
        );
    }
}