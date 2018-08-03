package codesquad.domain;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends CrudRepository<Promotion, Long> {
    Iterable<Promotion> findByDeletedAndStartDateLessThanEqualAndEndDateGreaterThanEqual(boolean isDeleted, LocalDate today1, LocalDate today2);
    List<Promotion> findByDeleted(boolean isDeleted);
}
