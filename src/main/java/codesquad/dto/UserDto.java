package codesquad.dto;

import codesquad.annotation.FieldsValueMatch;
import codesquad.domain.Role;
import codesquad.domain.User;
import codesquad.validate.Patterns;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@FieldsValueMatch(
        field = "password",
        fieldMatch = "password2"
)
public class UserDto {

    @Pattern(regexp = Patterns.USER_ID)
    @Size(min = 5, max = 30)
    private String userId;
    @Pattern(regexp = Patterns.PASSWORD)
    @Size(min = 8, max = 16)
    private String password;
    @Pattern(regexp = Patterns.PASSWORD)
    @Size(min = 8, max = 16)
    private String password2;
    @Pattern(regexp = Patterns.NAME)
    @Size(min = 2, max = 16)
    private String name;
    @Pattern(regexp = Patterns.PHONE_NUMBER)
    private String phoneNumber;

    public UserDto() {
    }

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

    public User toUser(Role role, PasswordEncoder passwordEncoder) {
        return new User(this.userId, passwordEncoder.encode(this.password), this.name, this.phoneNumber, role, LocalDateTime.now());
    }
}