package codesquad.repository;

import codesquad.domain.Category;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
 List<Category> findByParent(Category parent);
}
