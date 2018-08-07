package codesquad.repository;

import codesquad.domain.Banchan;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface BanchanRepository extends CrudRepository<Banchan, Long> {
    List<Banchan> findByParentId(Long parentId);
}
