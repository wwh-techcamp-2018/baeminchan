package codesquad.category.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    void deleteByParentCategory(Category category);

    boolean existsByParentCategory(Category category);

    List<Category> findAllByParentCategoryIsNull();

    List<Category> findByParentCategoryId(Long id);
}
