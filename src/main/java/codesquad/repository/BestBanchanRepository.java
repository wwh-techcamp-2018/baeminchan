package codesquad.repository;

import codesquad.domain.BestCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//@Repository
public interface BestBanchanRepository extends CrudRepository<BestCategory, Long> {
    Iterable<BestCategory> findByIsActiveTrue();
}
