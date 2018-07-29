package codesquad.domain.user;

import codesquad.dto.user.JoinUserDto;
import codesquad.dto.user.LoginUserDto;
import codesquad.exception.LoginFailedException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Slf4j
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "UserType")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Size(max = 200)
    @Column(unique = true, updatable = false, nullable = false)
    private String email;

    @Size(max = 70)
    @Column(nullable = false)
    private String encryptedPassword;

    @Size(min=2)
    @Column(nullable = false)
    private String name;

    @Size(min =10 ,max =11)
    @Column(nullable = false)
    private String phoneNo;

    public User() {
    }

    private User(JoinUserDto joinUserDto, PasswordEncoder passwordEncoder) {
        this.name = joinUserDto.getName();
        this.phoneNo = joinUserDto.getPhoneNo();
        this.encryptedPassword = encryptPassword(joinUserDto.getPassword(),passwordEncoder);
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

    private String encryptPassword(String password,PasswordEncoder passwordEncoder){
        return  passwordEncoder.encode(password);
    }

    public boolean isAdmin() {
        return false;
    }
}

