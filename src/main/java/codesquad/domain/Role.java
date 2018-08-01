package codesquad.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public Role(Authority authority) {
        this.authority = authority;
    }

    public boolean isAdmin() {
        return this.authority == Authority.ADMIN;
    }
}
