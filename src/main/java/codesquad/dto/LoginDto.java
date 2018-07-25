package codesquad.dto;

import codesquad.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginDto {

    @NotBlank
    @Pattern(regexp = User.EMAIL_PATTERN)
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    @Pattern(regexp = User.PASSWORD_PATTERN)
    private String password;

    public LoginDto() {

    }

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
