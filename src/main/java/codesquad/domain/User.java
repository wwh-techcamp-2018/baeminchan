package codesquad.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Data
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

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public User setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
        return this;
    }
}
