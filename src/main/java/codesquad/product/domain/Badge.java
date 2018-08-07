package codesquad.product.domain;

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
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;

    @Column
    private String name;

    @ManyToMany(mappedBy = "badges")
    @JsonIgnore
    private List<Product> products;
}

// TODO : TBL_PRODUCT_BADGE 왜 생김?
