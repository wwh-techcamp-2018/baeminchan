package codesquad.domain;

import codesquad.validator.EmailValidator;
import codesquad.validator.UserNameValidator;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class UserValidationTest {
    private static Validator validator;

    private EmailValidator emailValidator;

    private UserNameValidator userNameValidator;

    private User user;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        user = User.builder()
                .email("a@b.com")
                .name("username")
                .password("password")
                .confirmPassword("password")
                .phone("000-0000-0000")
                .build();
    }

    @Test
    public void email_올바른형식() {
        emailValidator = new EmailValidator();
        assertThat(emailValidator.isValid(user.getEmail(), null)).isTrue();
    }

    @Test
    public void email_틀린형식() {
        user.setEmail("ab.com");
        emailValidator = new EmailValidator();
        assertThat(emailValidator.isValid(user.getEmail(), null)).isFalse();
    }

    @Test
    public void name_올바른형식() {
        userNameValidator = new UserNameValidator();
        assertThat(userNameValidator.isValid(user.getName(), null)).isTrue();
    }

    @Test
    public void name_틀린형식_최대_길이_초과() {
        user.setName("김수완무거북이과오러아려ㅏ너아쟈포나기가");
        userNameValidator = new UserNameValidator();
        assertThat(userNameValidator.isValid(user.getName(), null)).isFalse();
    }

    @Test
    public void name_틀린형식_최소_길이_미만() {
        user.setName("김");
        userNameValidator = new UserNameValidator();
        assertThat(userNameValidator.isValid(user.getName(), null)).isFalse();
    }

    @Test
    public void name_틀린형식_숫자포함() {
        user.setName("김1");
        userNameValidator = new UserNameValidator();
        assertThat(userNameValidator.isValid(user.getName(), null)).isFalse();
    }

    @Test
    public void name_틀린형식_특수문자포함() {
        user.setName("김$이혁진");
        userNameValidator = new UserNameValidator();
        assertThat(userNameValidator.isValid(user.getName(), null)).isFalse();
    }
}
