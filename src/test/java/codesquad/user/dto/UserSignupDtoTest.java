package codesquad.user.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSignupDtoTest {
    private static final String PASSWORD = "password";

    @Test
    public void matchPassword() {
        UserSignupDto dto = UserSignupDto.builder().password(PASSWORD).passwordCheck(PASSWORD).build();
        assertThat(dto.matchPassword()).isTrue();
    }


    @Test
    public void matchPasswordFail() {
        UserSignupDto dto = UserSignupDto.builder().password(PASSWORD).passwordCheck(PASSWORD + 1).build();
        assertThat(dto.matchPassword()).isFalse();
    }
}