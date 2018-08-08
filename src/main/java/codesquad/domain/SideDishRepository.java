package codesquad.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SideDishRepository extends CrudRepository<SideDish, Long> {
    Optional<List<SideDish>> findByNameContaining(String searchText);
}
