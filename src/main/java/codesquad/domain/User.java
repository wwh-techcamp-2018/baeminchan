package codesquad.domain;

import codesquad.dto.JoinUserDTO;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String encryptedPassword;
    @NotNull
    private String name;
    @NotNull
    private String phoneNo;
    @Column
    private boolean isAdmin = false;

    public User() {
    }

    public User(@NotNull String email, @NotNull String encryptedPassword, @NotNull String name, @NotNull String phoneNo) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public static User fromDTO(JoinUserDTO joinUserDTO, PasswordEncoder passwordEncoder) {
        return new User(joinUserDTO.getEmail(), passwordEncoder.encode(joinUserDTO.getPassword()),
                joinUserDTO.getName(), joinUserDTO.getPhoneNo());
    }

    protected void changeAdmin() {
        isAdmin = true;
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, encryptedPassword);
    }
}
