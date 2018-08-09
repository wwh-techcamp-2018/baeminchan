package codesquad.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.title FROM Product p WHERE p.title LIKE %:keyword%")
    List<String> searchTitlesByKeyword(@Param("keyword") String keyword);
}
