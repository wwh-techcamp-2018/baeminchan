package codesquad.user.dto;

import codesquad.user.domain.User;
import codesquad.utils.Regex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Pattern(regexp = Regex.EMAIL, message = "Invalid email")
    private String email;

    @NotNull
    @Pattern(regexp = Regex.PASSWORD, message = "Invalid password")
    private String password;

    public boolean matchPassword(User user, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(password, user.getPassword());
    }
}
