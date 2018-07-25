package codesquad.domain;

import codesquad.validate.Email;
import codesquad.validate.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @Email
    private String email;

    @Password
    private String password;

    public boolean matchPassword(User user, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(password, user.getPassword());
    }
}
