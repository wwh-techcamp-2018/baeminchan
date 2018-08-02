package codesquad.repository;

import codesquad.domain.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
    Optional<Menu> findByParentIsNull();
}
