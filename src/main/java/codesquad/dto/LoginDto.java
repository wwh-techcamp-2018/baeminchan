package codesquad.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 4, max = 30)
    private String password;


    public static LoginDto defaultLoginDto() {
        return new LoginDto("javajigi@kakao.com", "123123");
    }

    public LoginDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
