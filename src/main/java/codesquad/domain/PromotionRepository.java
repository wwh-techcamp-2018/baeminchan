package codesquad.domain;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends CrudRepository<Promotion, Long> {
    List<Promotion> findByStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityAsc(LocalDate nowDate1, LocalDate nowDate2);
}
