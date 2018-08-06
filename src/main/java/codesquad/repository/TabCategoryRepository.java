package codesquad.repository;

import codesquad.domain.TabCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabCategoryRepository extends JpaRepository<TabCategory, Long> {
}
