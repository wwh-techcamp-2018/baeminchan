package codesquad.domain;

import codesquad.validate.Email;
import codesquad.validate.Name;
import codesquad.validate.Password;
import codesquad.validate.Phone;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Email
    private String email;

    @Name
    private String name;

    @Password
    private String password;

    @Password
    private String confirmPassword;

    @Phone
    private String phone;

    public boolean matchPassword() {
        return password.equals(confirmPassword);
    }

    public User toEntity(PasswordEncoder passwordEncoder){
        if(!matchPassword()){
            //error 처리
        }
        return User.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .email(email)
                .phone(phone)
                .build();

    }

}
