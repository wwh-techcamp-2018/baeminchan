package codesquad.domain;

import codesquad.dto.JoinUserDto;
import codesquad.dto.LoginUserDto;
import codesquad.exception.LoginFailedException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "UserType")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(unique = true, updatable = false, nullable = false)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNo;

    public User() {
    }

    private User(JoinUserDto joinUserDto, PasswordEncoder passwordEncoder) {
        this.name = joinUserDto.getName();
        this.phoneNo = joinUserDto.getPhoneNo();
        this.encryptedPassword = passwordEncoder.encode(joinUserDto.getPassword());
        this.email = joinUserDto.getEmail();
    }

    public static User createUserByJoinUserDto(JoinUserDto joinUserDto, PasswordEncoder passwordEncoder) {
        User user = new User(joinUserDto, passwordEncoder);
        return user;
    }

    public void isMatchPassword(LoginUserDto loginUserDto, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(loginUserDto.getPassword(),encryptedPassword)) {
            throw new LoginFailedException();
        }
    }

    public boolean isAdmin() {
        return false;
    }
}

