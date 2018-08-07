package codesquad.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class BestProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    public BestProduct(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }
}
