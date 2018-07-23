package codesquad.domain;

import codesquad.dto.JoinUserDto;
import codesquad.dto.LoginUserDto;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
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

    @Column(nullable = false)
    private boolean isAdmin = false;

    public static User createUserByJoinUserDto(JoinUserDto joinUserDto){
        User user = new User();
        user.setEmail(joinUserDto.getEmail());
        user.setEncryptedPassword(joinUserDto.getPassword());
        user.setName(joinUserDto.getName());
        user.setPhoneNo(joinUserDto.getPhoneNo());

        return user;
    }

    public void isMatchPassword(LoginUserDto loginUserDto) {
        if(!this.encryptedPassword.equals(loginUserDto.getPassword())){
            throw new IllegalArgumentException();
        }
    }
}

