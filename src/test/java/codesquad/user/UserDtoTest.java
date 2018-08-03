package codesquad.user;


import codesquad.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoTest {

    private UserDto userDto;

    @Before
    public void setUp() throws Exception {
        userDto = UserDto.builder()
                .name("woowa")
                .email("sample@woowa.com")
                .phone("010-4592-1234")
                .password("woowahan1!")
                .confirmPassword("woowahan1!")
                .build();
    }

    @Test
    public void matchPasswordSucceed() {
        assertThat(userDto.matchPassword()).isTrue();
    }

    @Test
    public void matchPasswordFail() {
        userDto.setConfirmPassword("beaminchan1@");
        assertThat(userDto.matchPassword()).isFalse();
    }
}