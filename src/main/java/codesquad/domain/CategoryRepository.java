package codesquad.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Iterable<Category> findByDeletedAndParentCategoryIsNullOrderByPriorityAsc(boolean deleted);
    Optional<Category> findByName(String name);
    Iterable<Category> findByDeleted(boolean deleted);
}
