package codesquad.repository;

import codesquad.domain.BestCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BestCategoryRepository extends CrudRepository<BestCategory, Long> {
    List<BestCategory> findAll();
}
