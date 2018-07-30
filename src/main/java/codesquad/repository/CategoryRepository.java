package codesquad.repository;

import codesquad.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoriesByParentEquals(Category parent);

    Optional<Category> findCategoryById(Long id);
}
