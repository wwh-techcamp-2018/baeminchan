package codesquad.repository;

import codesquad.domain.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
    List<EventCategory> findAll();
}
