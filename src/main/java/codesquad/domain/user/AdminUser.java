package codesquad.domain.user;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
public class AdminUser extends User {
    @Override
    public boolean isAdmin() {
        return true;
    }
}
