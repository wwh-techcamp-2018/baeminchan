package codesquad.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class User implements Serializable {
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

    @Column
    private LocalDateTime joinDate;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_role_to_user"))
    private Role role;

    public User(String userId, String password, String name, String phoneNumber, Role role) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.joinDate = LocalDateTime.now();
    }

    public boolean isAdmin() {
        return role.isAdmin();
    }
}
