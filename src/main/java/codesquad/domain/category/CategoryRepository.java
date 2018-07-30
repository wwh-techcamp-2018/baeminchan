package codesquad.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(value = "SELECT * FROM CATEGORY where PARENT_ID IS NULL", nativeQuery = true)
    List<Category> findAllRootCategories();

    List<Category> findByParent(Category parent);
}
