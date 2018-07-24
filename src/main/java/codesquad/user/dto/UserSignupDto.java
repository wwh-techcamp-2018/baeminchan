package codesquad.user.dto;


import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDto {
    private String name;
    private String email;
    private String password;
    private String passwordCheck;
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
