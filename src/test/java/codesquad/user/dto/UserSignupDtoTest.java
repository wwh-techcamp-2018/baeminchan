package codesquad.user.dto;

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
}