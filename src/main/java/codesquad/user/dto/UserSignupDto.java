package codesquad.user.dto;


import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDto {
    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Pattern(regexp = "\\p{Alnum}+[.\\p{Alnum}]+@\\p{Alnum}+\\.\\p{Alpha}+[.]?\\p{Alpha}+")
    private String email;

    @NotNull
    @Size(min = 8, max = 16)
    @Pattern(regexp = "^.*(?=.*\\d)(?=.*\\p{Alpha}).*$")
    private String password;

    @NotNull
    @Size(min = 8, max = 16)
    @Pattern(regexp = "^.*(?=.*\\d)(?=.*\\p{Alpha}).*$")
    private String passwordCheck;

    @NotNull
    @Size(min = 12, max = 13)
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    private String phoneNumber;


    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .role(Role.USER)
                .build();
    }

    public boolean matchPassword() {
        return password.equals(passwordCheck);
    }
}
