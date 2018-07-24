package codesquad.user.dto;

import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import codesquad.validate.EqualPasswords;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString
@EqualPasswords
@NoArgsConstructor
@Getter
@Setter
public class SignupDto {

    @NotNull(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞게 입력해주세요.",
            regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotNull(message = "비밀번호를 입력해주세요.")
    @Pattern(message = "영문 및 숫자를 포함한 8자 이상 16자 이하로 입력해주세요.",
            regexp = "^.*(?=^.{8,16}$)(?=.*\\d)(?=.*[a-zA-Z]).*$")
    private String password;

    private String confirmPassword;

    @NotNull(message = "이름을 입력해주세요.")
    @Size(message = "2자 이상 45자 이하로 입력해주세요.", min = 2, max = 45)
    private String name;

    @NotNull(message = "전화번호를 입력해주세요.")
    @Pattern(message = "전화번호를 000-0000-0000 형태로 입력해주세요.",
            regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$")
    private String phoneNumber;

    public SignupDto(String email, String password, String confirmPassword, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public boolean validatePassword() {
        if (password == null) {
            return password == confirmPassword;
        }

        return password.equals(confirmPassword);
    }

    public User toEntity(PasswordEncoder encoder) {
        return User.builder()
                .email(email)
                .password(encoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .role(Role.USER)
                .build();
    }
}
