package codesquad.product.domain;

import codesquad.category.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
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
    @CollectionTable(joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
    private Set<String> images;

    @Column
    private String deliveryType;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
    private Set<Day> receiptableDays;

    @Builder
    public Product(Category category, String title, Long originalPrice, Double discountPercent, String description, Set<String> images, String deliveryType, Set<Day> receiptableDays) {
        this.category = category;
        this.title = title;
        this.originalPrice = originalPrice;
        this.discountPercent = discountPercent;
        this.description = description;
        this.images = images;
        this.deliveryType = deliveryType;
        this.receiptableDays = receiptableDays;
    }
}
