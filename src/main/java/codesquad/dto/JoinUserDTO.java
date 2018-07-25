package codesquad.dto;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class JoinUserDTO {
    private static final Logger log = LoggerFactory.getLogger(JoinUserDTO.class);

    private final static String EMAIL_REGEX = "([a-zA-Z0-9]+[a-zA-Z0-9._-]*)@[a-zA-Z0-9]+((\\.?[a-zA-Z0-9]+){0,1})\\.([a-z]{2,3})";
    private final static String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private final static String PHONE_NO_REGEX = "(01[06789])-(\\d{3,4})-(\\d{4})";

    @Pattern(regexp = EMAIL_REGEX, message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 50, message = "이메일은 최대 50자 길이로 입력해주세요.")
    private String email;

    @Pattern(regexp = PASSWORD_REGEX, message = "비밀번호 형식이 올바르지 않습니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8~16자를 입력해주세요.")
    private String password;

    private String passwordConfirm;

    @Size(max = 32)
    private String name;

    @Pattern(regexp = PHONE_NO_REGEX, message = "전화번호 형식이 올바르지 않습니다.")
    private String phoneNo;

    @AssertTrue(message = "비밀번호와 비밀번호 확인이 서로 다릅니다.")
    public boolean isMatched() {
        log.debug("{} {}", password, passwordConfirm);
        return password.equals(passwordConfirm);
    }
}
