package codesquad.domain.user;

import codesquad.dto.user.LoginUserDto;
import codesquad.dto.user.UserSessionDto;
import codesquad.exception.LoginFailedException;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "UserType")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long uid;

    @Size(max = 200)
    @Column(unique = true, updatable = false, nullable = false)
    protected String email;

    @Size(max = 70)
    @Column(nullable = false)
    protected String encryptedPassword;

    @Size(min = 2)
    @Column(nullable = false)
    protected String name;

    @Size(min = 10, max = 11)
    @Column(nullable = false)
    protected String phoneNo;

    abstract boolean isAdmin();


    public void isMatchPassword(LoginUserDto loginUserDto, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(loginUserDto.getPassword(), encryptedPassword)) {
            throw new LoginFailedException();
        }
    }

    public UserSessionDto toUserSessionDto() {
        return new UserSessionDto(uid, isAdmin());
    }

    protected String encryptPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }
}
