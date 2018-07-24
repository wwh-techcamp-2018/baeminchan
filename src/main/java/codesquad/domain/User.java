package codesquad.domain;

import codesquad.BadRequestException;
import codesquad.dto.SignupDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Pattern(regexp = "[._0-9a-zA-Z-]+@[0-9a-zA-Z]+.([0-9a-zA-Z]+)")
    @Column(length = 40, unique = true, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Size(min = 1, max = 10)
    @Column(length = 10, nullable = false)
    private String name;

    @Size(min = 4, max = 14)
    @Pattern(regexp = "[0-9]+-[0-9]+-[0-9]+")
    @Column(length = 14, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserPermissions permissions;

    public User() {
        this.permissions = UserPermissions.NORMAL;
    }

    public User(String email, String password, String name, String phoneNumber, UserPermissions permissions) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.permissions = permissions;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static User of(SignupDto signupDto, PasswordEncoder passwordEncoder) {
        if (!signupDto.getPassword().equals(signupDto.getPasswordConfirm()))
            throw new BadRequestException(DomainField.USER_PASSWORD.getFieldName(), "비밀번호와 비밀번호 확인이 일치하지 않습니다.");

        return new User(
                signupDto.getEmail(),
                passwordEncoder.encode(signupDto.getPassword()),
                signupDto.getName(),
                signupDto.getPhoneNumber(),
                UserPermissions.NORMAL
        );
    }

    public void checkPassword(String loginPassword, PasswordEncoder encoder) {
        if (!encoder.matches(loginPassword, password))
            throw new BadRequestException(DomainField.USER_PASSWORD.getFieldName(), "아이디와 비밀번호가 일치하지 않습니다.");
    }
}
