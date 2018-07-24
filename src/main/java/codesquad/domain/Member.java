package codesquad.domain;

import lombok.Data;
import support.domain.Role;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String phoneNumber;

    @ElementCollection
    @CollectionTable(name = "MEMBER_ROLES", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>(Arrays.asList(Role.DEFAULT));

    public Member(String email, String password, String username, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }
}
