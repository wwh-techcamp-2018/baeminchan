package codesquad.domain.search;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchItemRepository extends JpaRepository<SearchItem, Long> {
}
