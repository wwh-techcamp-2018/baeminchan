package codesquad.user.domain;

import codesquad.support.domain.BaseTimeEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "user_id", length = 32)
    private String uuid;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 45, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;
}
