package codesquad.dto;

import codesquad.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    @NotBlank(message = "please input email")
    @Size(min = 3, max = 100, message = "email should be longer than 3 and shorter than 100")
    @Email(message = "wrong email format")
    private String email;

    @NotBlank(message = "please input password")
    @Size(min = 8, max = 15, message = "password should be longer than 8 and shorter than 15")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-zA-Z]).{8,15})", message = "Invalid Password")
    private String password;

    @NotBlank(message = "please input username")
    @Size(min = 3, max = 30, message = "username should be longer than 3 and shorter than 30")
    private String username;

    @NotBlank(message = "please input phone number")
    @Size(min = 9, max = 11, message = "phoneNumber should be longer than 9 and shorter than 11")
    private String phoneNumber;

    public Member toEntity(PasswordEncoder bcryptPasswordEncoder) {
        return new Member(email, bcryptPasswordEncoder.encode(password), username, phoneNumber);
    }
}
