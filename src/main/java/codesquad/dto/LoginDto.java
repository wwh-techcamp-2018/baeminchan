package codesquad.dto;

import codesquad.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDto {

    @NotBlank
    @Pattern(regexp = User.EMAIL_PATTERN)
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    @Pattern(regexp = User.PASSWORD_PATTERN)
    private String password;
}
