package codesquad.repository;

import codesquad.domain.BestBanchan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface BestBanchanRepository extends CrudRepository<BestBanchan, Long> {
}
