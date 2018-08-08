package codesquad.domain.product;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 2)
    private String title;

    @Column
    @Size(min = 2)
    private String description;

    @Column
    private Long price;

    @Column
    @Size(min = 2)
    private String imgUrl;
//
//    @ManyToOne()
//    @JoinColumn(name="category_id")
//    private Category category;
}
