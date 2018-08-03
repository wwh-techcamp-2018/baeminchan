package codesquad.product.domain;

import lombok.Builder;

import javax.persistence.*;
import java.util.Set;


@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(length = 45, nullable = false)
    private String title;

    @Column(nullable = false)
    private Long originalPrice;

    @Column(nullable = false)
    private Double discountPercent;

    @Column(length = 100, nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
    private Set<String> images;

    @Column(length = 100)
    private String deliveryType;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
    private Set<Day> receiptableDays;
}
