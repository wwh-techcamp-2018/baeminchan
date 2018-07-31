package codesquad.repository;

import codesquad.domain.Category;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    //Optional<List<Category>> findByParentIsNull();
    Optional<List<Category>> findByParentId(Long pId);
}
