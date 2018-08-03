package codesquad.product.domain;

import codesquad.category.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private Long originalPrice;

    @Column(nullable = false)
    private Double discountPercent;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable
    private Set<String> images;

    @Column(nullable = false)
    private String deliveryType;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "pid", referencedColumnName = "id"))
    private Set<Day> receiptableDays;
}
