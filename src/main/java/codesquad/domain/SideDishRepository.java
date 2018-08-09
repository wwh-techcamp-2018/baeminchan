package codesquad.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SideDishRepository extends CrudRepository<SideDish, Long> {
    List<SideDish> findByNameContaining(String word);
}
