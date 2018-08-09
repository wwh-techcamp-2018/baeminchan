package codesquad.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import support.domain.Role;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    // TODO password 암호화 이후 길이 바꾸기
    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 11, nullable = false)
    private String phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "MEMBER_ROLES", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>(Arrays.asList(Role.DEFAULT));

    public Member(String email, String password, String username, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public void giveAdmin() {
        this.roles.add(Role.ADMIN);
    }

    public boolean matchPassword(String rawPassword, PasswordEncoder bcryptPasswordEncoder) {
        return bcryptPasswordEncoder.matches(rawPassword, this.password);
    }

    public boolean isAdmin() {
        return this.roles.contains(Role.ADMIN);
    }
}
