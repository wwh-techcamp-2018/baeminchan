package codesquad.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Category {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne
    @Getter
    @Setter
    private Category parentCategory;

    public Category(String title) {
        this.title = title;
    }
//    TODO: Product Entity 만들기
//    @OneToMany(mappedBy = "category")
//    List<Product> products;
}
