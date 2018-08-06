package codesquad.domain;

import codesquad.enums.Authority;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByAuthority(Authority authority);
}
