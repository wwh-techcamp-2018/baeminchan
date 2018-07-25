package codesquad.user.dto;

import codesquad.support.test.DtoValidationTest;
import org.junit.Before;
import org.junit.Test;

public class LoginDtoTest extends DtoValidationTest<LoginDto> {

    private LoginDto dto;

    @Before
    public void setUp() throws Exception {
        dto = new LoginDto("email@email.com", "password1234");
    }

    @Test
    public void 정상적인_로그인_양식() {
        validate(dto, 0);
    }

    @Test
    public void 이메일이_NULL() {
        dto.setEmail(null);
        validate(dto, 1);
    }

    @Test
    public void 이메일이_형식에_맞지_않음() {
        dto.setEmail("tech_cmy-woowahan.com");
        validate(dto, 1);
    }

    @Test
    public void 비밀번호가_NULL() {
        dto.setPassword(null);
        validate(dto, 1);
    }

    @Test
    public void 비밀번호가_8자_미만() {
        final String TOO_SHORT_PASSWORD = "a1s2d3f";
        dto.setPassword(TOO_SHORT_PASSWORD);
        validate(dto, 1);
    }

    @Test
    public void 비밀번호가_16자_초과() {
        final String TOO_LONG_PASSWORD = "a1s2d3f4g5h6j7k8l";
        dto.setPassword(TOO_LONG_PASSWORD);
        validate(dto, 1);
    }

    @Test
    public void 숫자로만_이루어진_비밀번호() {
        dto.setPassword("12345678");
        validate(dto, 1);
    }

    @Test
    public void 문자로만_이루어진_비밀번호() {
        dto.setPassword("abcdefgh");
        validate(dto, 1);
    }
}