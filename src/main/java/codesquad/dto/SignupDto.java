package codesquad.dto;

import codesquad.domain.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SignupDto {

    @NotBlank
    @Pattern(regexp = User.EMAIL_PATTERN)
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    @Pattern(regexp = User.PASSWORD_PATTERN)
    private String password;

    private String passwordConfirm;

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;

    @NotBlank
    @Size(min = 4, max = 14)
    @Pattern(regexp = User.PHONENUMBER_PATTERN)
    private String phoneNumber;

    public boolean isPasswordMatched() {
        return password.equals(passwordConfirm);
    }
}
