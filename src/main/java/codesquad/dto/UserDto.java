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
        message = "비밀번호가 일치하지 않습니다."
)
public class UserDto {

    @Pattern(regexp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$")
    private String userId;

    @Pattern(regexp = "[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$")
    private String emailDomain;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
    @Size(min = 8, max = 16)
    private String password;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
    @Size(min = 8, max = 16)
    private String password2;
    @Pattern(regexp = "^[가-힣]*$")
    @Size(min = 2, max = 16)
    private String name;
    @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$")
    private String phoneNumber;

    public UserDto() {
    }
//
//    public UserDto(String userId, String emailDomain, String password, String password2, String name, String phoneNumber) {
//        this.userId = userId + "@" + emailDomain;
//        this.emailDomain = emailDomain;
//        this.password = password;
//        this.password2 = password2;
//        this.name = name;
//        this.phoneNumber = phoneNumber;
//    }

    public UserDto setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDto setPassword2(String password2) {
        this.password2 = password2;
        return this;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public UserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDto setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
        this.userId = userId + "@" + this.emailDomain;
        return this;
    }    // 클라이언트에서 빈칸 확인하기

    public User toUser(Role role, PasswordEncoder passwordEncoder) {
        return new User(this.userId, passwordEncoder.encode(this.password), this.name, this.phoneNumber, role, LocalDateTime.now());
    }
}

