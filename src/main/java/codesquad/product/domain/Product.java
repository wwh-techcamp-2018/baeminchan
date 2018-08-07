package codesquad.product.domain;

import codesquad.category.domain.BestCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column
    private String title;

    @Column
    private String subtitle;

    @Column
    private String brand;

    @Column
    private int originalPrice;

    @Column
    private Long salesRate;

    @ManyToMany
    @JoinTable(name="tbl_Badge_product",
            joinColumns = @JoinColumn(name="badgeId"),
            inverseJoinColumns = @JoinColumn(name="productId"))
    private List<Badge> badges;

    @ManyToMany
    @JoinTable(name="tbl_bestcategory_product",
            joinColumns = @JoinColumn(name="productId"),
            inverseJoinColumns = @JoinColumn(name="bestcategoryId"))
    private List<BestCategory> bestCategories;


}
