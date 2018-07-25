package codesquad.user.dto;

import codesquad.support.test.DtoValidationTest;
import org.junit.Before;
import org.junit.Test;

public class SignupDtoTest extends DtoValidationTest<SignupDto> {
    private SignupDto dto;

    @Before
    public void setUp() throws Exception {
        dto = new SignupDto("email@email.com", "password1234", "password1234", "윤찬명", "010-5114-6224");
    }

    @Test
    public void 정상적인_회원가입_요청() {
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
        dto.setConfirmPassword(null);
        validate(dto, 1);
    }

    @Test
    public void 비밀번호가_8자_미만() {
        final String TOO_SHORT_PASSWORD = "a1s2d3f";
        dto.setPassword(TOO_SHORT_PASSWORD);
        dto.setConfirmPassword(TOO_SHORT_PASSWORD);
        validate(dto, 1);
    }

    @Test
    public void 비밀번호가_16자_초과() {
        final String TOO_LONG_PASSWORD = "a1s2d3f4g5h6j7k8l";
        dto.setPassword(TOO_LONG_PASSWORD);
        dto.setConfirmPassword(TOO_LONG_PASSWORD);
        validate(dto, 1);
    }

    @Test
    public void 두_비밀번호가_일치하지_않음() {
        dto.setPassword("password1");
        dto.setConfirmPassword("password2");
        validate(dto, 1);
    }

    @Test
    public void 숫자로만_이루어진_비밀번호() {
        dto.setPassword("12345678");
        dto.setConfirmPassword("12345678");
        validate(dto, 1);
    }

    @Test
    public void 문자로만_이루어진_비밀번호() {
        dto.setPassword("abcdefgh");
        dto.setConfirmPassword("abcdefgh");
        validate(dto, 1);
    }

    @Test
    public void 이름이_NULL() {
        dto.setName(null);
        validate(dto, 1);
    }

    @Test
    public void 이름이_2자_미만() {
        dto.setName("윤");
        validate(dto, 1);
    }

    @Test
    public void 이름이_45자_초과() {
        dto.setName("12345678901234567890" +
                "12345678901234567890" +
                "123456");
        validate(dto, 1);
    }

    @Test
    public void 전화번호가_NULL() {
        dto.setPhoneNumber(null);
        validate(dto, 1);
    }

    @Test
    public void 전화번호가_형식에_맞지_않음() {
        dto.setPhoneNumber("0101-2345-231");
        validate(dto, 1);
    }
}