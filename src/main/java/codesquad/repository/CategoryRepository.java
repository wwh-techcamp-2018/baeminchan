package codesquad.repository;


import codesquad.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByParentIsNull();
    List<Category> findByName(String name);
    Optional<Category> findByNameAndParentIsNull(String parent);
}
