package codesquad.domain;

import codesquad.validator.EmailValidator;
import codesquad.validator.PasswordValidator;
import codesquad.validator.PhoneValidator;
import codesquad.validator.UserNameValidator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserValidationTest {
    private EmailValidator emailValidator;

    private UserNameValidator userNameValidator;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = User.builder()
                .email("a@b.com")
                .name("이혁진")
                .password("gurwls0808#$")
                .confirmPassword("password")
                .phone("010-1234-5678")
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

    @Test
    public void password_올바른형식() {
        PasswordValidator passwordValidator = new PasswordValidator();
        assertThat(passwordValidator.isValid(user.getPassword(), null)).isTrue();
    }

    @Test
    public void password_틀린형식_최대_길이_초과() {
        user.setPassword("wlgkfjdkvu!@#231412414213");
        userNameValidator = new UserNameValidator();
        assertThat(userNameValidator.isValid(user.getPassword(), null)).isFalse();
    }

    @Test
    public void password_틀린형식_최소_길이_미만() {
        user.setPassword("w!3");
        userNameValidator = new UserNameValidator();
        assertThat(userNameValidator.isValid(user.getPassword(), null)).isFalse();
    }

    @Test
    public void password_특수문자_없음() {
        user.setPassword("wfhjd3333");
        userNameValidator = new UserNameValidator();
        assertThat(userNameValidator.isValid(user.getPassword(), null)).isFalse();
    }

    @Test
    public void password_숫자_없음() {
        user.setPassword("wfhjd!!!!");
        userNameValidator = new UserNameValidator();
        assertThat(userNameValidator.isValid(user.getPassword(), null)).isFalse();
    }

    @Test
    public void phone_올바른형식() {
        PhoneValidator phoneValidator = new PhoneValidator();
        assertThat(phoneValidator.isValid(user.getPhone(), null)).isTrue();
    }

    @Test
    public void phone_틀린_기지국_번호() {
        user.setPhone("000-1234-5678");
        PhoneValidator phoneValidator = new PhoneValidator();
        assertThat(phoneValidator.isValid(user.getPhone(), null)).isFalse();
    }

    @Test
    public void phone_숫자가아님() {
        user.setPhone("000-1234-567a");
        PhoneValidator phoneValidator = new PhoneValidator();
        assertThat(phoneValidator.isValid(user.getPhone(), null)).isFalse();
    }
}
