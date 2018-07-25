package codesquad.domain;

import codesquad.converter.LocalDateTimeConverter;
import codesquad.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true, updatable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_role_to_user"))
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime joinDate;

    public User(String userId, String password, String name, String phoneNumber) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public User(String userId, String password, String name, String phoneNumber, Role role) {
        this(userId, password, name, phoneNumber);
        this.role = role;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public void init(Role role) {
        this.role = role;
        this.joinDate = LocalDateTime.now();
    }
}
