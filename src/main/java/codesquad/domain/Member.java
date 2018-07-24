package codesquad.domain;

import codesquad.support.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 320, nullable = false, unique = true)
    private String email;
    @Column(length = 200, nullable = false)
    private String password;
    @Column(length = 30, nullable = false)
    private String username;
    @Column(length = 11, nullable = false)
    private String phoneNumber;
    @ElementCollection
    private Set<Role> roles = new HashSet<>(Arrays.asList(Role.DEFAULT));

    public Member(String email, String password, String username, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }
}
