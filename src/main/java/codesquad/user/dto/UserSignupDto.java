package codesquad.user.dto;


import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import codesquad.util.Regex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserSignupDto {
    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Pattern(regexp = Regex.EMAIL)
    private String email;

    @NotNull
    @Size(min = 8, max = 16)
    @Pattern(regexp = Regex.PASSWORD)
    private String password;

    @NotNull
    @Size(min = 8, max = 16)
    @Pattern(regexp = Regex.PASSWORD)
    private String passwordCheck;

    @NotNull
    @Size(min = 12, max = 13)
    @Pattern(regexp = Regex.PHONE_NUM)
    private String phoneNumber;

    @AssertTrue(message = "")
    private boolean isMatchPassword() {
        return password.equals(passwordCheck);
    }

    public UserSignupDto(String name, String email, String password, String passwordCheck, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.phoneNumber = phoneNumber;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .role(Role.USER)
                .build();
    }
}
