package codesquad.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer price;
    @Column
    private Integer discountRate;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> bannerUrls;

    public Product(String name, Integer price, Integer discountRate, List<String> bannerUrls) {
        this.name = name;
        this.price = price;
        this.discountRate = discountRate;
        this.bannerUrls = bannerUrls;
    }

    public static Product defaultProduct() {
        return new Product("냉호박죽", 5850, 0, Arrays.asList("https://cdn.bmf.kr/_data/product/IC5BB/7db9fa5a56f6203836ae7c90ab67b3ce.jpg"));
    }
}
