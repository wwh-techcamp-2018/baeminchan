package codesquad.domain.user;

import codesquad.dto.user.JoinUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;

@Slf4j
@Getter
@NoArgsConstructor
@Entity
public class NormalUser extends User {
    private NormalUser(JoinUserDto joinUserDto, PasswordEncoder passwordEncoder) {
        this.name = joinUserDto.getName();
        this.phoneNo = joinUserDto.getPhoneNo();
        this.encryptedPassword = encryptPassword(joinUserDto.getPassword(), passwordEncoder);
        this.email = joinUserDto.getEmail();
    }

    public static NormalUser createUserByJoinUserDto(JoinUserDto joinUserDto, PasswordEncoder passwordEncoder) {
        NormalUser normalUser = new NormalUser(joinUserDto, passwordEncoder);
        return normalUser;
    }

    public boolean isAdmin() {
        return false;
    }
}

