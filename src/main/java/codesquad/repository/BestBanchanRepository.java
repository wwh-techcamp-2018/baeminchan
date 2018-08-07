package codesquad.repository;

import codesquad.domain.BestCategory;
import org.springframework.data.repository.CrudRepository;

//@Repository
public interface BestBanchanRepository extends CrudRepository<BestCategory, Long> {
}
