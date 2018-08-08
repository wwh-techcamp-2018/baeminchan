package codesquad.domain;

import codesquad.exception.BadRequestException;
import codesquad.dto.SignupDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@ToString
@NoArgsConstructor
public class User {
    public static final User GUEST_USER = new GuestUser();

    public static final String EMAIL_PATTERN = "[._0-9a-zA-Z-]+@[0-9a-zA-Z]+.([0-9a-zA-Z]+)";
    public static final String PHONENUMBER_PATTERN = "[0-9]+-[0-9]+-[0-9]+";
    public static final String PASSWORD_PATTERN = "^[0-9a-zA-Z]+";

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @NotBlank
    @Pattern(regexp = EMAIL_PATTERN)
    @Column(length = 40, unique = true, nullable = false, updatable = false)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @Setter
    @NotBlank
    @Size(min = 1, max = 10)
    @Column(length = 10, nullable = false)
    private String name;

    @Getter
    @Setter
    @NotBlank
    @Size(min = 4, max = 14)
    @Pattern(regexp = PHONENUMBER_PATTERN)
    @Column(length = 14, nullable = false)
    private String phoneNumber;

    @Getter
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserPermissions permissions = UserPermissions.NORMAL;

    @Builder
    public User(String email, String password, String name, String phoneNumber, UserPermissions permissions) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.permissions = permissions;
    }

    public static User of(SignupDto signupDto, PasswordEncoder passwordEncoder) {
        if (!signupDto.isPasswordMatched())
            throw new BadRequestException(DomainField.USER_PASSWORD_CONFIRM, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");

        return User.builder()
                .email(signupDto.getEmail())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .name(signupDto.getName())
                .phoneNumber(signupDto.getPhoneNumber())
                .permissions(UserPermissions.NORMAL)
                .build();
    }

    public User checkPassword(String loginPassword, PasswordEncoder encoder) {
        if (!encoder.matches(loginPassword, password))
            throw new BadRequestException(DomainField.USER_PASSWORD, "아이디와 비밀번호가 일치하지 않습니다.");
        return this;
    }

    public boolean isGuestUser() {
        return false;
    }

    public boolean isAdmin() {
        return permissions == UserPermissions.ADMIN;
    }

    static class GuestUser extends User {

        @Override
        public boolean isGuestUser() {
            return true;
        }
    }
}
