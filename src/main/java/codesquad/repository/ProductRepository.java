package codesquad.repository;

import codesquad.domain.EventCategory;
import codesquad.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByIdEquals(Long id);

    List<Product> findAllByEventCategoryEquals(EventCategory eventCategory);
}
