package codesquad.dto;

import codesquad.domain.User;
import codesquad.domain.UserPermissions;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupDto {

    @NotBlank
    @Pattern(regexp = User.EMAIL_PATTERN)
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    @Pattern(regexp = User.PASSWORD_PATTERN)
    private String password;

    private String passwordConfirm;

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;

    @NotBlank
    @Size(min = 4, max = 14)
    @Pattern(regexp = User.PHONENUMBER_PATTERN)
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

    public boolean isPasswordMatched() {
        return password.equals(passwordConfirm);
    }

    public static class SignupDtoBuilder {
        private String email;
        private String name;
        private String password;
        private String passwordConfirm;
        private String phoneNumber;

        public SignupDtoBuilder() {

        }

        public SignupDtoBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public SignupDtoBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public SignupDtoBuilder withPasswordConfirm(String passwordConfirm) {
            this.passwordConfirm = passwordConfirm;
            return this;
        }

        public SignupDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SignupDtoBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public SignupDto build() {
            return new SignupDto(email, password, passwordConfirm, name, phoneNumber);
        }
    }
}
