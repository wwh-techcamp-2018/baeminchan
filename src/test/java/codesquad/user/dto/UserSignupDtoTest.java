package codesquad.user.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSignupDtoTest {
    public static final String PASSWORD = "123456qwerA";

    public static UserSignupDto.UserSignupDtoBuilder validDtoBuilder() {
        return UserSignupDto.builder()
                .name("javajigi")
                .password(PASSWORD)
                .passwordCheck(PASSWORD)
                .phoneNumber("010-1234-5678")
                .email("tester@gmail.com");
    }

    @Test
    public void matchPassword() {
        UserSignupDto dto = validDtoBuilder().build();
        assertThat(dto.matchPassword()).isTrue();
    }


    @Test
    public void matchPasswordFail() {
        UserSignupDto dto = validDtoBuilder()
                .passwordCheck(PASSWORD + 1)
                .build();
        assertThat(dto.matchPassword()).isFalse();
    }
}