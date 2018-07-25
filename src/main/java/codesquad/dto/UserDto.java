package codesquad.dto;

import codesquad.domain.User;
import codesquad.exception.NotMatchException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDto {
    private static final Logger log = LoggerFactory.getLogger(UserDto.class);

    @Pattern(regexp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$")
    private String userId;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
    @Size(min = 8, max = 16)
    private String password;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
    @Size(min = 8, max = 16)
    private String rePassword;
    @Pattern(regexp = "^[가-힣]*$")
    @Size(min = 2, max = 16)
    private String name;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String phoneNumber;




    public UserDto(String userId, String password, String name, String phoneNumber, String rePassword) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.rePassword = rePassword;
    }

    public User toUser(String encodedPassword) throws NotMatchException {
        if (!matchPassword()) throw new NotMatchException("비밀번호가 일치하지 않습니다.");

        return new User(userId, encodedPassword, name, phoneNumber);
    }

    private boolean matchPassword() {
        return rePassword.equals(password);
    }
}
