package codesquad.domain;

import codesquad.exception.UnAuthenticationException;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public User(String userId, String password, String name, String phoneNumber, Role role, LocalDateTime joinDate) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.joinDate = joinDate;
    }

    public boolean login(String rawPassword, PasswordEncoder passwordEncoder) throws UnAuthenticationException {
        if (!passwordEncoder.matches(rawPassword, this.password)) {
            throw new UnAuthenticationException("비밀번호가 일치하지 않습니다.");
        }
        return true;
    }

    public boolean isAdmin() {
        return this.role.isAdmin();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", joinDate=" + joinDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(role, user.role) &&
                Objects.equals(joinDate, user.joinDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, password, name, phoneNumber, role, joinDate);
    }
}
