package codesquad.user.dto;

import codesquad.user.domain.User;
import codesquad.constant.Regex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
public class UserLoginDto {
    @NotNull
    @Pattern(regexp = Regex.EMAIL)
    private String email;

    @NotNull
    @Size(min = 8, max = 16)
    @Pattern(regexp = Regex.PASSWORD)
    private String password;

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
