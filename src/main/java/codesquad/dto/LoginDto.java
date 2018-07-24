package codesquad.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginDto {

    @NotBlank
    @Pattern(regexp = "^[._0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[0-9a-zA-Z-]+)+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(min = 8, max = 16)
    @Pattern(regexp = "^[0-9a-zA-Z]+", message = "비밀번호 형식이 올바르지 않습니다.")
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
