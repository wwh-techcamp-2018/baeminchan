package codesquad.domain;

import codesquad.dto.CategoryDto;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
