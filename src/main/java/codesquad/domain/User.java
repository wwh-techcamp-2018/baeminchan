package codesquad.domain;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final Logger log = LoggerFactory.getLogger(User.class);

    @Column(nullable = false, length = 30, unique = true, updatable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Size(min = 2, max = 16)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user_role"))
    private Role role;

    @Column
    private LocalDateTime joinDate;

    public User() {
    }

    public User(String userId, String password, @Size(min = 2, max = 16) String name, String phoneNumber, Role role, LocalDateTime joinDate) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.joinDate = joinDate;
    }

    public boolean login(String password, PasswordEncoder passwordEncoder) throws IllegalAccessException {
        if (!passwordEncoder.matches(password, this.password)) {
            throw new IllegalAccessException();
        }
        return true;
    }
}
