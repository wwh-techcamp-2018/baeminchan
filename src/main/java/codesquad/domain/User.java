package codesquad.domain;



import codesquad.support.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class User extends AbstractEntity {
    public static final String PASSWORD = "^[a-zA-Z0-9]*$";
    public static final String NAME = "^[가-힣]*$";
    public static final String PHONE_NUMBER = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";

    @NotNull
    @Column(unique = true)
    @Email
    private String email;

    @Transient
    @NotNull
    @Pattern(regexp = PASSWORD)
    @Size(min = 8, max = 20)
    private String password;

    @Transient
    @NotNull
    @Pattern(regexp = PASSWORD)
    @Size(min = 8, max = 20)
    private String passwordCheck;

    @Column( nullable = false)
    private String encodedPassword;

    @NotNull
    @Pattern(regexp = NAME)
    @Size(min = 2, max = 4)
    private String name;

    @NotNull
    @Pattern(regexp = PHONE_NUMBER)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserAuthority userAuthority = UserAuthority.GENERAL;



    public User() {
    }

    public User(String email, String password, String name, String phoneNumber){
        this(email, password, password, name, phoneNumber);
    }


    public User(String email, String password, String passwordCheck, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
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

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
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

    public UserAuthority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(UserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordCheck='" + passwordCheck + '\'' +
                ", encodedPassword='" + encodedPassword + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userAuthority= '" + userAuthority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email);
    }

    public boolean isEqualPassword(){
        return password.equals(passwordCheck);
    }

    public void encrypt(PasswordEncoder passwordEncoder) {
        encodedPassword = passwordEncoder.encode(password);
    }

    public boolean matchEncodedPassword(PasswordEncoder passwordEncoder, User rawUser){
        return matchEncodedPassword(passwordEncoder, rawUser.password);
    }

    public boolean matchEncodedPassword(PasswordEncoder passwordEncoder, String rawPassword){
        return passwordEncoder.matches(rawPassword, this.encodedPassword);
    }

    public void registAdmin() {
        this.userAuthority = UserAuthority.ADMIN;
    }

    public boolean isAdmin(){
        return this.userAuthority == UserAuthority.ADMIN;
    }
}