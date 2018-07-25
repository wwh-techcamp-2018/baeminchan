package codesquad.dto;

import codesquad.annotation.FieldsValueMatch;
import codesquad.domain.Role;
import codesquad.domain.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@FieldsValueMatch(
        field = "password",
        fieldMatch = "password2",
        message = "Passwords do not match!"
)
public class UserDto {

    @Pattern(regexp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$", message = "아이디는 영어와 숫자 조합만 가능합니다.")
    private String userId;

    @Pattern(regexp = "[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$", message = "이메일 형식을 지켜주세요.")
    private String emailDomain;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 반드시 영문과 숫자와 특수문자를 포함해야합니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8~16자로 만들어주세요.")
    private String password;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 반드시 영문과 숫자와 특수문자를 포함해야합니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8~16자로 만들어주세요.")
    private String password2;
    @Pattern(regexp = "^[가-힣]*$", message = "이름은 2~16자리 한글만 가능합니다.")
    @Size(min = 2, max = 16, message = "이름은 2~16자로 만들어주세요.")
    private String name;
    @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$", message = "핸드폰 형식이 올바르지 않습니다.")
    private String phoneNumber;

    public UserDto() {
    }

    public UserDto(String userId, String emailDomain, String password, String password2, String name, String phoneNumber) {
        this.userId = userId + "@" + emailDomain;
        this.emailDomain = emailDomain;
        this.password = password;
        this.password2 = password2;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
        this.userId = userId + "@" + this.emailDomain;
    }

    public User toUser(Role role, PasswordEncoder passwordEncoder) {
        return new User()
                .setUserId(this.userId)
                .setPassword(passwordEncoder.encode(this.password))
                .setName(this.name)
                .setPhoneNumber(this.phoneNumber)
                .setJoinDate(LocalDateTime.now())
                .setRole(role);

    }
}
