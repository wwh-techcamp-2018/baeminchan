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
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String phoneNumber;

    public UserDto() {
    }

    public UserDto(String userId, String emailDomain, String password, String password2, String name, String phoneNumber) {
        this.userId = userId;
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
