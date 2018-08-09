package codesquad.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int discountRate;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> bannerUrls = new HashSet<>();

    public boolean isMatchName(String serchText) {
        return this.name.contains(serchText);
    }

    public Product(String name) {
        this.name = name;
    }
}
