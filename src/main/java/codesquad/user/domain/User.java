package codesquad.user.domain;

import codesquad.support.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "user_id", length = 32)
    private String uuid;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 45, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 20, nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String email, String password, String phoneNumber, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
