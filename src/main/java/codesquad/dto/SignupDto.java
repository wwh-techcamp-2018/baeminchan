package codesquad.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupDto {

    @NotBlank
    @Pattern(regexp = "^[._0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[0-9a-zA-Z-]+)+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(min = 8, max = 16)
    @Pattern(regexp = "^[0-9a-zA-Z]+", message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;

    private String passwordConfirm;

    @Size(min = 1, max = 10)
    private String name;

    @Size(min = 4, max = 14)
    @Pattern(regexp = "[0-9]+-[0-9]+-[0-9]+", message = "숫자만 입력해주세요.")
    private String phoneNumber;

    public SignupDto() {

    }

    public SignupDto(String email, String password, String passwordConfirm, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
