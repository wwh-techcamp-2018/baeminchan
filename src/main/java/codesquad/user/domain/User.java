package codesquad.user.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    public static final GuestUser GUEST_USER = new GuestUser();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role = Role.DEFAULT;
//    private Set<Role> role = new HashSet<>(Arrays.asList(Role.DEFAULT));

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean deleted;

    @JsonIgnore
    public boolean isGuestUser() {
        return false;
    }

    public boolean hasRole(Role... role) {
        Set<Role> requiredRole = new HashSet<>(Arrays.asList(role));
//        return this.role.containsAll(requiredRole);
        return requiredRole.contains(this.role);
    }

    public boolean isAdmin() {
        return this.role.equals(Role.ADMIN);
    }

    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }
}
