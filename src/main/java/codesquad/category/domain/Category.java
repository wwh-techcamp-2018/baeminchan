package codesquad.category.domain;

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

    public Category(String title, Category parentCategory) {
        this.title = title;
        this.parentCategory = parentCategory;
    }

//    TODO: Product Entity 만들기
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    List<Product> products;
}
