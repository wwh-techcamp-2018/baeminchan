package codesquad.repository;

import codesquad.domain.Promotion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PromotionRepository extends CrudRepository<Promotion, Long> {
    List<Promotion> findAll();
}
