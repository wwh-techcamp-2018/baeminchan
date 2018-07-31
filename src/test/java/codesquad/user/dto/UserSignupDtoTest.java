package codesquad.user.dto;

import codesquad.user.domain.UserTest;

public class UserSignupDtoTest {
    public static UserSignupDto.UserSignupDtoBuilder validDtoBuilder() {
        return UserSignupDto.builder()
                .name("javajigi")
                .password(UserTest.RAW_PASSWORD)
                .passwordCheck(UserTest.RAW_PASSWORD)
                .phoneNumber("010-1234-5678")
                .email("tester@gmail.com");
    }
}