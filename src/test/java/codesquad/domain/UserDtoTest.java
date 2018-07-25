package codesquad.domain;


import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoTest {

    private static final Logger log = LoggerFactory.getLogger(UserDtoTest.class);

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