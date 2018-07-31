package codesquad.product.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAllByParentCategory(Category mainCategory);
}
