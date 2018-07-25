package codesquad.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotBlank
    @Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[0-9a-zA-Z-]+)$", message = "email 형식을 정확히 입력해 주세요.")
    private String email;

    @Pattern(regexp="[0-9a-zA-Z]*$", message="숫자와 영문으로만 구성된 비밀번호를 입력하시오.")
    @Size(min=8, max=16)
    private String password;

    private String passwordConfirm;


    @Size(min=1, max=10)
    private String name;

    @NotBlank
    @Pattern(regexp = "[0-9]+-[0-9]+-[0-9]+", message = "phoneNumber 형식을 따라주세요.")
    @Size(min=4, max=14)
    private String phoneNumber;

    public UserDTO(){

    }

    public UserDTO(String email, String password, String passwordConfirm, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public boolean passwordConfirm() {
        return password.equals(passwordConfirm);
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
