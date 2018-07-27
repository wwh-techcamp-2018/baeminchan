package codesquad.user.dto;

import codesquad.exception.UnAuthenticationException;
import codesquad.user.domain.User;
import codesquad.utils.Regex;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Pattern(regexp = Regex.EMAIL, message = "Invalid email")
    private String email;

    @NotNull
    @Pattern(regexp = Regex.USERNAME, message = "Invalid name")
    private String name;

    @NotNull
    @Pattern(regexp = Regex.PASSWORD, message = "Invalid password")
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @Pattern(regexp = Regex.PHONE, message = "Invalid phone number")
    private String phone;

    public boolean matchPassword() {
        return password.equals(confirmPassword);
    }

    public User toEntity(PasswordEncoder passwordEncoder) {

        return User.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .email(email)
                .phone(phone)
                .build();

    }
}
